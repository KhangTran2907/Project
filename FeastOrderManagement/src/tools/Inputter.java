/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import model.Customer;
import tools.Acceptable;


/**
 *
 * @author NH
 */
public class Inputter {
    private Scanner ndl;
    private Scanner sc = new Scanner(System.in);
    
    public Inputter(){
        this.ndl = new Scanner(System.in);
    }
    
    public String inputString(String msg) {
        String input;
        do {
            System.out.print(msg);
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    // ✅ New method that allows empty input
    public String inputString(String msg, boolean allowEmpty) {
        String input;
        while (true) {
            System.out.print(msg);
            input = sc.nextLine().trim();

            if (allowEmpty || !input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty.");
        }
    }
    public int inputInt(String mess){
        return inputInt(mess, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
public int inputInt(String mess, int min, int max) {
    int result = 0;
    boolean valid = false;
    while(!valid) {
        try {
            String temp = inputString(mess);
            result = Integer.parseInt(temp.trim());
            if(result < min || result > max) {
                System.out.println("Number must be between " + min + " and " + max + ". Please try again.");
            } else {
                valid = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer! Please enter a valid number.");
        }
    }
    return result;
}

    
    public double inputDouble(String mess){
        double result = 0;
        boolean valid = false;
        while(!valid){
            try {
                String temp = inputString(mess);
                result = Double.parseDouble(temp.trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Please enter a valid decimal number.");
            }
        }
       return result;
    }
    
    public Date inputDate(String mess) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);  // strict parsing (no 32/01/2020, etc)
    Date date = null;
    while (date == null) {
        String temp = inputString(mess);
        try {
            date = sdf.parse(temp);
        } catch (ParseException ex) {
            System.out.println("Invalid date format. Please enter date as dd/MM/yyyy.");
        }
    }
    return date;
}

    
    public String inputAndLoop(String mess, String pattern, boolean isLoop){
        String result = "";
        boolean loop = true;
        do{
            result = inputString(mess);
            loop = !Acceptable.isValid(result, pattern);
            if(loop && (isLoop && result.length() > 0 ))
                System.out.println("Data is invalid! Re-enter..");
        }while(isLoop && loop);
        return result.trim();
    } 
    
    /**
     * Phuong thuc cho phep nhap du lieu cua 1 khach hang
     * @return 
     */

    public Customer InputCustomer() {
        Customer x = new Customer();
        String id = inputAndLoop("Enter you ID number: ", Acceptable.CUS_ID_VALID, true);
        String name = inputAndLoop("Enter your name: " ,Acceptable.NAME_VALID , true);
        String phone = inputAndLoop("Enter your phone number: " ,Acceptable.PHONE_VALID , true);
        String email = inputAndLoop("Enter email: " ,Acceptable.EMAIL_VALID , true);
        
        return new Customer(id, phone, name, email);
    }
    
}
