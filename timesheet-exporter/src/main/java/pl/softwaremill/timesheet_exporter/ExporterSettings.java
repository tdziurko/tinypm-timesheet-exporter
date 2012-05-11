package pl.softwaremill.timesheet_exporter;

import com.beust.jcommander.Parameter;

import java.util.List;

public class ExporterSettings {

    @Parameter(names = "-url", description = "Tiny PM server address", required = true)
    private String tinypmUrl;

    @Parameter(names = "-token", description = "Authentication token", required = true)
    private String authenticationToken;

    @Parameter(names = "-project", description = "Codes of projects to be included in timesheet report", required = true)
    private List<String> projectCodes;

    @Parameter(names = "-year", description = "Year of timesheet", required = true)
    private int year;

    @Parameter(names = "-month", description = "Number of month (1-12) for timesheet", required = true)
    private int month;

    @Parameter(names = "-user", description = "User's timesheet", required = false)
    private String user;


    public String getTinypmUrl() {
        return tinypmUrl;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public List<String> getProjectCodes() {
        return projectCodes;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getUser() {
        return user;
    }
}
