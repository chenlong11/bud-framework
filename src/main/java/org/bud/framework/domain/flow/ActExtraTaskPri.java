package org.bud.framework.domain.flow;

public class ActExtraTaskPri {
    private String id;

    private String procDefId;

    private String taskDefKey;

    private Byte priType;

    private String userId;

    private String groupId;

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

    public Byte getPriType() {
        return priType;
    }

    public void setPriType(Byte priType) {
        this.priType = priType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }
}