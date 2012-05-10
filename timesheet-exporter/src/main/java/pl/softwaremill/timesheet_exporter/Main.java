package pl.softwaremill.timesheet_exporter;

import com.google.code.tinypmclient.Project;
import com.google.code.tinypmclient.TinyPM;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String tinyPmUrl = args[0];
        String authenticationToken = args[1];

        TinyPM tinyPM = new TinyPM(tinyPmUrl, authenticationToken);
        List<Project> projects = tinyPM.getAllProjects();

        System.out.println("Number of projects = " + projects.size());
    }
}
