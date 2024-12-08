package menu;

import models.Menu;
import utilities.JSONHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateMenu extends JFrame {
    private JTextField nameField;
    private JTextField priceField;
    private JButton createButton;

    public CreateMenu() {
        setTitle("Create Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // UI components
        JLabel nameLabel = new JLabel("Menu Name:");
        nameField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        createButton = new JButton("Create Menu");

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(priceLabel);
        add(priceField);
        add(createButton);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMenu();
            }
        });
    }

    private void createMenu() {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());

        Menu menu = new Menu("MENU001", name, price);
        JSONHandler.addMenu(menu);

        JOptionPane.showMessageDialog(this, "Menu created successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreateMenu frame = new CreateMenu();
            frame.setVisible(true);
        });
    }
}
