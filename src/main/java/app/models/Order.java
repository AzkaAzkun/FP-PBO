package models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String idOrder;
    private String customerName;
    private List<OrderItem> items;

    public Order(String idOrder, String customerName) {
        this.idOrder = idOrder;
        this.customerName = customerName;
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    // Getters and Setters
    public String getIdOrder() {
        return idOrder;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order ID: " + idOrder + ", Customer: " + customerName + ", Items: " + items.size();
    }
}
