package pl.softwaremill.timesheet_exporter;

import com.google.code.tinypmclient.Iteration;
import com.google.code.tinypmclient.Project;
import com.google.code.tinypmclient.TinyPM;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class DataCollector {

    private final TinyPM tinyPM;

    public DataCollector(TinyPM tinyPM) {
        this.tinyPM = tinyPM;
    }

    public void collectDataFromProjectInMonth(Project project, final int monthNumber) {

        List<Iteration> allIterations = tinyPM.getAllIterations(project);
        Collection<Iteration> iterationsInGivenMonth = Collections2.filter(allIterations, new Predicate<Iteration>() {
            @Override
            public boolean apply(@Nullable Iteration iteration) {
                return iterationIsInAGivenMonth(iteration, monthNumber);
            }
        });

        for (Iteration iteration : iterationsInGivenMonth) {

        }


    }

    protected boolean iterationIsInAGivenMonth(Iteration iteration, int monthNumber) {

        DateTime startDate = new DateTime(iteration.getStartDate());
        DateTime endDate = new DateTime(iteration.getCalculatedEndDate());

        return startDate.getMonthOfYear() == monthNumber || endDate.getMonthOfYear() == monthNumber;
    }
}
