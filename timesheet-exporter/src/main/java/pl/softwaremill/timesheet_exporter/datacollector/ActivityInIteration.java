package pl.softwaremill.timesheet_exporter.datacollector;

import com.google.code.tinypmclient.Task;
import com.google.code.tinypmclient.User;
import com.google.code.tinypmclient.UserStory;
import com.google.code.tinypmclient.internal.Activity;

import java.util.Date;

public class ActivityInIteration {

    private Activity activity;
    private IterationInProject iteration;

    public ActivityInIteration(Activity activity, IterationInProject iteration) {
        this.activity = activity;
        this.iteration = iteration;
    }

    public Date getDate() {
        return activity.getDate();
    }

    public void setUser(User user) {
        activity.setUser(user);
    }

    public Task getTask() {
        return activity.getTask();
    }

    public void setDate(Date date) {
        activity.setDate(date);
    }

    public UserStory getUserStory() {
        return activity.getUserStory();
    }

    public void setUserStory(UserStory userStory) {
        activity.setUserStory(userStory);
    }

    public User getUser() {
        return activity.getUser();
    }

    public void setTask(Task task) {
        activity.setTask(task);
    }

    public void setTimeSpent(float timeSpent) {
        activity.setTimeSpent(timeSpent);
    }

    public float getTimeSpent() {
        return activity.getTimeSpent();
    }

    public IterationInProject getIteration() {
        return iteration;
    }
}
