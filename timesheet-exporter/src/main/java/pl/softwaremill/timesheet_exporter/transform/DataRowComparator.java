package pl.softwaremill.timesheet_exporter.transform;

import java.util.Comparator;

public class DataRowComparator implements Comparator<DataRow> {


    @Override
    public int compare(DataRow row1, DataRow row2) {

        int userCompared = row1.getUser().compareTo(row2.getUser());

        if (userCompared != 0) {
            return userCompared;
        }

        int projectCompared = row1.getProject().compareTo(row2.getProject());

        if (projectCompared != 0) {
            return projectCompared;
        }

        int datesCompared = row1.getDate().compareTo(row2.getDate());

        return datesCompared;
    }
}
