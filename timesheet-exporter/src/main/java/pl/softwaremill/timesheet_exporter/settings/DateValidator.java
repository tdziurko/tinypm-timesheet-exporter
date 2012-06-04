package pl.softwaremill.timesheet_exporter.settings;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator implements IParameterValidator {

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void validate(String name, String value) throws ParameterException {
        try {
            format.parse(value);
        } catch (ParseException e) {
            throw new ParameterException("Parameter " + name + " should be a date in format yyyy-MM-dd");
        }
    }
}
