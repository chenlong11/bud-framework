package org.bud.framework.domain.flow;

import java.util.ArrayList;
import java.util.List;

public class ActExtraTaskFormExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActExtraTaskFormExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProcDefIdIsNull() {
            addCriterion("proc_def_id is null");
            return (Criteria) this;
        }

        public Criteria andProcDefIdIsNotNull() {
            addCriterion("proc_def_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcDefIdEqualTo(String value) {
            addCriterion("proc_def_id =", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotEqualTo(String value) {
            addCriterion("proc_def_id <>", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdGreaterThan(String value) {
            addCriterion("proc_def_id >", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdGreaterThanOrEqualTo(String value) {
            addCriterion("proc_def_id >=", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLessThan(String value) {
            addCriterion("proc_def_id <", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLessThanOrEqualTo(String value) {
            addCriterion("proc_def_id <=", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLike(String value) {
            addCriterion("proc_def_id like", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotLike(String value) {
            addCriterion("proc_def_id not like", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdIn(List<String> values) {
            addCriterion("proc_def_id in", values, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotIn(List<String> values) {
            addCriterion("proc_def_id not in", values, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdBetween(String value1, String value2) {
            addCriterion("proc_def_id between", value1, value2, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotBetween(String value1, String value2) {
            addCriterion("proc_def_id not between", value1, value2, "procDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyIsNull() {
            addCriterion("task_def_key is null");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyIsNotNull() {
            addCriterion("task_def_key is not null");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyEqualTo(String value) {
            addCriterion("task_def_key =", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotEqualTo(String value) {
            addCriterion("task_def_key <>", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyGreaterThan(String value) {
            addCriterion("task_def_key >", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyGreaterThanOrEqualTo(String value) {
            addCriterion("task_def_key >=", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyLessThan(String value) {
            addCriterion("task_def_key <", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyLessThanOrEqualTo(String value) {
            addCriterion("task_def_key <=", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyLike(String value) {
            addCriterion("task_def_key like", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotLike(String value) {
            addCriterion("task_def_key not like", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyIn(List<String> values) {
            addCriterion("task_def_key in", values, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotIn(List<String> values) {
            addCriterion("task_def_key not in", values, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyBetween(String value1, String value2) {
            addCriterion("task_def_key between", value1, value2, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotBetween(String value1, String value2) {
            addCriterion("task_def_key not between", value1, value2, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andFormTypeIsNull() {
            addCriterion("form_type is null");
            return (Criteria) this;
        }

        public Criteria andFormTypeIsNotNull() {
            addCriterion("form_type is not null");
            return (Criteria) this;
        }

        public Criteria andFormTypeEqualTo(Byte value) {
            addCriterion("form_type =", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotEqualTo(Byte value) {
            addCriterion("form_type <>", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeGreaterThan(Byte value) {
            addCriterion("form_type >", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("form_type >=", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLessThan(Byte value) {
            addCriterion("form_type <", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLessThanOrEqualTo(Byte value) {
            addCriterion("form_type <=", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeIn(List<Byte> values) {
            addCriterion("form_type in", values, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotIn(List<Byte> values) {
            addCriterion("form_type not in", values, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeBetween(Byte value1, Byte value2) {
            addCriterion("form_type between", value1, value2, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("form_type not between", value1, value2, "formType");
            return (Criteria) this;
        }

        public Criteria andFormUrlIsNull() {
            addCriterion("form_url is null");
            return (Criteria) this;
        }

        public Criteria andFormUrlIsNotNull() {
            addCriterion("form_url is not null");
            return (Criteria) this;
        }

        public Criteria andFormUrlEqualTo(String value) {
            addCriterion("form_url =", value, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormUrlNotEqualTo(String value) {
            addCriterion("form_url <>", value, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormUrlGreaterThan(String value) {
            addCriterion("form_url >", value, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormUrlGreaterThanOrEqualTo(String value) {
            addCriterion("form_url >=", value, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormUrlLessThan(String value) {
            addCriterion("form_url <", value, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormUrlLessThanOrEqualTo(String value) {
            addCriterion("form_url <=", value, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormUrlLike(String value) {
            addCriterion("form_url like", value, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormUrlNotLike(String value) {
            addCriterion("form_url not like", value, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormUrlIn(List<String> values) {
            addCriterion("form_url in", values, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormUrlNotIn(List<String> values) {
            addCriterion("form_url not in", values, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormUrlBetween(String value1, String value2) {
            addCriterion("form_url between", value1, value2, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormUrlNotBetween(String value1, String value2) {
            addCriterion("form_url not between", value1, value2, "formUrl");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNull() {
            addCriterion("form_id is null");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNotNull() {
            addCriterion("form_id is not null");
            return (Criteria) this;
        }

        public Criteria andFormIdEqualTo(String value) {
            addCriterion("form_id =", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotEqualTo(String value) {
            addCriterion("form_id <>", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThan(String value) {
            addCriterion("form_id >", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThanOrEqualTo(String value) {
            addCriterion("form_id >=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThan(String value) {
            addCriterion("form_id <", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThanOrEqualTo(String value) {
            addCriterion("form_id <=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLike(String value) {
            addCriterion("form_id like", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotLike(String value) {
            addCriterion("form_id not like", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdIn(List<String> values) {
            addCriterion("form_id in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotIn(List<String> values) {
            addCriterion("form_id not in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdBetween(String value1, String value2) {
            addCriterion("form_id between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotBetween(String value1, String value2) {
            addCriterion("form_id not between", value1, value2, "formId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}