package order;

import models.Order;
import utilities.JSONHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteOrder extends JFrame {
    private JTextField orderIdField;
    private JButton deleteButton;

    public DeleteOrder() {
        setTitle("Delete Order");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        // UI components
        JLabel orderIdLabel = new JLabel("Order ID:");
        orderIdField = new JTextField();
        deleteButton = new JButton("Delete Order");

        // Add components to the frame
        add(orderIdLabel);
        add(orderIdField);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOrder();
            }
        });
    }

    private void deleteOrder() {
        String orderId = orderIdField.getText();

        // Deleting order logic
        Order order = new Order(orderId, "Dummy Customer");
        JSONHandler.deleteOrder(order);

        JOptionPane.showMessageDialog(this, "Order deleted successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteOrder frame = new DeleteOrder();
            frame.setVisible(true);
        });
    }
}
