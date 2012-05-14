package pl.softwaremill.timesheet_exporter.printer;

import com.google.code.tinypmclient.User;
import com.google.common.collect.Multimap;
import pl.softwaremill.timesheet_exporter.settings.ExporterSettings;
import pl.softwaremill.timesheet_exporter.transform.DataRow;

import java.util.Set;

public class ConsoleReportPrinter implements IReportPrinter {

    private ExporterSettings settings;

    public ConsoleReportPrinter(ExporterSettings settings) {
        this.settings = settings;
    }

    @Override
    public void printReport(Multimap<User, DataRow> reportData) {

        System.out.println("Timesheet Report");
        boolean printProjectName = settings.getProjectCodes().size() > 1;
        boolean printUserName = settings.getUser() != null;

        System.out.println(ToCsv.getColumns(printProjectName, printUserName));

        Set<User> users = reportData.keySet();

        for (User user : users) {
            float sumPerUser = 0f;

            for (DataRow dataRow : reportData.values()) {
                System.out.println(ToCsv.toCSV(dataRow, printProjectName, printUserName));
                sumPerUser += dataRow.getTimeSpent();
            }

            System.out.println("Sum = " + sumPerUser);
        }


    }
}
