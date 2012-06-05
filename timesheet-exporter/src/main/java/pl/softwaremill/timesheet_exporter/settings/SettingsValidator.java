package pl.softwaremill.timesheet_exporter.settings;

import com.beust.jcommander.ParameterException;
import com.google.common.annotations.VisibleForTesting;

public class SettingsValidator {

    public void validate(ExporterSettings settings) {
        validateProjectAndUserSettings(settings);
        validateDateSettings(settings);
    }

    @VisibleForTesting
    protected void validateDateSettings(ExporterSettings settings) {
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

    @VisibleForTesting
    protected void validateProjectAndUserSettings(ExporterSettings settings) {
        if (settings.getProjectCodes() == null && settings.getUser() == null) {
            throw new ParameterException("Either -project or -user must be provided");
        }
    }
}