package order;

import models.*;
import utilities.JSONHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateOrder extends JFrame {
    private JTextField customerNameField;
    private JComboBox<String> menuComboBox;
    private JTextField quantityField;
    private JButton addItemButton, submitOrderButton;

    public CreateOrder() {
        setTitle("Create Order");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // UI components
        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField();
        JLabel menuLabel = new JLabel("Select Menu:");
        menuComboBox = new JComboBox<>();
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();
        addItemButton = new JButton("Add Item");
        submitOrderButton = new JButton("Submit Order");

        // Populate menu combo box
        for (Menu menu : JSONHandler.getAllMenuItems()) {
            menuComboBox.addItem(menu.getName());
        }

        // Add components to the frame
        add(customerNameLabel);
        add(customerNameField);
        add(menuLabel);
        add(menuComboBox);
        add(quantityLabel);
        add(quantityField);
        add(addItemButton);
        add(submitOrderButton);

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToOrder();
            }
        });

        submitOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitOrder();
            }
        });
    }

    private void addItemToOrder() {
        String customerName = customerNameField.getText();
        String menuName = (String) menuComboBox.getSelectedItem();
        int quantity = Integer.parseInt(quantityField.getText());

        Menu menu = JSONHandler.getAllMenuItems().stream()
                .filter(m -> m.getName().equals(menuName))
                .findFirst().orElse(null);
        
        if (menu != null) {
            Order order = new Order("ORD001", customerName);
            order.addItem(new OrderItem(menu.getIdMenu(), quantity));

            JOptionPane.showMessageDialog(this, "Item added to order: " + menu.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Menu not found!");
        }
    }

    private void submitOrder() {
        String customerName = customerNameField.getText();
        Order order = new Order("ORD001", customerName);
        // Add order items to the order

        JSONHandler.addOrder(order);
        JOptionPane.showMessageDialog(this, "Order submitted successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreateOrder frame = new CreateOrder();
            frame.setVisible(true);
        });
    }
}
