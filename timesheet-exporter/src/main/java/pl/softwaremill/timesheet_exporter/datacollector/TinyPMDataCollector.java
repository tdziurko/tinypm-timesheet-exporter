package pl.softwaremill.timesheet_exporter.datacollector;

import com.google.code.tinypmclient.Iteration;
import com.google.code.tinypmclient.Project;
import com.google.code.tinypmclient.TinyPM;
import com.google.code.tinypmclient.internal.Activity;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import pl.softwaremill.timesheet_exporter.settings.ExporterSettings;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class TinyPMDataCollector {

    protected TinyPM tinyPM;
    private ExporterSettings settings;
    private TimePredicatesFactory timePredicatesFactory;

    public TinyPMDataCollector(ExporterSettings settings) {

        this.settings = settings;
        tinyPM = new TinyPM(settings.getTinypmUrl(), settings.getAuthenticationToken());
        timePredicatesFactory = new TimePredicatesFactory(settings);
    }

    public Collection<ActivityInIteration> collectData() {

        List<Project> projects = loadRequestedProjects(settings.getProjectCodes());
        List<IterationInProject> allIterations = loadIterationsForProjects(projects);
        Collection<IterationInProject> iterationsInGivenMonth = filterIterations(allIterations);

        return loadFilteredTasks(iterationsInGivenMonth);
    }

    protected List<Project> loadRequestedProjects(List<String> projectCodes) {

        if (projectCodes == null) {
            return tinyPM.getAllProjects();
        }

        List<Project> projects = Lists.newArrayList();

        for (String code : projectCodes) {
            Project project = tinyPM.getProject(code);
            if (project != null) {
                projects.add(project);
            }
        }

        if (notAllProjectsWereFound(projectCodes, projects)) {
            return throwExceptionWithAvailableProjectCodes();
        }

        return projects;
    }

    private boolean notAllProjectsWereFound(List<String> projectCodes, List<Project> projects) {
        return projectCodes.size() != projects.size();
    }

    private List<Project> throwExceptionWithAvailableProjectCodes() {
        Collection<String> availableProjectCodes = Collections2.transform(tinyPM.getAllProjects(), new Function<Project, String>() {
            @Override
            public String apply(@Nullable Project project) {
                return project.getCode();
            }
        });

        throw new RuntimeException("Some projects were not found using given project codes. Available project codes are: \n" +
                Joiner.on(", ").join(availableProjectCodes));
    }

    private List<IterationInProject> loadIterationsForProjects(List<Project> projects) {
        List<IterationInProject> allIterations = Lists.newArrayList();
        for (Project project : projects) {

            List<Iteration> iterationsFromProject = tinyPM.getAllIterations(project);
            for (Iteration iteration : iterationsFromProject) {
                allIterations.add(new IterationInProject(iteration, project));
            }
        }
        return allIterations;
    }

    private Collection<IterationInProject> filterIterations(List<IterationInProject> allIterations) {
        return Collections2.filter(allIterations, timePredicatesFactory.forIteration());
    }

    private Collection<ActivityInIteration> loadFilteredTasks(Collection<IterationInProject> iterationsInGivenMonth) {
        List<ActivityInIteration> activities = Lists.newArrayList();

        for (IterationInProject iteration : iterationsInGivenMonth) {
            List<Activity> timesheetForIteration = tinyPM.getTimesheetForIteration(iteration.getId());

            for (Activity activity : timesheetForIteration) {
                activities.add(new ActivityInIteration(activity, iteration));
            }
        }

        return filterTasks(activities);
    }

    private Collection<ActivityInIteration> filterTasks(Collection<ActivityInIteration> activities) {

        activities = removeTasksBasingOnDate(activities);

        if (settings.getUser() != null) {
            return removeTasksFromOtherUsers(activities, settings.getUser());
        } else {
            return activities;
        }
    }

    private Collection<ActivityInIteration> removeTasksFromOtherUsers(Collection<ActivityInIteration> activities, final String user) {
        return Collections2.filter(activities, new Predicate<ActivityInIteration>() {
            @Override
            public boolean apply(ActivityInIteration activity) {
                return user.equals(activity.getUser().getName());
            }
        });
    }

    private Collection<ActivityInIteration> removeTasksBasingOnDate(Collection<ActivityInIteration> activities) {
        activities = Collections2.filter(activities, timePredicatesFactory.forTask());
        return activities;
    }


}
