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
import org.bud.framework.domain.flow.ActExtraTaskPri;
import org.bud.framework.domain.flow.ActExtraTaskPriExample;

public interface ActExtraTaskPriMapper {
    @SelectProvider(type=ActExtraTaskPriSqlProvider.class, method="countByExample")
    long countByExample(ActExtraTaskPriExample example);

    @DeleteProvider(type=ActExtraTaskPriSqlProvider.class, method="deleteByExample")
    int deleteByExample(ActExtraTaskPriExample example);

    @Delete({
        "delete from act_extra_task_pri",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into act_extra_task_pri (id, proc_def_id, ",
        "task_def_key, pri_type, ",
        "user_id, group_id)",
        "values (#{id,jdbcType=VARCHAR}, #{procDefId,jdbcType=VARCHAR}, ",
        "#{taskDefKey,jdbcType=VARCHAR}, #{priType,jdbcType=TINYINT}, ",
        "#{userId,jdbcType=VARCHAR}, #{groupId,jdbcType=VARCHAR})"
    })
    int insert(ActExtraTaskPri record);

    @InsertProvider(type=ActExtraTaskPriSqlProvider.class, method="insertSelective")
    int insertSelective(ActExtraTaskPri record);

    @SelectProvider(type=ActExtraTaskPriSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="proc_def_id", property="procDefId", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_def_key", property="taskDefKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="pri_type", property="priType", jdbcType=JdbcType.TINYINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.VARCHAR)
    })
    List<ActExtraTaskPri> selectByExample(ActExtraTaskPriExample example);

    @Select({
        "select",
        "id, proc_def_id, task_def_key, pri_type, user_id, group_id",
        "from act_extra_task_pri",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="proc_def_id", property="procDefId", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_def_key", property="taskDefKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="pri_type", property="priType", jdbcType=JdbcType.TINYINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.VARCHAR)
    })
    ActExtraTaskPri selectByPrimaryKey(String id);

    @UpdateProvider(type=ActExtraTaskPriSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ActExtraTaskPri record, @Param("example") ActExtraTaskPriExample example);

    @UpdateProvider(type=ActExtraTaskPriSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ActExtraTaskPri record, @Param("example") ActExtraTaskPriExample example);

    @UpdateProvider(type=ActExtraTaskPriSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ActExtraTaskPri record);

    @Update({
        "update act_extra_task_pri",
        "set proc_def_id = #{procDefId,jdbcType=VARCHAR},",
          "task_def_key = #{taskDefKey,jdbcType=VARCHAR},",
          "pri_type = #{priType,jdbcType=TINYINT},",
          "user_id = #{userId,jdbcType=VARCHAR},",
          "group_id = #{groupId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(ActExtraTaskPri record);
}