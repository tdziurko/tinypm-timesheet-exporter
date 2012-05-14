package pl.softwaremill.timesheet_exporter.transform;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

public class DateUtilShould {

    @Test
    public void formatDate() {

        // given
        Date date = new DateTime().withDate(2011, 12, 20).toDate();

        // when
        String dateString = DateUtil.formatDate(date);

        // then
        assertThat(dateString).isEqualTo("20-12-2011");
    }

    @Test
    public void formatDateForNull() {

        // given
        Date date = new DateTime().withDate(2011, 12, 20).toDate();

        // when
        String dateString = DateUtil.formatDate(null);

        // then
        assertThat(dateString).isEqualTo("");
    }
}
