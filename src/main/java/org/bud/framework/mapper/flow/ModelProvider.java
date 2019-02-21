package org.bud.framework.mapper.flow;

import org.bud.framework.domain.flow.Model;
import org.bud.framework.util.IdGenerateUtil;
import org.bud.framework.util.StringUtil;
import org.bud.framework.vo.flow.ModelVo;

/**
 * Created by chenlong
 * Date：2017/9/11
 * time：18:28
 */
public class ModelProvider {

    public String save(Model model) {
        if (StringUtil.isNotEmpty(model.getId())) {
            return update(model);
        } else {
            model.setId(IdGenerateUtil.generate16Id());
            return insert();
        }
    }

    public String insert() {
        StringBuilder builder = new StringBuilder(
        " insert into ACT_DE_MODEL ( " +
        "            id, " +
        "            name, " +
        "            model_key, " +
        "            description, " +
        "            model_comment, " +
        "            created, " +
        "            created_by, " +
        "            last_updated, " +
        "            last_updated_by, " +
        "            version, " +
        "            model_editor_json, " +
        "            model_type, " +
        "            thumbnail)  " +
        "         values ( " +
        "            #{id}, " +
        "            #{name}, " +
        "            #{key}, " +
        "            #{description}, " +
        "            #{comment}, " +
        "            #{created}, " +
        "            #{createdBy}, " +
        "            #{lastUpdated}, " +
        "            #{lastUpdatedBy}, " +
        "            #{version}, " +
        "            #{modelEditorJson}, " +
        "            #{modelType}, " +
        "            #{thumbnail} " +
        "          ) "
        );
        return builder.toString();
    }


    public String update(Model model) {

        StringBuilder builder = new StringBuilder(" update ACT_DE_MODEL set id = #{id} ");

        if(StringUtil.isNotNull(model.getName())){
            builder.append(" ,name = #{name} ");
        }

        if(StringUtil.isNotNull(model.getKey())){
            builder.append(" ,model_key = #{key} ");
        }

        if(StringUtil.isNotNull(model.getDescription())){
            builder.append(" ,description = #{description} ");
        }

        if(StringUtil.isNotNull(model.getLastUpdated())){
            builder.append(" ,last_updated = #{lastUpdated} ");
        }

        if(StringUtil.isNotNull(model.getLastUpdatedBy())){
            builder.append(" ,last_updated_by = #{lastUpdatedBy} ");
        }

        if(StringUtil.isNotNull(model.getModelEditorJson())){
            builder.append(" ,model_editor_json = #{modelEditorJson} ");
        }

        if(StringUtil.isNotNull(model.getThumbnail())){
            builder.append(" ,thumbnail = #{thumbnail}  ");
        }

        if(StringUtil.isNotNull(model.getDeploymentId())){
            builder.append(" ,deploy_id = #{deploymentId}  ");
        }

        if(StringUtil.isNotNull(model.getProcessDefId())){
            builder.append(" ,proc_def_id = #{processDefId}  ");
        }

        if(StringUtil.isNotNull(model.getProcessDefKey())){
            builder.append(" ,proc_def_key = #{processDefKey}  ");
        }

        builder.append(" where id = #{id} ");

        return builder.toString();
    }


    public String getByParameters(ModelVo modelVo) {

        StringBuilder builder = new StringBuilder();
        builder.append(" select a.id,a.name,a.model_key 'key',a.description,a.model_comment comment,a.created,a.created_by createdBy,a.last_updated lastUpdated, " +
                " a.last_updated_by lastUpdatedBy,a.version,a.model_editor_json modelEditorJson,a.model_type modelType,a.thumbnail,a.deploy_id deploymentId,proc_def_key processDefKey " +
                " from ACT_DE_MODEL a  where 1=1 ");

        if(StringUtil.isNotEmpty(modelVo.getModelType())){
            builder.append(" and a.model_type = #{modelType} ");
        }

        if(StringUtil.isNotEmpty(modelVo.getFilter())){
            builder.append(" and (lower(a.name) like #{filter} or lower(a.description) like #{filter}) ");
        }

        if(StringUtil.isNotEmpty(modelVo.getKey())){
            builder.append(" and a.model_key = #{key} ");
        }

        return builder.toString();
    }

}
