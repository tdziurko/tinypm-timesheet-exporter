package pl.softwaremill.timesheet_exporter.printer;

import pl.softwaremill.timesheet_exporter.settings.ExporterSettings;

public class PrinterFactory {

    public static IReportPrinter createPrinter(ExporterSettings settings) {

        switch (settings.getOutput()) {
            case CONSOLE:
                return new ConsoleReportPrinter(settings);
            case PDF:
                return new PdfReportPrinter(settings);
            case CSV:
                return new CsvReportPrinter(settings);
            default:
                throw new RuntimeException("No output format specified!");

        }

    }
}
