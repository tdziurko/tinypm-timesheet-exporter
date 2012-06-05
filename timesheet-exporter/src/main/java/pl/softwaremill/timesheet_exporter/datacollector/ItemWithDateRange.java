package pl.softwaremill.timesheet_exporter.datacollector;

import java.util.Date;

public interface ItemWithDateRange {

    Date getStartDate();

    Date getEndDate();
}
