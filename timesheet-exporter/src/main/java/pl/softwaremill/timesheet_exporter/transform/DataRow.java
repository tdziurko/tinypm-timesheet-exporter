package pl.softwaremill.timesheet_exporter.transform;

import java.util.Date;

public class DataRow {

    private String user;
    private String project;
    private String userStory;
    private String task;
    private Date date;
    private float timeSpent;

    public DataRow(String user, String project, String userStory, String task, Date date, float timeSpent) {
        this.user = user;
        this.project = project;
        this.userStory = userStory;
        this.task = task;
        this.date = date;
        this.timeSpent = timeSpent;
    }

    public String getUser() {
        return user;
    }

    public String getProject() {
        return project;
    }

    public String getUserStory() {
        return userStory;
    }

    public String getTask() {
        return task;
    }

    public Date getDate() {
        return date;
    }

    public float getTimeSpent() {
        return timeSpent;
    }
}
