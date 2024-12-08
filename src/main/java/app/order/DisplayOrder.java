package order;

import models.Order;
import utilities.JSONHandler;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DisplayOrder extends JFrame {
    private JTextArea orderListArea;

    public DisplayOrder() {
        setTitle("Display Orders");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        orderListArea = new JTextArea();
        orderListArea.setEditable(false);
        add(new JScrollPane(orderListArea), BorderLayout.CENTER);

        List<Order> orders = JSONHandler.getAllOrders();
        for (Order order : orders) {
            orderListArea.append(order.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DisplayOrder frame = new DisplayOrder();
            frame.setVisible(true);
        });
    }
}
