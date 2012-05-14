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
import org.joda.time.DateTime;
import pl.softwaremill.timesheet_exporter.ExporterSettings;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class TinyPMDataCollector {

    protected TinyPM tinyPM;
    private ExporterSettings settings;

    public TinyPMDataCollector(ExporterSettings settings) {

        this.settings = settings;
        tinyPM = new TinyPM(settings.getTinypmUrl(), settings.getAuthenticationToken());
    }

    public Collection<Activity> collectData() {

        List<Project> projects = loadRequestedProjects(settings.getProjectCodes());
        List<Iteration> allIterations = loadIterationsForProjects(projects);
        Collection<Iteration> iterationsInGivenMonth = filterIterationsUsingYearAndMonth(allIterations);

        return loadTimesheets(iterationsInGivenMonth);
    }

    protected List<Project> loadRequestedProjects(List<String> projectCodes) {
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

    private List<Iteration> loadIterationsForProjects(List<Project> projects) {
        List<Iteration> allIterations = Lists.newArrayList();
        for (Project project : projects) {
            allIterations.addAll(tinyPM.getAllIterations(project));
        }
        return allIterations;
    }

    private Collection<Iteration> filterIterationsUsingYearAndMonth(List<Iteration> allIterations) {
        return Collections2.filter(allIterations, new Predicate<Iteration>() {
            @Override
            public boolean apply(@Nullable Iteration iteration) {
                return iterationIsInAGivenYearAndMonth(iteration, settings.getYear(), settings.getMonth());
            }
        });
    }

    protected boolean iterationIsInAGivenYearAndMonth(Iteration iteration, int year, int month) {

        DateTime startDate = new DateTime(iteration.getStartDate());
        DateTime endDate = new DateTime(iteration.getCalculatedEndDate());

        return dateIsInGivenYearAndMonth(year, month, startDate) || dateIsInGivenYearAndMonth(year, month, endDate);
    }

    private boolean dateIsInGivenYearAndMonth(int year, int month, DateTime date) {
        return date.getYear() == year && date.getMonthOfYear() == month;
    }

    private Collection<Activity> loadTimesheets(Collection<Iteration> iterationsInGivenMonth) {
        List<Activity> activities = Lists.newArrayList();

        for (Iteration iteration : iterationsInGivenMonth) {
            activities.addAll(tinyPM.getTimesheetForIteration(iteration.getId()));
        }

        return filterTimesheets(activities);
    }

    private Collection<Activity> filterTimesheets(Collection<Activity> activities) {

        activities = removeTimesheetsFromDifferentMonths(activities);

        if (settings.getUser() != null) {
            return removeTimesheetsFromOtherUsers(activities, settings.getUser());
        } else {
            return activities;
        }
    }

    private Collection<Activity> removeTimesheetsFromOtherUsers(Collection<Activity> activities, final String user) {
        return Collections2.filter(activities, new Predicate<Activity>() {
            @Override
            public boolean apply(Activity activity) {
                return user.equals(activity.getUser().getName());
            }
        });
    }

    private Collection<Activity> removeTimesheetsFromDifferentMonths(Collection<Activity> activities) {
        activities = Collections2.filter(activities, new Predicate<Activity>() {
            @Override
            public boolean apply(Activity activity) {
                DateTime date = new DateTime(activity.getDate());
                return date.getYear() == settings.getYear() && date.getMonthOfYear() == settings.getMonth();
            }
        });
        return activities;
    }


}
