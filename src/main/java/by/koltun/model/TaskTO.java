package by.koltun.model;

import java.io.Serializable;
import java.util.Date;

public class TaskTO implements Serializable {
    private static final long serialVersionUID = 6848678086081710581L;

    private String id;

    private String processInstanceId;

    private String processDefinitionId;

    private String assignee;

    private Date createDate;

    public TaskTO() {}

    public TaskTO(final String id, final String processInstanceId, final String processDefinitionId,
                  final String assignee, final Date createDate) {
        this.id = id;
        this.processInstanceId = processInstanceId;
        this.processDefinitionId = processDefinitionId;
        this.assignee = assignee;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(final String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(final String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(final String assignee) {
        this.assignee = assignee;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(final Date createDate) {
        this.createDate = createDate;
    }
}
