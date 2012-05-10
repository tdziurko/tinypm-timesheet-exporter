package pl.softwaremill.timesheet_exporter;

import com.google.code.tinypmclient.Iteration;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class DataCollectorShould {

    public DataCollector dataCollector = new DataCollector(null);

    @Test(dataProvider = "dataProvider")
    public void checkIfIterationIsInGivenMonth(int startMonth, int endMonth, int checkedMonthNumber, boolean expectedResult) {
        // given
        Iteration iteration = prepareIterationInMonths(startMonth, endMonth);

        // when
        boolean result = dataCollector.iterationIsInAGivenMonth(iteration, checkedMonthNumber);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @DataProvider(name = "dataProvider")
    public Object[][] provideData() {
        return new Object[][]{
                {1, 1, 1, true},
                {1, 10, 10, true},
                {1, 10, 11, false},
                {5, 5, 5, true},
                {5, 5, 6, false},
                {1, 12, 12, true},
        };
    }

    private Iteration prepareIterationInMonths(int startMonth, int endMonth) {

        DateTime startDate = new DateTime().withDate(2012, startMonth, 1);
        DateTime endDate = new DateTime().withDate(2012, endMonth, 1);

        int duration = Days.daysBetween(startDate, endDate).getDays();
        Iteration iteration = new Iteration();
        iteration.setStartDate(startDate.toDate());
        iteration.setDuration(duration + 1);

        return iteration;
    }


}
