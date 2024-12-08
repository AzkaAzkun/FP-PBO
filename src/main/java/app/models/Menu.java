package models;

public class Menu {
    private String idMenu;
    private String name;
    private double price;

    public Menu(String idMenu, String name, double price) {
        this.idMenu = idMenu;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public String getIdMenu() {
        return idMenu;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Menu ID: " + idMenu + ", Name: " + name + ", Price: " + price;
    }
}
