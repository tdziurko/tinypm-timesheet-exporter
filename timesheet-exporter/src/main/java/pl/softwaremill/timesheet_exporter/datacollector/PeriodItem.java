package pl.softwaremill.timesheet_exporter.datacollector;

import java.util.Date;

public interface PeriodItem {

    Date getStartDate();

    Date getEndDate();
}
