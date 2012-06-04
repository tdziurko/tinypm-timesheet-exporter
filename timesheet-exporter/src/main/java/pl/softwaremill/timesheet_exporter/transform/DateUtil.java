package pl.softwaremill.timesheet_exporter.transform;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(Date date) {

        if (date == null) {
            return "";
        }
        return formatter.format(date);
    }

    public static Date createDate(int year, int month, int day) {
        return new DateTime().withDate(year, month, day).toDate();
    }
}
