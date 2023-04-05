package ibf2022.paf.assessment.server.models;

// TODO: Task 4

public class Task {

    private String username;
    private Integer taskId;
    private String description;
    private Integer priority;
    private String dueDate;
    private Integer taskCount;
    

    public Integer getTaskCount() {
        return taskCount;
    }
    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getTaskId() {
        return taskId;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Task() {
    }

    public Task(String username, Integer taskId, String description, Integer priority, String dueDate) {
        this.username = username;
        this.taskId = taskId;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task [username=" + username + ", taskId=" + taskId + ", description=" + description + ", priority="
                + priority + ", dueDate=" + dueDate + "]";
    }







}
