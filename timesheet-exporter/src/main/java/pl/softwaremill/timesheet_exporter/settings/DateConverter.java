package pl.softwaremill.timesheet_exporter.settings;

import com.beust.jcommander.IStringConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements IStringConverter<Date> {

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String value) {
        try {
            return format.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
