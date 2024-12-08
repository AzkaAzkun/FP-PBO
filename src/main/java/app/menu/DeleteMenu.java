package menu;

import models.Menu;
import utilities.JSONHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteMenu extends JFrame {
    private JTextField menuIdField;
    private JButton deleteButton;

    public DeleteMenu() {
        setTitle("Delete Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        // UI components
        JLabel menuIdLabel = new JLabel("Menu ID:");
        menuIdField = new JTextField();
        deleteButton = new JButton("Delete Menu");

        // Add components to the frame
        add(menuIdLabel);
        add(menuIdField);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMenu();
            }
        });
    }

    private void deleteMenu() {
        String menuId = menuIdField.getText();

        Menu menu = new Menu(menuId, "Dummy Menu", 0.0);
        JSONHandler.deleteMenu(menu);

        JOptionPane.showMessageDialog(this, "Menu deleted successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteMenu frame = new DeleteMenu();
            frame.setVisible(true);
        });
    }
}
