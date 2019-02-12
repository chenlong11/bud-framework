package org.bud.framework.mapper.flow;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.bud.framework.domain.flow.Model;
import org.bud.framework.vo.flow.ModelVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenlong
 * Date：2017/9/11
 * time：17:55
 */
@Repository
public interface ModelMapper {

    @SelectProvider(type = ModelProvider.class, method = "save")
    void save(Model model);

    @Delete({" delete from ACT_DE_MODEL where id = #{id} "})
    void delete(String id);

    @Select({" select id,name,model_key 'key',description,model_comment comment,created,created_by createdBy,last_updated lastUpdated," +
            " last_updated_by lastUpdatedBy,version,model_editor_json modelEditorJson,model_type modelType,thumbnail " +
            " from ACT_DE_MODEL where id = #{id} "})
    Model get(String id);

    @SelectProvider(type = ModelProvider.class, method = "getByParameters")
    List<ModelVo> getByParameters(ModelVo modelVo);

}
