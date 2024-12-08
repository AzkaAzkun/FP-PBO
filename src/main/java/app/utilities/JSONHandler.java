package utilities;

import models.Order;
import models.Menu;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONHandler {
    private static final String ORDER_FILE = "database/order.json";
    private static final String MENU_FILE = "database/menu.json";
    private static Gson gson = new Gson();

    // Example methods for adding, deleting, and updating JSON data

    public static void addOrder(Order order) {
        List<Order> orders = getAllOrders();
        orders.add(order);
        saveOrders(orders);
    }

    public static List<Order> getAllOrders() {
        return loadFromFile(ORDER_FILE, new TypeToken<List<Order>>() {}.getType());
    }

    public static void deleteOrder(Order order) {
        List<Order> orders = getAllOrders();
        orders.removeIf(o -> o.getIdOrder().equals(order.getIdOrder()));
        saveOrders(orders);
    }

    public static void saveOrders(List<Order> orders) {
        saveToFile(orders, ORDER_FILE);
    }

    public static void addMenu(Menu menu) {
        List<Menu> menus = getAllMenuItems();
        menus.add(menu);
        saveMenuItems(menus);
    }

    public static List<Menu> getAllMenuItems() {
        return loadFromFile(MENU_FILE, new TypeToken<List<Menu>>() {}.getType());
    }

    public static void deleteMenu(Menu menu) {
        List<Menu> menus = getAllMenuItems();
        menus.removeIf(m -> m.getIdMenu().equals(menu.getIdMenu()));
        saveMenuItems(menus);
    }

    private static <T> List<T> loadFromFile(String filename, TypeToken<List<T>> typeToken) {
        // Placeholder for loading JSON data from a file (implement file read logic)
        return new ArrayList<>();
    }

    private static <T> void saveToFile(List<T> data, String filename) {
        // Placeholder for saving data to a file (implement file write logic)
    }

    private static void saveMenuItems(List<Menu> menus) {
        saveToFile(menus, MENU_FILE);
    }
}
