package app;

import menu.CreateMenu;
import menu.EditMenu;
import menu.DeleteMenu;
import menu.DisplayMenu;
import order.CreateOrder;
import order.EditOrder;
import order.DeleteOrder;
import order.DisplayOrder;
import models.Order;
import models.OrderItem;
import models.Menu;
import utilities.JSONHandler;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Set Look and Feel to ensure the UI looks good across platforms
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Show a simple main menu to choose actions
        String[] options = {
            "Add Menu", "Edit Menu", "Delete Menu", "Display Menus",
            "Add Order", "Edit Order", "Delete Order", "Display Orders", "Exit"
        };
        
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, 
                    "Choose an option:", 
                    "Menu Management", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE, 
                    null, 
                    options, 
                    options[0]);

            if (choice == 0) {
                // Open the CreateMenu frame
                SwingUtilities.invokeLater(() -> new CreateMenu().setVisible(true));
            } else if (choice == 1) {
                // Open the EditMenu frame
                SwingUtilities.invokeLater(() -> new EditMenu().setVisible(true));
            } else if (choice == 2) {
                // Open the DeleteMenu frame
                SwingUtilities.invokeLater(() -> new DeleteMenu().setVisible(true));
            } else if (choice == 3) {
                // Open the DisplayMenu frame
                SwingUtilities.invokeLater(() -> new DisplayMenu().setVisible(true));
            } else if (choice == 4) {
                // Open the CreateOrder frame
                SwingUtilities.invokeLater(() -> new CreateOrder().setVisible(true));
            } else if (choice == 5) {
                // Open the EditOrder frame
                SwingUtilities.invokeLater(() -> new EditOrder().setVisible(true));
            } else if (choice == 6) {
                // Open the DeleteOrder frame
                SwingUtilities.invokeLater(() -> new DeleteOrder().setVisible(true));
            } else if (choice == 7) {
                // Open the DisplayOrder frame
                SwingUtilities.invokeLater(() -> new DisplayOrder().setVisible(true));
            } else if (choice == 8) {
                // Exit the application
                System.exit(0);
            }
        }
    }
}
