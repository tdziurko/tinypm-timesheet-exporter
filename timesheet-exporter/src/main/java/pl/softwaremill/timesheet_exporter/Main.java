package pl.softwaremill.timesheet_exporter;

import com.google.code.tinypmclient.Project;
import com.google.code.tinypmclient.TinyPM;

public class Main {

    public static void main(String[] args) {

        String tinyPmUrl = args[0];
        String authenticationToken = args[1];
        String projectCode = args[2];
        int monthNumber = Integer.parseInt(args[3]);

        TinyPM tinyPM = new TinyPM(tinyPmUrl, authenticationToken);
        Project project = tinyPM.getProject(projectCode);

        DataCollector dataCollector = new DataCollector(tinyPM);
        dataCollector.collectDataFromProjectInMonth(project, monthNumber);
    }
}
