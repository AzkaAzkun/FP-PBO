import java.util.ArrayList;

class ActivityManager {
    private final ArrayList<Activity> activities;

    public ActivityManager() {
        activities = new ArrayList<>();
    }

    public void addActivity(String description) {
        activities.add(new Activity(description));
    }

    public void updateActivity(int index, String newDescription) {
        activities.get(index).setDescription(newDescription);
    }

    public void deleteActivity(int index) {
        activities.remove(index);
    }

    public Activity getActivity(int index) {
        return activities.get(index);
    }

    public ArrayList<Activity> getAllActivities() {
        return new ArrayList<>(activities);
    }
}