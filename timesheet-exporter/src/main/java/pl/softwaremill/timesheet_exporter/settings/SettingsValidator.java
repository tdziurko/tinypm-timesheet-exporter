package pl.softwaremill.timesheet_exporter.settings;

import com.beust.jcommander.ParameterException;

public class SettingsValidator {

    public void validate(ExporterSettings settings) {
        validateDateSettings(settings);
    }

    private void validateDateSettings(ExporterSettings settings) {
        if (settings.getMonth() == null && settings.getDateFrom() == null && settings.getDateTo() == null) {
            throw new ParameterException("Either -month or pair -dateFrom and -dateTo must be provided");
        }

        if (settings.getMonth() != null && settings.getDateFrom() != null && settings.getDateTo() != null) {
            throw new ParameterException("Either -month or pair -dateFrom and -dateTo must be provided");
        }

        if (settings.getMonth() == null && (settings.getDateFrom() != null && settings.getDateFrom() != null) == false) {
            throw new ParameterException("Both parameters -dateFrom and -dateTo must be provided");
        }

        if (settings.getMonth() == null && settings.getDateFrom().after(settings.getDateTo())) {
            throw new ParameterException("Value of -dateTo can not be before -dateFrom");
        }
    }
}