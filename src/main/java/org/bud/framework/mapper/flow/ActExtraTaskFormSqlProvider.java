package org.bud.framework.mapper.flow;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.bud.framework.domain.flow.ActExtraTaskForm;
import org.bud.framework.domain.flow.ActExtraTaskFormExample.Criteria;
import org.bud.framework.domain.flow.ActExtraTaskFormExample.Criterion;
import org.bud.framework.domain.flow.ActExtraTaskFormExample;

public class ActExtraTaskFormSqlProvider {

    public String countByExample(ActExtraTaskFormExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("act_extra_task_form");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(ActExtraTaskFormExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("act_extra_task_form");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ActExtraTaskForm record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("act_extra_task_form");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getProcDefId() != null) {
            sql.VALUES("proc_def_id", "#{procDefId,jdbcType=VARCHAR}");
        }
        
        if (record.getTaskDefKey() != null) {
            sql.VALUES("task_def_key", "#{taskDefKey,jdbcType=VARCHAR}");
        }
        
        if (record.getFormType() != null) {
            sql.VALUES("form_type", "#{formType,jdbcType=TINYINT}");
        }
        
        if (record.getFormUrl() != null) {
            sql.VALUES("form_url", "#{formUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getFormId() != null) {
            sql.VALUES("form_id", "#{formId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(ActExtraTaskFormExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("proc_def_id");
        sql.SELECT("task_def_key");
        sql.SELECT("form_type");
        sql.SELECT("form_url");
        sql.SELECT("form_id");
        sql.FROM("act_extra_task_form");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ActExtraTaskForm record = (ActExtraTaskForm) parameter.get("record");
        ActExtraTaskFormExample example = (ActExtraTaskFormExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("act_extra_task_form");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=VARCHAR}");
        }
        
        if (record.getProcDefId() != null) {
            sql.SET("proc_def_id = #{record.procDefId,jdbcType=VARCHAR}");
        }
        
        if (record.getTaskDefKey() != null) {
            sql.SET("task_def_key = #{record.taskDefKey,jdbcType=VARCHAR}");
        }
        
        if (record.getFormType() != null) {
            sql.SET("form_type = #{record.formType,jdbcType=TINYINT}");
        }
        
        if (record.getFormUrl() != null) {
            sql.SET("form_url = #{record.formUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getFormId() != null) {
            sql.SET("form_id = #{record.formId,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("act_extra_task_form");
        
        sql.SET("id = #{record.id,jdbcType=VARCHAR}");
        sql.SET("proc_def_id = #{record.procDefId,jdbcType=VARCHAR}");
        sql.SET("task_def_key = #{record.taskDefKey,jdbcType=VARCHAR}");
        sql.SET("form_type = #{record.formType,jdbcType=TINYINT}");
        sql.SET("form_url = #{record.formUrl,jdbcType=VARCHAR}");
        sql.SET("form_id = #{record.formId,jdbcType=VARCHAR}");
        
        ActExtraTaskFormExample example = (ActExtraTaskFormExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ActExtraTaskForm record) {
        SQL sql = new SQL();
        sql.UPDATE("act_extra_task_form");
        
        if (record.getProcDefId() != null) {
            sql.SET("proc_def_id = #{procDefId,jdbcType=VARCHAR}");
        }
        
        if (record.getTaskDefKey() != null) {
            sql.SET("task_def_key = #{taskDefKey,jdbcType=VARCHAR}");
        }
        
        if (record.getFormType() != null) {
            sql.SET("form_type = #{formType,jdbcType=TINYINT}");
        }
        
        if (record.getFormUrl() != null) {
            sql.SET("form_url = #{formUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getFormId() != null) {
            sql.SET("form_id = #{formId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, ActExtraTaskFormExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}