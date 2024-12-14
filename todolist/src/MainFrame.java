import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

class MainFrame extends JFrame {
    private final ActivityManager activityManager;
    private final DefaultTableModel tableModel;
    private final JTable activityTable;

    public MainFrame() {
        activityManager = new ActivityManager();
        setTitle("To-Do List DaKaFi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        // Table Setup
        String[] columnNames = {"No", "Nama Kegiatan", "Deskripsi", "Waktu Dibuat", "Deadline", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 3; // No & Waktu Dibuat tidak bisa diedit
            }
        };
        activityTable = new JTable(tableModel);
        activityTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(activityTable);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton toggleStatusButton = new JButton("Toggle Status");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(toggleStatusButton);

        // Add components
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Listeners
        addButton.addActionListener(e -> addActivity());
        updateButton.addActionListener(e -> updateActivity());
        deleteButton.addActionListener(e -> deleteActivity());
        toggleStatusButton.addActionListener(e -> toggleStatus());

        setVisible(true);

        // Load activities from file
        List<Activity> activities = FileHandler.loadActivities();
        for (Activity activity : activities) {
            activityManager.addActivity(activity);
        }
        refreshTable();
    }

    private void addActivity() {
        JTextField nameField = new JTextField();
        JTextField descField = new JTextField();
        
        // Create a checkbox for user to select if they want to enter a deadline
        JCheckBox deadlineCheckBox = new JCheckBox("Set Deadline");
        deadlineCheckBox.setSelected(false); // Default is unchecked
        
        // Create a spinner for date and time selection, initially disabled
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner deadlineSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(deadlineSpinner, "dd MMM yyyy HH:mm");
        deadlineSpinner.setEditor(editor);
        deadlineSpinner.setEnabled(false);  // Initially disabled
        
        // Add listener to enable/disable date spinner based on checkbox
        deadlineCheckBox.addItemListener(e -> {
            boolean selected = deadlineCheckBox.isSelected();
            deadlineSpinner.setEnabled(selected);
        });

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Nama Kegiatan:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Deskripsi:"));
        inputPanel.add(descField);
        inputPanel.add(deadlineCheckBox); // Checkbox to allow selecting deadline
        inputPanel.add(deadlineSpinner);  // Date spinner

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Add New Activity", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String desc = descField.getText().trim();
            String deadlineStr = ""; // Default empty

            // If user selected to set a deadline, then get the date
            if (deadlineCheckBox.isSelected()) {
                Date deadline = (Date) deadlineSpinner.getValue();
                if (deadline != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm, dd MMMM yyyy");
                    deadlineStr = sdf.format(deadline);
                }
            }

            if (!name.isEmpty()) {
                activityManager.addActivity(name, desc, deadlineStr);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Nama kegiatan harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        FileHandler.saveActivities(activityManager.getAllActivities());
    }

    private void updateActivity() {
        int selectedRow = activityTable.getSelectedRow();
        if (selectedRow != -1) {
            String name = (String) tableModel.getValueAt(selectedRow, 1);
            String desc = (String) tableModel.getValueAt(selectedRow, 2);
            String deadline = (String) tableModel.getValueAt(selectedRow, 4);

            JTextField nameField = new JTextField(name);
            JTextField descField = new JTextField(desc);

            // Set the existing deadline in the spinner
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm, dd MMMM yyyy");
            Date date = null;
            try {
                if (!deadline.isEmpty()) {
                    date = sdf.parse(deadline);
                }
            } catch (Exception e) {
                // Handle error gracefully
            }

            SpinnerDateModel dateModel = new SpinnerDateModel(date, null, null, java.util.Calendar.DAY_OF_MONTH);
            JSpinner deadlineSpinner = new JSpinner(dateModel);
            JSpinner.DateEditor editor = new JSpinner.DateEditor(deadlineSpinner, "dd MMM yyyy HH:mm");
            deadlineSpinner.setEditor(editor);

            JCheckBox deadlineCheckBox = new JCheckBox("Set Deadline", date != null);
            deadlineCheckBox.setSelected(date != null);  // Set if date is already present

            // Add listener to enable/disable date spinner based on checkbox
            deadlineCheckBox.addItemListener(e -> {
                boolean selected = deadlineCheckBox.isSelected();
                deadlineSpinner.setEnabled(selected);
            });

            JPanel inputPanel = new JPanel(new GridLayout(4, 2));
            inputPanel.add(new JLabel("Nama Kegiatan:"));
            inputPanel.add(nameField);
            inputPanel.add(new JLabel("Deskripsi:"));
            inputPanel.add(descField);
            inputPanel.add(deadlineCheckBox); // Checkbox to allow selecting deadline
            inputPanel.add(deadlineSpinner);  // Date spinner

            int result = JOptionPane.showConfirmDialog(this, inputPanel, "Update Activity", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String newDeadline = deadlineCheckBox.isSelected() ? sdf.format(deadlineSpinner.getValue()) : "";
                activityManager.updateActivity(selectedRow, nameField.getText().trim(), descField.getText().trim(), newDeadline);
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih kegiatan untuk diupdate.", "Error", JOptionPane.WARNING_MESSAGE);
        }

        FileHandler.saveActivities(activityManager.getAllActivities());
    }

    private void deleteActivity() {
        int selectedRow = activityTable.getSelectedRow();
        if (selectedRow != -1) {
            activityManager.deleteActivity(selectedRow);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih kegiatan untuk dihapus.", "Error", JOptionPane.WARNING_MESSAGE);
        }

        FileHandler.saveActivities(activityManager.getAllActivities());
    }

    private void toggleStatus() {
        int selectedRow = activityTable.getSelectedRow();
        if (selectedRow != -1) {
            activityManager.toggleStatus(selectedRow);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih kegiatan untuk mengubah status.", "Error", JOptionPane.WARNING_MESSAGE);
        }

        FileHandler.saveActivities(activityManager.getAllActivities());
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm, dd MMMM yyyy");
        for (int i = 0; i < activityManager.getAllActivities().size(); i++) {
            Activity activity = activityManager.getAllActivities().get(i);
            tableModel.addRow(new Object[] {
                    i + 1,
                    activity.getName(),
                    activity.getDescription(),
                    sdf.format(activity.getCreatedTime()),
                    activity.getDeadline(),
                    activity.getStatus()
            });
        }

        // Update status cell colors
        activityTable.getColumnModel().getColumn(5).setCellRenderer(new StatusCellRenderer());
    }

    private static class StatusCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if ("Selesai".equals(value)) {
                cell.setBackground(Color.GREEN);
                cell.setForeground(Color.BLACK);
            } else {
                cell.setBackground(Color.RED);
                cell.setForeground(Color.WHITE);
            }
            return cell;
        }
    }
}
