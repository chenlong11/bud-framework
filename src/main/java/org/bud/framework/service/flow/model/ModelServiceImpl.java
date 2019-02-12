package org.bud.framework.service.flow.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.bud.framework.exception.InternalServerErrorException;
import org.bud.framework.domain.flow.*;
import org.bud.framework.domain.system.User;
import org.bud.framework.mapper.flow.ModelRelationMapper;
import org.bud.framework.mapper.flow.ModelMapper;
import org.bud.framework.util.StringUtil;
import org.bud.framework.util.WebUtil;
import org.bud.framework.vo.flow.ModelVo;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.editor.language.json.converter.util.JsonConverterUtil;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chenlong
 * Date：2017/9/11
 * time：16:55
 */
@Service
public class ModelServiceImpl implements ModelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelServiceImpl.class);

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected ModelRelationMapper modelRelationMapper;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected ModelImageService modelImageService;

    @Autowired
    protected RepositoryService repositoryService;

    public ModelKeyRepresentation validateModelKey(Model model, Integer modelType, String key) {
        ModelKeyRepresentation modelKeyResponse = new ModelKeyRepresentation();
        modelKeyResponse.setKey(key);

        ModelVo modelVo = new ModelVo();
        modelVo.setKey(key);
        modelVo.setModelType(modelType);

        List<ModelVo> models = modelMapper.getByParameters(modelVo);
        for (Model modelInfo : models) {
            if (model == null || !modelInfo.getId().equals(model.getId())) {
                modelKeyResponse.setKeyAlreadyExists(true);
                modelKeyResponse.setId(modelInfo.getId());
                modelKeyResponse.setName(modelInfo.getName());
                break;
            }
        }

        return modelKeyResponse;
    }

    @Override
    public String createModelJson(ModelRepresentation model) {
        String json = null;
        if (Integer.valueOf(AbstractModel.MODEL_TYPE_BPMN).equals(model.getModelType())) {
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.set("stencilset", stencilSetNode);
            ObjectNode propertiesNode = objectMapper.createObjectNode();
            propertiesNode.put("process_id", model.getKey());
            propertiesNode.put("name", model.getName());
            if (StringUtil.isNotEmpty(model.getDescription())) {
                propertiesNode.put("documentation", model.getDescription());
            }
            editorNode.set("properties", propertiesNode);

            ArrayNode childShapeArray = objectMapper.createArrayNode();
            editorNode.set("childShapes", childShapeArray);
            ObjectNode childNode = objectMapper.createObjectNode();
            childShapeArray.add(childNode);
            ObjectNode boundsNode = objectMapper.createObjectNode();
            childNode.set("bounds", boundsNode);
            ObjectNode lowerRightNode = objectMapper.createObjectNode();
            boundsNode.set("lowerRight", lowerRightNode);
            lowerRightNode.put("x", 130);
            lowerRightNode.put("y", 193);
            ObjectNode upperLeftNode = objectMapper.createObjectNode();
            boundsNode.set("upperLeft", upperLeftNode);
            upperLeftNode.put("x", 100);
            upperLeftNode.put("y", 163);
            childNode.set("childShapes", objectMapper.createArrayNode());
            childNode.set("dockers", objectMapper.createArrayNode());
            childNode.set("outgoing", objectMapper.createArrayNode());
            childNode.put("resourceId", "startEvent1");
            ObjectNode stencilNode = objectMapper.createObjectNode();
            childNode.set("stencil", stencilNode);
            stencilNode.put("id", "StartNoneEvent");
            json = editorNode.toString();
        }
        return json;
    }

    @Override
    public Model createModel(ModelRepresentation model, String editorJson, User createdBy) {
        Model newModel = new Model();
        newModel.setVersion(1);
        newModel.setName(model.getName());
        newModel.setKey(model.getKey());
        newModel.setModelType(Model.MODEL_TYPE_BPMN);
        newModel.setCreated(Calendar.getInstance().getTime());
        newModel.setCreatedBy(createdBy.getId());
        newModel.setDescription(model.getDescription());
        newModel.setModelEditorJson(editorJson);
        newModel.setLastUpdated(Calendar.getInstance().getTime());
        newModel.setLastUpdatedBy(createdBy.getId());
        persistModel(newModel);
        return newModel;
    }

    protected Model persistModel(Model model) {

        if (StringUtil.isNotEmpty(model.getModelEditorJson())) {
            // Parse json to java
            ObjectNode jsonNode = null;
            try {
                jsonNode = (ObjectNode) objectMapper.readTree(model.getModelEditorJson());
            } catch (Exception e) {
                LOGGER.error("Could not deserialize json model", e);
                throw new InternalServerErrorException("Could not deserialize json model");
            }

            if ((model.getModelType() == null || model.getModelType().intValue() == Model.MODEL_TYPE_BPMN)) {

                // Thumbnail
                byte[] thumbnail = modelImageService.generateThumbnailImage(model, jsonNode);
                if (thumbnail != null) {
                    model.setThumbnail(thumbnail);
                }

                modelMapper.save(model);

                // Relations
                handleBpmnProcessFormModelRelations(model, jsonNode);
                handleBpmnProcessDecisionTaskModelRelations(model, jsonNode);

            }
        }else {
            modelMapper.save(model);
        }

        return model;
    }

    protected void handleBpmnProcessFormModelRelations(AbstractModel bpmnProcessModel, ObjectNode editorJsonNode) {
        List<JsonNode> formReferenceNodes = JsonConverterUtil.filterOutJsonNodes(JsonConverterUtil.getBpmnProcessModelFormReferences(editorJsonNode));
        Set<String> formIds = JsonConverterUtil.gatherStringPropertyFromJsonNodes(formReferenceNodes, "id");
        handleModelRelations(bpmnProcessModel, formIds, ModelRelationTypes.TYPE_FORM_MODEL_CHILD);
    }

    protected void handleBpmnProcessDecisionTaskModelRelations(AbstractModel bpmnProcessModel, ObjectNode editorJsonNode) {
        List<JsonNode> decisionTableNodes = JsonConverterUtil.filterOutJsonNodes(JsonConverterUtil.getBpmnProcessModelDecisionTableReferences(editorJsonNode));
        Set<String> decisionTableIds = JsonConverterUtil.gatherStringPropertyFromJsonNodes(decisionTableNodes, "id");
        handleModelRelations(bpmnProcessModel, decisionTableIds, ModelRelationTypes.TYPE_DECISION_TABLE_MODEL_CHILD);
    }

    /**
     * Generic handling of model relations: deleting/adding where needed.
     */
    protected void handleModelRelations(AbstractModel bpmnProcessModel, Set<String> idsReferencedInJson, String relationshipType) {

        // Find existing persisted relations
        List<ModelRelation> persistedModelRelations = modelRelationMapper.findByParentModelIdAndType(bpmnProcessModel.getId(), relationshipType);

        // if no ids referenced now, just delete them all
        if (idsReferencedInJson == null || idsReferencedInJson.size() == 0) {
            for (ModelRelation modelRelation : persistedModelRelations) {
                modelRelationMapper.delete(modelRelation);
            }
            return;
        }

        Set<String> alreadyPersistedModelIds = new HashSet<String>(persistedModelRelations.size());
        for (ModelRelation persistedModelRelation : persistedModelRelations) {
            if (!idsReferencedInJson.contains(persistedModelRelation.getModelId())) {
                // model used to be referenced, but not anymore. Delete it.
                modelRelationMapper.delete(persistedModelRelation);
            } else {
                alreadyPersistedModelIds.add(persistedModelRelation.getModelId());
            }
        }

        // Loop over all referenced ids and see which one are new
        for (String idReferencedInJson : idsReferencedInJson) {

            // if model is referenced, but it is not yet persisted = create it
            if (!alreadyPersistedModelIds.contains(idReferencedInJson)) {

                // Check if model actually still exists. Don't create the relationship if it doesn't exist. The client UI will have cope with this too.
                if (modelMapper.get(idReferencedInJson) != null) {
                    modelRelationMapper.save(new ModelRelation(bpmnProcessModel.getId(), idReferencedInJson, relationshipType));
                }
            }
        }
    }

    @Override
    public List<ModelVo> getModelsByParameters(ModelVo modelVo) {
        return modelMapper.getByParameters(modelVo);
    }

    @Override
    public void delModelById(String id) {
        modelMapper.delete(id);
    }

    @Override
    public Model getModel(String modelId) {
        return modelMapper.get(modelId);
    }

    @Override
    public void saveModel(Model model) {
        model.setModelType(Model.MODEL_TYPE_BPMN);
        model.setLastUpdated(new Date());
        model.setLastUpdatedBy(WebUtil.getCurrentUser().getId());
        persistModel(model);
    }

    @Override
    public void publicModel(String id) {
        try {
            org.bud.framework.domain.flow.Model model = getModel(id);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(model.getModelEditorJson());
            byte[] bpmnBytes = null;

            BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
            String processName = model.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment()
                    .name(model.getName())
                    .addString(processName, new String(bpmnBytes))
                    .deploy();

            String processDefId = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult().getId();
            //回填model processDefId
            saveModel(new org.bud.framework.domain.flow.Model(model.getId(), deployment.getId(), processDefId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
