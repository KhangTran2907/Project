/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1;

/**
 *
 * @author MSII
 */
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        ItemList obj = new ItemList();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add a new Vase");
            System.out.println("2. Add a new Statue");
            System.out.println("3. Add a new Painting");
            System.out.println("4. Display all items");
            System.out.println("5. Find items by the creator");
            System.out.println("6. Update item by its index");
            System.out.println("7. Remove item by its index");
            System.out.println("8. Display list of Vase items");
            System.out.println("9. Sort items by value (ascending)");
            System.out.println("10. Exit");
            System.out.print("Input your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            Item tmp;
            switch (choice) {
                case 1:
                    tmp = new Vase();
                    tmp.input();
                    if (obj.addItem(tmp)) {
                        System.out.println("Vase added successfully.");
                    }
                    break;
                case 2:
                    tmp = new Statue();
                    tmp.input();
                    if (obj.addItem(tmp)) {
                        System.out.println("Statue added successfully.");
                    }
                    break;
                case 3:
                    tmp = new Painting();
                    tmp.input();
                    if (obj.addItem(tmp)) {
                        System.out.println("Painting added successfully.");
                    }
                    break;
                case 4:
                    obj.displayAll();
                    break;
                case 5:
                    System.out.print("Enter creator's name to search: ");
                    String creator = sc.nextLine();
                    obj.findItemIndex(creator);
                    break;
                case 6:
                    System.out.print("Enter index to update: ");
                    int updateIndex = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    obj.updateItem(updateIndex);
                    break;
                case 7:
                    System.out.print("Enter index to remove: ");
                    int removeIndex = sc.nextInt();
                    if (obj.removeItem(removeIndex)) {
                        System.out.println("Item removed successfully.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;
                case 8:
                    obj.displayItemsByType();
                    break;
                case 9:
                    obj.sortItems();
                    System.out.println("Items sorted by value.");
                    break;
                case 10:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select again.");
            }
        } while (choice != 10);

        sc.close();
    }
}