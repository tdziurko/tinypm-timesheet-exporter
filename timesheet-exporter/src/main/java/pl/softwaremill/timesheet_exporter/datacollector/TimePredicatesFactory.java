package pl.softwaremill.timesheet_exporter.datacollector;

import com.google.common.base.Predicate;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import pl.softwaremill.timesheet_exporter.settings.ExporterSettings;

import javax.annotation.Nullable;
import java.util.Date;

public class TimePredicatesFactory {

    private ExporterSettings settings;

    public TimePredicatesFactory(ExporterSettings settings) {
        this.settings = settings;
    }

    public Predicate<IterationInProject> forIteration() {

        if (settings.getMonth() != null) {
            return new Predicate<IterationInProject>() {
                @Override
                public boolean apply(@Nullable IterationInProject iteration) {
                    return iterationIsInAGivenYearAndMonth(iteration, settings.getYear(), settings.getMonth());
                }
            };
        } else {
            return new Predicate<IterationInProject>() {
                @Override
                public boolean apply(@Nullable IterationInProject iteration) {
                    return itemOverlapsGivenDateRange(iteration, settings.getDateFrom(), settings.getDateTo());
                }
            };
        }

    }

    public Predicate<ActivityInIteration> forTask() {
        if (settings.getMonth() != null) {
            return new Predicate<ActivityInIteration>() {
                @Override
                public boolean apply(ActivityInIteration activity) {
                    DateTime date = new DateTime(activity.getDate());
                    return date.getYear() == settings.getYear() && date.getMonthOfYear() == settings.getMonth();
                }
            };
        } else {
            return new Predicate<ActivityInIteration>() {
                @Override
                public boolean apply(@Nullable ActivityInIteration activity) {
                    return itemOverlapsGivenDateRange(activity, settings.getDateFrom(), settings.getDateTo());
                }
            };
        }
    }

    protected boolean iterationIsInAGivenYearAndMonth(IterationInProject iteration, int year, int month) {

        DateTime startDate = new DateTime(iteration.getStartDate());
        DateTime endDate = new DateTime(iteration.getEndDate());

        return dateIsInGivenYearAndMonth(year, month, startDate) || dateIsInGivenYearAndMonth(year, month, endDate);
    }

    private boolean dateIsInGivenYearAndMonth(int year, int month, DateTime date) {
        return date.getYear() == year && date.getMonthOfYear() == month;
    }

    protected boolean itemOverlapsGivenDateRange(ItemWithDateRange itemWithDateRange, Date dateFrom, Date dateTo) {
        DateTime startDate = new DateTime(itemWithDateRange.getStartDate());
        DateTime endDate = new DateTime(itemWithDateRange.getEndDate()).withTime(23, 59, 59, 999);

        Interval iterationInterval = new Interval(startDate, endDate);

        Interval filterInterval = new Interval(new DateTime(dateFrom), new DateTime(dateTo).withTime(23, 59, 59, 999));

        return filterInterval.overlaps(iterationInterval);


    }
}
