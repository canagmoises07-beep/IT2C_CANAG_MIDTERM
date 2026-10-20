package com.canteen.ui;

import com.canteen.dao.ScenarioDao;
import com.canteen.model.Item;

import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {
    private final ScenarioDao dao = new ScenarioDao();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice = 0;

        do {
            System.out.println("==================================================");
            System.out.println("          CANTEEN INVENTORY MANAGEMENT            ");
            System.out.println("==================================================");
            System.out.println("[1] Add New Food Item");
            System.out.println("[2] View Canteen Inventory");
            System.out.println("[3] Exit System");
            System.out.println("==================================================");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("\nInvalid input. Please enter a valid number.\n");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addNewItemFlow();
                    break;
                case 2:
                    viewInventoryFlow();
                    break;
                case 3:
                    System.out.println("\nExiting system... Thank you!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please select between 1 and 3.\n");
            }
        } while (choice != 3);
    }

    private void addNewItemFlow() {
        System.out.println("\n--- ADD NEW FOOD ITEM ---");
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine().trim();

        System.out.print("Enter Category (MEAL / SNACK / BEVERAGE): ");
        String category = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter Price: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid price format.");
            scanner.next();
        }
        double price = scanner.nextDouble();

        System.out.print("Enter Stock Quantity: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid stock format.");
            scanner.next();
        }
        int stock = scanner.nextInt();
        scanner.nextLine();

        Item newItem = new Item(itemName, category, price, stock);
        boolean isAdded = dao.addItem(newItem);

        if (isAdded) {
            System.out.println("\nSUCCESS: Food item added to inventory!\n");
        } else {
            System.out.println("\nFAILED: Could not add item.\n");
        }
    }

    private void viewInventoryFlow() {
        List<Item> items = dao.getAllItems();
        System.out.println("\n==================================================");
        System.out.println("            CANTEEN FOOD INVENTORY                ");
        System.out.println("==================================================");
        System.out.println("+-----+--------------------+------------+----------+------------+");
        System.out.printf("| %-3s | %-18s | %-10s | %-8s | %-10s |\n", "ID", "ITEM NAME", "CATEGORY", "PRICE", "STOCK");
        System.out.println("+-----+--------------------+------------+----------+------------+");

        for (Item item : items) {
            System.out.printf("| %-3d | %-18s | %-10s | %-8.2f | %-10d |\n",
                    item.getId(),
                    item.getItemName(),
                    item.getCategory(),
                    item.getPrice(),
                    item.getStock()
            );
        }
        System.out.println("+-----+--------------------+------------+----------+------------+");
        System.out.println("Total Records Displayed: " + items.size() + "\n");
    }
}