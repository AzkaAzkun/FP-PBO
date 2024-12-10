import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {
    private final ActivityManager activityManager;
    private final DefaultListModel<String> listModel;
    private final JList<String> activityList;

    public MainFrame() {
        activityManager = new ActivityManager();
        listModel = new DefaultListModel<>();
        activityList = new JList<>(listModel);

        setTitle("To-Do List DaKaFi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        JScrollPane scrollPane = new JScrollPane(activityList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        addButton.addActionListener(e -> addActivity());
        updateButton.addActionListener(e -> updateActivity());
        deleteButton.addActionListener(e -> deleteActivity());

        refreshActivityList();
        setVisible(true);
    }

    private void addActivity() {
        String activity = JOptionPane.showInputDialog(this, "Enter new activity:");
        if (activity != null && !activity.isEmpty()) {
            activityManager.addActivity(activity);
            refreshActivityList();
        }
    }

    private void updateActivity() {
        int selectedIndex = activityList.getSelectedIndex();
        if (selectedIndex != -1) {
            String newActivity = JOptionPane.showInputDialog(this, "Update activity:",
                    activityManager.getActivity(selectedIndex).getDescription());
            if (newActivity != null && !newActivity.isEmpty()) {
                activityManager.updateActivity(selectedIndex, newActivity);
                refreshActivityList();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an activity to update.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteActivity() {
        int selectedIndex = activityList.getSelectedIndex();
        if (selectedIndex != -1) {
            activityManager.deleteActivity(selectedIndex);
            refreshActivityList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an activity to delete.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refreshActivityList() {
        listModel.clear();
        for (Activity activity : activityManager.getAllActivities()) {
            listModel.addElement(activity.toString());
        }
    }
}