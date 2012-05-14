package pl.softwaremill.timesheet_exporter.settings;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

public class OutputConverter implements IStringConverter<OutputEnum> {

    @Override
    public OutputEnum convert(String value) {
        OutputEnum convertedValue = OutputEnum.fromString(value);

        if (convertedValue == null) {
            throw new ParameterException("Value " + value + "can not be converted to OutputEnum. " +
                    "Available values are: console, pdf, xls.");
        }
        return convertedValue;
    }
}
