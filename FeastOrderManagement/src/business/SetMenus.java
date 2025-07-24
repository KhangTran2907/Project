/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SetMenu;
import java.util.ArrayList;

/**
 *
 * @author NH
 */
public class SetMenus extends HashMap<String, SetMenu>{
    private String pathFile;
    
    public SetMenus(){
        super();
        this.pathFile = "FeastMenu.csv";
             try {
            readFromFile();
        } catch (Exception e) {
            System.err.println("Warning: Could not load menu data. " + e.getMessage());
            // Initialize with empty menu instead of failing
        }
    
    }
    /**
     * Phương thức kiểm tra xem 1 setMenu có tồn tại trong danh sách hay không
     * @param menuId
     * @return 
     */
    
    public boolean isValidMenuId(String menuId){
        return this.containsKey(menuId);
    }
    
    public void showMenuList() {
        if (this.isEmpty()) {
            System.out.println("No menu items available.");
            return;
        }

        System.out.println("===== Feast Menu List =====");
        for (SetMenu menu : this.values()) {
            System.out.println(menu);
        }
    }
    
private void readFromFile() throws IOException {
    System.out.println("Reading from file: " + pathFile);
    File file = new File(pathFile);
    if (!file.exists()) throw new IOException("Menu file not found: " + pathFile);

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        boolean isFirstLine = true;
        int lineNum = 0;

        while ((line = br.readLine()) != null) {
            lineNum++;
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            if (line.trim().isEmpty()) continue;
            try {
                SetMenu menuItem = dataToObject(line);
                if (menuItem != null) this.put(menuItem.getMenuId(), menuItem);
                else System.err.println("Skipping invalid line " + lineNum + ": " + line);
            } catch (Exception e) {
                System.err.println("Error parsing line " + lineNum + ": " + e.getMessage());
            }
        }
    }
}

    
private SetMenu dataToObject(String line) {
    String[] parts = line.split(",");
    if (parts.length < 4) return null;

    try {
        String menuId = parts[0].trim();
        String menuName = parts[1].trim();
        double price = Double.parseDouble(parts[2].trim());  // Correct: price is now 3rd
        String ingredients = parts[3].trim();                // ingredients is 4th

        return new SetMenu(menuId, menuName, price, ingredients);
    } catch (NumberFormatException e) {
        System.err.println("Invalid price format: " + e.getMessage());
        return null;
    }
}

       private ArrayList<SetMenu> menuList = new ArrayList<>();

    public void loadFromCSV(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                SetMenu menu = dataToObject(line);
                if (menu != null) {
                    menuList.add(menu);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    
    public void displayMenu() {
        System.out.printf("%-10s %-20s %-10s %-30s\n", "Menu ID", "Menu Name", "Price", "Ingredients");
        System.out.println("-------------------------------------------------------------------------------");
        for (SetMenu menu : menuList) {
            System.out.printf("%-10s %-20s %-10.2f %-30s\n",
                    menu.getMenuId(), menu.getMenuName(), menu.getPrice(), menu.getIngredients());
        }
    }

        
          
}