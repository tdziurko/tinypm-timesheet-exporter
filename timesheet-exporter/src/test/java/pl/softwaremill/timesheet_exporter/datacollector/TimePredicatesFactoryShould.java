package pl.softwaremill.timesheet_exporter.datacollector;

import com.google.code.tinypmclient.Iteration;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.softwaremill.timesheet_exporter.transform.DateUtil;

import static org.fest.assertions.Assertions.assertThat;

public class TimePredicatesFactoryShould {

    TimePredicatesFactory predicatesFactory = new TimePredicatesFactory(null);

    @Test(dataProvider = "dateRangesProvider")
    public void checkIfIterationIsInGivenMonth(int startYear, int startMonth, int endyear, int endMonth, int yearToCheck, int monthToCheck, boolean expectedResult) {
        // given
        IterationInProject iteration = prepareIterationInMonths(startYear, startMonth, endyear, endMonth);

        // when
        boolean result = predicatesFactory.iterationIsInAGivenYearAndMonth(iteration, yearToCheck, monthToCheck);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private IterationInProject prepareIterationInMonths(int startYear, int startMonth, int endYear, int endMonth) {

        DateTime startDate = new DateTime().withDate(startYear, startMonth, 1);
        DateTime endDate = new DateTime().withDate(endYear, endMonth, 1);

        int duration = Days.daysBetween(startDate, endDate).getDays();
        IterationInProject iteration = new IterationInProject(new Iteration(), null);
        iteration.setStartDate(startDate.toDate());
        iteration.setDuration(duration + 1);

        return iteration;
    }

    @DataProvider(name = "dateRangesProvider")
    public Object[][] provideData() {
        return new Object[][]{
                {2012, 1, 2012, 1, 2012, 1, true},
                {2012, 1, 2012, 10, 2012, 10, true},
                {2012, 1, 2012, 10, 2012, 11, false},
                {2012, 5, 2012, 5, 2012, 5, true},
                {2012, 5, 2012, 5, 2012, 6, false},
                {2012, 1, 2012, 12, 2012, 12, true},
                {2012, 1, 2012, 12, 2012, 12, true},
                {2012, 1, 2013, 11, 2013, 12, false},
                {2012, 2, 2012, 12, 2012, 1, false},
        };
    }

    @Test(dataProvider = "timePeriodsProvider")
    public void checkIfPeriodItemIsInGivenDateRange(int iterationYear, int iterationMonth, int iterationDay, int iterationDuration,
                                                    int yearFrom, int monthFrom, int dayFrom, int yearTo, int monthTo, int dayTo, boolean expectedResult) {

        // given
        IterationInProject iteration = new IterationInProject(new Iteration(), null);
        iteration.setStartDate(DateUtil.createDate(iterationYear, iterationMonth, iterationDay));
        iteration.setDuration(iterationDuration);

        // when
        boolean result = predicatesFactory.periodItemOverlapsGivenDateRange(iteration,
                DateUtil.createDate(yearFrom, monthFrom, dayFrom), DateUtil.createDate(yearTo, monthTo, dayTo));

        // then
        assertThat(result).isEqualTo(expectedResult);
    }


    @DataProvider(name = "timePeriodsProvider")
    public Object[][] provideTimePeriods() {
        return new Object[][]{
                {2012, 10, 5, 25, 2012, 10, 10, 2012, 10, 17, true},
                {2012, 10, 5, 3, 2012, 10, 2, 2012, 10, 5, true},
                {2012, 10, 5, 3, 2012, 10, 2, 2012, 10, 4, false},
                {2012, 10, 5, 3, 2012, 10, 10, 2012, 10, 20, false},
                {2012, 10, 5, 3, 2012, 10, 2, 2012, 10, 17, true},
                {2012, 10, 5, 1, 2012, 10, 5, 2012, 10, 5, true},
                {2012, 10, 5, 1, 2012, 10, 6, 2012, 10, 6, false},
        };
    }
}
