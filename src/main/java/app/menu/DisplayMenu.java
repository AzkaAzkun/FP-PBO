package menu;

import models.Menu;
import utilities.JSONHandler;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DisplayMenu extends JFrame {
    private JTextArea menuListArea;

    public DisplayMenu() {
        setTitle("Display Menus");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        menuListArea = new JTextArea();
        menuListArea.setEditable(false);
        add(new JScrollPane(menuListArea), BorderLayout.CENTER);

        List<Menu> menus = JSONHandler.getAllMenuItems();
        for (Menu menu : menus) {
            menuListArea.append(menu.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DisplayMenu frame = new DisplayMenu();
            frame.setVisible(true);
        });
    }
}
