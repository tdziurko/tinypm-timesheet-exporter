package pl.softwaremill.timesheet_exporter.printer;

import com.google.code.tinypmclient.User;
import com.google.common.collect.Multimap;
import pl.softwaremill.timesheet_exporter.transform.DataRow;

public interface IReportPrinter {

    void printReport(Multimap<User, DataRow> reportData);


}
