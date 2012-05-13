package pl.softwaremill.timesheet_exporter;

import com.beust.jcommander.JCommander;
import com.google.code.tinypmclient.internal.Activity;
import pl.softwaremill.timesheet_exporter.transform.ActivityTransformer;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {

        ExporterSettings exporterSettings = new ExporterSettings();
        new JCommander(exporterSettings, args);

        Collection<Activity> activities = new TinyPMDataCollector(exporterSettings).collectData();

        new ActivityTransformer(activities).transform();
    }
}
