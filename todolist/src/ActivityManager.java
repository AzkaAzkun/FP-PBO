import java.util.ArrayList;
import java.util.List;

public class ActivityManager {
    private final List<Activity> activities;

    public ActivityManager() {
        this.activities = new ArrayList<>();
    }

    public void addActivity(String name, String description, String deadline) {
        activities.add(new Activity(name, description, deadline));
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }    

    public void updateActivity(int index, String name, String description, String deadline) {
        Activity activity = activities.get(index);
        activity.setName(name);
        activity.setDescription(description);
        activity.setDeadline(deadline);
    }

    public void deleteActivity(int index) {
        activities.remove(index);
    }

    public void toggleStatus(int index) {
        Activity activity = activities.get(index);
        activity.toggleStatus();
    }

    public List<Activity> getAllActivities() {
        return activities;
    }
}
