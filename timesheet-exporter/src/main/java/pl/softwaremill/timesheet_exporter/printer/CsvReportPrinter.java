package pl.softwaremill.timesheet_exporter.printer;

import com.google.code.tinypmclient.User;
import com.google.common.collect.Multimap;
import pl.softwaremill.timesheet_exporter.settings.ExporterSettings;
import pl.softwaremill.timesheet_exporter.transform.DataRow;

public class CsvReportPrinter implements IReportPrinter {

    private ExporterSettings settings;

    public CsvReportPrinter(ExporterSettings settings) {

        this.settings = settings;
    }

    @Override
    public void printReport(Multimap<User, DataRow> reportData) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
