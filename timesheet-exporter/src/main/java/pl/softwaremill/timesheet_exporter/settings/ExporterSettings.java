package pl.softwaremill.timesheet_exporter.settings;

import com.beust.jcommander.Parameter;
import com.google.common.annotations.VisibleForTesting;

import java.util.Date;
import java.util.List;

public class ExporterSettings {

    @Parameter(names = "-url", description = "Tiny PM server address", required = true)
    private String tinypmUrl;

    @Parameter(names = "-token", description = "Authentication token", required = true)
    private String authenticationToken;

    @Parameter(names = "-project", description = "Codes of projects to be included in timesheet report")
    private List<String> projectCodes;

    @Parameter(names = "-year", description = "Year of timesheet")
    private Integer year;

    @Parameter(names = "-month", description = "Number of month (1-12) for timesheet", validateWith = MonthValidator.class)
    private Integer month;

    @Parameter(names = "-dateFrom", description = "Start date of the report, in yyyy-MM-dd format",
            validateWith = DateValidator.class, converter = DateConverter.class)
    private Date dateFrom;

    @Parameter(names = "-dateTo", description = "End date of the report, in yyyy-MM-dd format",
            validateWith = DateValidator.class, converter = DateConverter.class)
    private Date dateTo;

    @Parameter(names = "-user", description = "User's timesheet", required = false)
    private String user;

    @Parameter(names = "-output", description = "Report output format", required = true, converter = OutputConverter.class)
    private OutputEnum output;


    public String getTinypmUrl() {
        return tinypmUrl;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public List<String> getProjectCodes() {
        return projectCodes;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public String getUser() {
        return user;
    }

    public OutputEnum getOutput() {
        return output;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    @VisibleForTesting
    protected void setMonth(Integer month) {
        this.month = month;
    }

    @VisibleForTesting
    protected void setYear(Integer year) {
        this.year = year;
    }

    @VisibleForTesting
    protected void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @VisibleForTesting
    protected void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @VisibleForTesting
    protected void setProjectCodes(List<String> projectCodes) {
        this.projectCodes = projectCodes;
    }

    @VisibleForTesting
    protected void setUser(String user) {
        this.user = user;
    }
}
