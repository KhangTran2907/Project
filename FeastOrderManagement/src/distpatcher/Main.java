/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distpatcher;

import business.Customers;
import business.Orders;
import business.SetMenus;
import java.util.List;
import model.Customer;
import tools.Acceptable;
import tools.Inputter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import model.Order;
import model.SetMenu;



/**
 *
 * @author MSII
 */
public class Main {
        private static Customers dskh = new Customers();
        private static Orders ors = new Orders();
        private static SetMenus td = new SetMenus();
        private static Inputter nl = new Inputter();
    public static void main(String[] args){
        int choice = 0;
     do{
         choice = nl.inputInt("1.Register Customer.\n"
                            + "2.Update customer information.\n"
                            + "3.Search for customer information by name.\n"
                            + "4.Display feast menus.\n"
                            + "5.Place a feast order.\n"
                            + "6.Update order information.\n"
                            + "7.Save data to file.\n"
                            + "8.Display Customer or Order lists.",
                            1, 8);
                                   
         switch(choice){
             case 1:    
                
                dskh.addnew(nl.InputCustomer());
                 break;
             case 2:
                 
    String idToUpdate = nl.inputString("Enter customer ID to update: ");
    Customer existing = dskh.searchByID(idToUpdate);
    if (existing == null) {
        System.out.println("Customer ID not found.");
    } else {
        System.out.println("Existing customer info: ");
        System.out.println(existing);

        // Ask for new details
        String newName = nl.inputString("Enter new name [" + existing.getName() + "]: ", false);
        String newPhone = nl.inputString("Enter new phone [" + existing.getPhoneNumber()+ "]: ", false);
        String newEmail = nl.inputString("Enter new email [" + existing.getEmail() + "]: ", false);

        // Update only if not empty
        if (!newName.isEmpty()) existing.setName(newName);
        if (!newPhone.isEmpty()) existing.setPhoneNumber(newPhone);
        if (!newEmail.isEmpty()) existing.setEmail(newEmail);

        if (dskh.update(existing)) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Update failed.");
        }
    }
    break;

                 
             case 3:
                 String name = nl.inputString("Enter name to search: ");
                dskh.showAll(dskh.filterByName(name));
                break;
                 
             case 4:
                 td.displayMenu();
                 break;
             case 5:
                 placeFeastOrder();
                 break;
             case 6:
                 System.out.println("aaaaa");
                 break;
             case 7:
                 System.out.println("aaaaa");
                 break;
             case 8:

                 System.out.println("1. Display all customers");
                 System.out.println("2. Display all orders");
                 int sub = nl.inputInt("Choose option: ", 1, 2);
                 if (sub == 1) {
                     dskh.showAll();
                 } else {
                     ors.showAll();
                 }

                 break;
         }
             
         }while(choice > 0 && choice < 9);
         
        }
    
        private static void placeFeastOrder() {
        String cusId = nl.inputString("Enter customer ID: ");
        Customer customer = dskh.searchByID(cusId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        td.showMenuList();

        String menuId = nl.inputString("Enter menu ID: ");
        if (!td.containsKey(menuId)) {
            System.out.println("Invalid menu ID.");
            return;
        }

        int tables = nl.inputInt("Enter number of tables: ", 1, 500);
        String province = nl.inputString("Enter province: ");
        Date eventDate = nl.inputDate("Enter event date (dd/MM/yyyy): ");

        Order newOrder = new Order(null, cusId, province, menuId, tables, eventDate);
        if (!ors.isDuplicate(newOrder)) {
            ors.addNew(newOrder);
            System.out.println("Order placed successfully:");

            // Print order details
            System.out.println("Customer ID   : " + newOrder.getCustomerId());
            System.out.println("Province      : " + newOrder.getProvince());
            System.out.println("Event Date    : " + new SimpleDateFormat("dd/MM/yyyy").format(newOrder.getEventDate()));
            System.out.println("Number of Tables: " + newOrder.getNumOfTables());

            // Lookup and print SetMenu details
            SetMenu menu = td.get(menuId);
            if (menu != null) {
                System.out.println("Menu Details:");
                System.out.println("Code        : " + menu.getMenuId());
                System.out.println("Name        : " + menu.getMenuName());
                System.out.println("Price       : " + menu.getPrice());
                System.out.println("Ingredients : " + menu.getIngredients());
            } else {
                System.out.println("Menu details not found.");
            }
        } else {
            System.out.println("Duplicate order (same customer, menu, and date). Not added.");
        }

    }

        }
    
     

    

