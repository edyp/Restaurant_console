package com.company;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<Integer, MenuItem> menuItems = new HashMap();

    public void addMenuItem(String itemName, String itemDescription, Double itemPrice) {
        if (getItemByItemName(itemName) != null) {
            System.out.println(String.format(
                    "%s%s already exists in Menu, you can't add this item again.%s",
                    Main.ANSI_RED,
                    itemName,
                    Main.ANSI_RED
            ));
            return;
        }
        MenuItem menuItem = new MenuItem(itemName, itemDescription, itemPrice);
        Integer nextMenuItemPosition = menuLength() + 1;
        menuItems.put(nextMenuItemPosition, menuItem);

        System.out.println(String.format(
                "%s%s was added into menu on position: %s%s",
                Main.ANSI_GREEN,
                menuItem.getItemName(),
                nextMenuItemPosition,
                Main.ANSI_RESET
        ));
    }

    private Integer menuLength() {
        return this.menuItems.size();
    }

    public void printMenuItemPrice(String itemName) {
        MenuItem foundItem = getItemByItemName(itemName);
        if (foundItem != null) {
            System.out.println(String.format(
                    "%sThe %s costs %.2f PLN%s",
                    Main.ANSI_GREEN,
                    foundItem.getItemName(),
                    foundItem.getItemPrice(),
                    Main.ANSI_RESET
            ));
        } else {
            menuItemNotFoundMessage(itemName);
        }
    }

    private void menuItemNotFoundMessage(String itemName) {
        System.out.println(String.format(
                "%sSorry, we don't serve the \"%s\".%s",
                Main.ANSI_RED,
                itemName,
                Main.ANSI_RESET
        ));
    }

    private MenuItem getItemByItemName(String dishName) {
        for(Map.Entry<Integer, MenuItem> entry : this.menuItems.entrySet()) {
            if (entry.getValue().getItemName().equals(dishName)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void printMenu() {
        System.out.println(Main.ANSI_GREEN + " *** Restaurant Menu ***");
        for (Map.Entry<Integer, MenuItem> entry : this.menuItems.entrySet()) {
            System.out.println(String.format(
                    "%s. %s \"%s\" with price: %.2f PLN",
                    entry.getKey(),
                    entry.getValue().getItemName(),
                    entry.getValue().getItemDescription(),
                    entry.getValue().getItemPrice()
            ));
        }
        System.out.println(" *** End of Restaurant Menu ***\n" + Main.ANSI_RESET);
    }
}
