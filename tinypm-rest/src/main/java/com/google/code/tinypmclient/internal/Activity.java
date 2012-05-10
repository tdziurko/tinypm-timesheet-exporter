package com.google.code.tinypmclient.internal;

import com.google.code.tinypmclient.Task;
import com.google.code.tinypmclient.User;
import com.google.code.tinypmclient.UserStory;
import com.google.code.tinypmclient.internal.binding.DateAdapter;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlRootElement
@XmlAccessorType(FIELD)
public class Activity extends ActiveResource {

    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date date;

    private Task task;

    private float timeSpent;

    private User user;

    private UserStory userStory;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public float getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(float timeSpent) {
        this.timeSpent = timeSpent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }
}
