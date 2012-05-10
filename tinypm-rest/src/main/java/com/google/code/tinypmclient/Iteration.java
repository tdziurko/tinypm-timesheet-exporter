package com.google.code.tinypmclient;

import com.google.code.tinypmclient.internal.ActiveResource;
import com.google.code.tinypmclient.internal.binding.DateAdapter;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlRootElement
@XmlAccessorType(FIELD)
public class Iteration extends ActiveResource {

    private int id;

    private String name;

    private String goal;

    private float plannedVelocity;

    private int position;

    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date startDate;

    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public float getPlannedVelocity() {
        return plannedVelocity;
    }

    public void setPlannedVelocity(float plannedVelocity) {
        this.plannedVelocity = plannedVelocity;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
