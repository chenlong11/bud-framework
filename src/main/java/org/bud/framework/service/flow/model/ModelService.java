package org.bud.framework.service.flow.model;

import org.bud.framework.po.flow.Model;
import org.bud.framework.po.flow.ModelKeyRepresentation;
import org.bud.framework.po.flow.ModelRepresentation;
import org.bud.framework.po.system.User;
import org.bud.framework.vo.flow.ModelVo;

import java.util.List;

/**
 * Created by chenlong
 * Date：2017/9/10
 * time：11:30
 */
public interface ModelService {

    String createModelJson(ModelRepresentation modelRepresentation);

    Model createModel(ModelRepresentation model, String editorJson, User user);

    ModelKeyRepresentation validateModelKey(Model model, Integer modelType, String key);

    List<ModelVo> getModelsByParameters(ModelVo modelVo);

    void delModelById(String id);

    Model getModel(String modelId);

    void saveModel(Model model);

    void publicModel(String id);
}
