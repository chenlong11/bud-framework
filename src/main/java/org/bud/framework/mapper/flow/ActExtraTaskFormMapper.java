package org.bud.framework.mapper.flow;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.bud.framework.domain.flow.ActExtraTaskForm;
import org.bud.framework.domain.flow.ActExtraTaskFormExample;

public interface ActExtraTaskFormMapper {
    @SelectProvider(type=ActExtraTaskFormSqlProvider.class, method="countByExample")
    long countByExample(ActExtraTaskFormExample example);

    @DeleteProvider(type=ActExtraTaskFormSqlProvider.class, method="deleteByExample")
    int deleteByExample(ActExtraTaskFormExample example);

    @Delete({
        "delete from act_extra_task_form",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into act_extra_task_form (id, proc_def_id, ",
        "task_def_key, form_type, ",
        "form_url, form_id)",
        "values (#{id,jdbcType=VARCHAR}, #{procDefId,jdbcType=VARCHAR}, ",
        "#{taskDefKey,jdbcType=VARCHAR}, #{formType,jdbcType=TINYINT}, ",
        "#{formUrl,jdbcType=VARCHAR}, #{formId,jdbcType=VARCHAR})"
    })
    int insert(ActExtraTaskForm record);

    @InsertProvider(type=ActExtraTaskFormSqlProvider.class, method="insertSelective")
    int insertSelective(ActExtraTaskForm record);

    @SelectProvider(type=ActExtraTaskFormSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="proc_def_id", property="procDefId", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_def_key", property="taskDefKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="form_type", property="formType", jdbcType=JdbcType.TINYINT),
        @Result(column="form_url", property="formUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="form_id", property="formId", jdbcType=JdbcType.VARCHAR)
    })
    List<ActExtraTaskForm> selectByExample(ActExtraTaskFormExample example);

    @Select({
        "select",
        "id, proc_def_id, task_def_key, form_type, form_url, form_id",
        "from act_extra_task_form",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="proc_def_id", property="procDefId", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_def_key", property="taskDefKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="form_type", property="formType", jdbcType=JdbcType.TINYINT),
        @Result(column="form_url", property="formUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="form_id", property="formId", jdbcType=JdbcType.VARCHAR)
    })
    ActExtraTaskForm selectByPrimaryKey(String id);

    @UpdateProvider(type=ActExtraTaskFormSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ActExtraTaskForm record, @Param("example") ActExtraTaskFormExample example);

    @UpdateProvider(type=ActExtraTaskFormSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ActExtraTaskForm record, @Param("example") ActExtraTaskFormExample example);

    @UpdateProvider(type=ActExtraTaskFormSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ActExtraTaskForm record);

    @Update({
        "update act_extra_task_form",
        "set proc_def_id = #{procDefId,jdbcType=VARCHAR},",
          "task_def_key = #{taskDefKey,jdbcType=VARCHAR},",
          "form_type = #{formType,jdbcType=TINYINT},",
          "form_url = #{formUrl,jdbcType=VARCHAR},",
          "form_id = #{formId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(ActExtraTaskForm record);
}