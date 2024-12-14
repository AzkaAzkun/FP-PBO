import java.util.Date;

public class Activity {
    private String name;
    private String description;
    private String deadline;
    private String status;
    private Date createdTime;

    public Activity(String name, String description, String deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = "Belum Selesai";
        this.createdTime = new Date();  // Set the created time to the current date and time
    }

    public Activity(String name, String description, String deadline, String status, Date createdTime) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.createdTime = createdTime;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void toggleStatus() {
        if (this.status.equals("Belum Selesai")) {
            this.status = "Selesai";
        } else {
            this.status = "Belum Selesai";
        }
    }
}
