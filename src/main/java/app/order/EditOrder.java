package order;

import models.Order;
import models.OrderItem;
import utilities.JSONHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditOrder extends JFrame {
    private JTextField orderIdField;
    private JTextField customerNameField;
    private JTextField quantityField;
    private JButton editButton;

    public EditOrder() {
        setTitle("Edit Order");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        // UI components
        JLabel orderIdLabel = new JLabel("Order ID:");
        orderIdField = new JTextField();
        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();
        editButton = new JButton("Edit Order");

        // Add components to the frame
        add(orderIdLabel);
        add(orderIdField);
        add(customerNameLabel);
        add(customerNameField);
        add(quantityLabel);
        add(quantityField);
        add(editButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editOrder();
            }
        });
    }

    private void editOrder() {
        String orderId = orderIdField.getText();
        String customerName = customerNameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());

        // Update order logic (simplified for example)
        Order order = new Order(orderId, customerName);
        order.addItem(new OrderItem("MENU001", quantity));
        JSONHandler.updateOrder(order);

        JOptionPane.showMessageDialog(this, "Order edited successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EditOrder frame = new EditOrder();
            frame.setVisible(true);
        });
    }
}
