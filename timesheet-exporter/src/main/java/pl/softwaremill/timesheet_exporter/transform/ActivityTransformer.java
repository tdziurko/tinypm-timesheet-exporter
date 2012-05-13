package pl.softwaremill.timesheet_exporter.transform;

import com.google.code.tinypmclient.User;
import com.google.code.tinypmclient.internal.Activity;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import java.util.Collection;

public class ActivityTransformer {

    private Collection<Activity> activities;

    public ActivityTransformer(Collection<Activity> activities) {
        this.activities = activities;
    }


    public Multimap<User, DataRow> transform() {

        TreeMultimap<User, DataRow> timesheets = TreeMultimap.create(new UserNameComparator(), new DataRowComparator());


        for (Activity activity : activities) {
//            DataRow dataRow = new DataRow(activity.getUser().getName(), )
//            timesheets.put(activity.)
        }

        return timesheets;
    }
}
