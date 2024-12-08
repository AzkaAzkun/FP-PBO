package models;

public class OrderItem {
    private String idMenu;
    private int quantity;

    public OrderItem(String idMenu, int quantity) {
        this.idMenu = idMenu;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getIdMenu() {
        return idMenu;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Menu ID: " + idMenu + ", Quantity: " + quantity;
    }
}
