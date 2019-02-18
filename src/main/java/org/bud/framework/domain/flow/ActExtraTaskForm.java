package org.bud.framework.domain.flow;

public class ActExtraTaskForm {
    private String id;

    private String procDefId;

    private String taskDefKey;

    private Byte formType;

    private String formUrl;

    private String formId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId == null ? null : procDefId.trim();
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey == null ? null : taskDefKey.trim();
    }

    public Byte getFormType() {
        return formType;
    }

    public void setFormType(Byte formType) {
        this.formType = formType;
    }

    public String getFormUrl() {
        return formUrl;
    }

    public void setFormUrl(String formUrl) {
        this.formUrl = formUrl == null ? null : formUrl.trim();
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId == null ? null : formId.trim();
    }
}