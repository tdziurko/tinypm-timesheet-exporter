package pl.softwaremill.timesheet_exporter.settings;

public enum OutputEnum {

    CONSOLE,
    PDF,
    XLS;

    public static OutputEnum fromString(String code) {

        for (OutputEnum output : OutputEnum.values()) {
            if (output.toString().equalsIgnoreCase(code)) {
                return output;
            }
        }

        return null;
    }
}
