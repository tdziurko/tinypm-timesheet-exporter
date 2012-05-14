package pl.softwaremill.timesheet_exporter;

import com.beust.jcommander.JCommander;
import pl.softwaremill.timesheet_exporter.datacollector.ActivityInIteration;
import pl.softwaremill.timesheet_exporter.datacollector.TinyPMDataCollector;
import pl.softwaremill.timesheet_exporter.settings.ExporterSettings;
import pl.softwaremill.timesheet_exporter.transform.DataTransfomer;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {

        ExporterSettings exporterSettings = new ExporterSettings();
        new JCommander(exporterSettings, args);

        Collection<ActivityInIteration> activities = new TinyPMDataCollector(exporterSettings).collectData();

        new DataTransfomer(activities).transform();
    }
}
