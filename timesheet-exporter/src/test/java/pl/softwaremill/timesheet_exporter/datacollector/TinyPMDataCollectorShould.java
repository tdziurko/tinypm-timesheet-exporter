package pl.softwaremill.timesheet_exporter.datacollector;

import com.google.code.tinypmclient.Project;
import com.google.code.tinypmclient.TinyPM;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.softwaremill.timesheet_exporter.settings.ExporterSettings;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TinyPMDataCollectorShould {

    private TinyPMDataCollector dataCollector;
    private TinyPM tinyPmMock;

    @BeforeMethod
    public void init() {
        tinyPmMock = mock(TinyPM.class);

        dataCollector = new TinyPMDataCollector(new ExporterSettings());
        dataCollector.tinyPM = tinyPmMock;
    }

    @Test
    public void loadValidProjectCodes() {

        // given
        String code1 = "Project1";
        String code2 = "Project2";
        List<String> projectCodes = Arrays.asList(code1, code2);

        when(tinyPmMock.getAllProjects()).thenReturn(Arrays.asList(projectWithCode(code1), projectWithCode(code2)));
        when(tinyPmMock.getProject(code1)).thenReturn(projectWithCode(code1));
        when(tinyPmMock.getProject(code2)).thenReturn(projectWithCode(code2));

        // when
        List<Project> projects = dataCollector.loadRequestedProjects(projectCodes);

        // then
        assertThat(projects).onProperty("code").containsExactly(code1, code2);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Some projects were not found using given project codes. Available project codes are: \n" +
                    "Project1, Project2")
    public void failWhenLoadingOneInvalidProjectCode() {

        // given
        String code1 = "Project1";
        String code2 = "Project2";
        String invalidCode = "Invalid";

        List<String> projectCodes = Arrays.asList(code1, invalidCode);

        when(tinyPmMock.getAllProjects()).thenReturn(Arrays.asList(projectWithCode(code1), projectWithCode(code2)));
        when(tinyPmMock.getProject(code1)).thenReturn(projectWithCode(code1));
        when(tinyPmMock.getProject(code2)).thenReturn(projectWithCode(code2));

        // when
        List<Project> projects = dataCollector.loadRequestedProjects(projectCodes);
    }

    private Project projectWithCode(String code) {
        Project project = new Project();
        project.setCode(code);
        return project;
    }


}
