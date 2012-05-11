package pl.softwaremill.timesheet_exporter;

import com.beust.jcommander.JCommander;
import com.google.code.tinypmclient.internal.Activity;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {

        ExporterSettings exporterSettings = new ExporterSettings();

        new JCommander(exporterSettings, args);

        DataCollector dataCollector = new DataCollector(exporterSettings);
        Collection<Activity> activities = dataCollector.collectData();
    }
}
