import java.text.SimpleDateFormat;
import java.util.Date;

class Activity {
    private String description;
    private String timestamp;

    public Activity(String description) {
        this.description = description;
        updateTimestamp();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        updateTimestamp();
    }

    private void updateTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.timestamp = sdf.format(new Date());
    }

    @Override
    public String toString() {
        return description + " (Last updated: " + timestamp + ")";
    }
}