import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileHandler {

    private static final String FILE_PATH = "activities.json";

    // Menyimpan daftar kegiatan ke file JSON
    public static void saveActivities(List<Activity> activities) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("[\n");

        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            jsonString.append("  {\n");
            jsonString.append("    \"name\": \"").append(activity.getName()).append("\",\n");
            jsonString.append("    \"description\": \"").append(activity.getDescription()).append("\",\n");
            jsonString.append("    \"deadline\": \"").append(activity.getDeadline()).append("\",\n");
            jsonString.append("    \"createdTime\": \"").append(activity.getCreatedTime().getTime()).append("\",\n");
            jsonString.append("    \"status\": \"").append(activity.getStatus()).append("\"\n");
            jsonString.append("  }");

            if (i < activities.size() - 1) {
                jsonString.append(",");
            }

            jsonString.append("\n");
        }

        jsonString.append("]");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(jsonString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Membaca kegiatan dari file JSON
    public static List<Activity> loadActivities() {
        List<Activity> activities = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            // Parse JSON secara manual
            String jsonString = content.toString();
            if (!jsonString.isEmpty() && jsonString.startsWith("[") && jsonString.endsWith("]")) {
                jsonString = jsonString.substring(1, jsonString.length() - 1); // Remove brackets

                String[] activityStrings = jsonString.split("},\\s*\\{");  // Split each activity data

                for (String activityString : activityStrings) {
                    activityString = activityString.replaceAll("[\\{\\}\\n\\r]", "").trim(); // Clean up

                    String name = extractValue(activityString, "name");
                    String description = extractValue(activityString, "description");
                    String deadline = extractValue(activityString, "deadline");
                    String createdTimeStr = extractValue(activityString, "createdTime");
                    String status = extractValue(activityString, "status");

                    Date createdTime = new Date(Long.parseLong(createdTimeStr));  // Convert from milliseconds

                    // Create Activity object with all data, including status and createdTime
                    activities.add(new Activity(name, description, deadline, status, createdTime));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return activities;
    }

    // Helper method to extract value of a field from the JSON-like string
    private static String extractValue(String jsonString, String key) {
        String keyPattern = "\"" + key + "\": \"";
        int start = jsonString.indexOf(keyPattern) + keyPattern.length();
        int end = jsonString.indexOf("\"", start);
        return start > -1 && end > -1 ? jsonString.substring(start, end) : "";
    }
}
