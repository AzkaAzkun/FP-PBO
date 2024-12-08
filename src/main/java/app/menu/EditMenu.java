package menu;

import models.Menu;
import utilities.JSONHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMenu extends JFrame {
    private JTextField menuIdField;
    private JTextField nameField;
    private JTextField priceField;
    private JButton editButton;

    public EditMenu() {
        setTitle("Edit Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        // UI components
        JLabel menuIdLabel = new JLabel("Menu ID:");
        menuIdField = new JTextField();
        JLabel nameLabel = new JLabel("Menu Name:");
        nameField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        editButton = new JButton("Edit Menu");

        // Add components to the frame
        add(menuIdLabel);
        add(menuIdField);
        add(nameLabel);
        add(nameField);
        add(priceLabel);
        add(priceField);
        add(editButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editMenu();
            }
        });
    }

    private void editMenu() {
        String menuId = menuIdField.getText();
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());

        Menu menu = new Menu(menuId, name, price);
        JSONHandler.updateMenu(menu);

        JOptionPane.showMessageDialog(this, "Menu edited successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EditMenu frame = new EditMenu();
            frame.setVisible(true);
        });
    }
}
