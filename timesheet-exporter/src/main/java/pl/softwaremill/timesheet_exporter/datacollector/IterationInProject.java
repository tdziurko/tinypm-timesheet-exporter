package pl.softwaremill.timesheet_exporter.datacollector;

import com.google.code.tinypmclient.Iteration;
import com.google.code.tinypmclient.Project;

import java.util.Date;

public class IterationInProject implements ItemWithDateRange {

    private Iteration iteration;
    private Project project;

    public IterationInProject(Iteration iteration, Project project) {
        this.iteration = iteration;
        this.project = project;
    }

    public int getId() {
        return iteration.getId();
    }

    public void setId(int id) {
        iteration.setId(id);
    }

    public String getName() {
        return iteration.getName();
    }

    public void setName(String name) {
        iteration.setName(name);
    }

    public String getGoal() {
        return iteration.getGoal();
    }

    public void setGoal(String goal) {
        iteration.setGoal(goal);
    }

    public float getPlannedVelocity() {
        return iteration.getPlannedVelocity();
    }

    public void setPlannedVelocity(float plannedVelocity) {
        iteration.setPlannedVelocity(plannedVelocity);
    }

    public int getPosition() {
        return iteration.getPosition();
    }

    public void setPosition(int position) {
        iteration.setPosition(position);
    }

    public Date getStartDate() {
        return iteration.getStartDate();
    }

    public void setStartDate(Date startDate) {
        iteration.setStartDate(startDate);
    }

    public int getDuration() {
        return iteration.getDuration();
    }

    public void setDuration(int duration) {
        iteration.setDuration(duration);
    }

    public Project getProject() {
        return project;
    }

    @Override
    public Date getEndDate() {
        return iteration.getCalculatedEndDate();
    }
}
