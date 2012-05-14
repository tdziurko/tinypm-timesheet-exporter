package pl.softwaremill.timesheet_exporter.printer;

import pl.softwaremill.timesheet_exporter.transform.DataRow;
import pl.softwaremill.timesheet_exporter.transform.DateUtil;

public class ToCsv {

    public static String toCSV(DataRow row, boolean withProjectName, boolean withUser) {
        StringBuilder builder = new StringBuilder();

        if (withUser) {
            builder = ToCsv.appendWithComma(builder, row.getUser());
        }

        if (withProjectName) {
            builder = ToCsv.appendWithComma(builder, row.getProject());
        }

        builder = ToCsv.appendWithComma(builder, row.getUserStory());
        builder = ToCsv.appendWithComma(builder, row.getTask());
        builder = ToCsv.appendWithComma(builder, DateUtil.formatDate(row.getDate()));
        builder = builder.append(row.getTimeSpent());

        return builder.toString();
    }

    public static String getColumns(boolean withProjectName, boolean withUser) {
        StringBuilder builder = new StringBuilder();

        if (withUser) {
            builder = appendWithComma(builder, "User");
        }

        if (withProjectName) {
            builder = appendWithComma(builder, "Project");
        }

        builder = appendWithComma(builder, "User story");
        builder = appendWithComma(builder, "Task");
        builder = appendWithComma(builder, "Date");
        builder = builder.append("Time spent");

        return builder.toString();
    }

    private static StringBuilder appendWithComma(StringBuilder builder, String text) {
        return builder
                .append(text)
                .append(",");
    }
}
