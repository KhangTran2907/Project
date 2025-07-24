/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author MSII
 */
public class Customers extends HashMap<String, Customer> {
    private List<Customer> customerList;
    private String pathFile;
    private boolean saved;
    
    public boolean saved(){
        return saved;
    }
    
    public Customers(){
        super();
        this.customerList = new ArrayList<>();
        this.pathFile = "./Customers.dat";
        this.saved = true;
    }
    
    public boolean addnew(Customer x){
        if( x == null || this.containsKey(x.getId())){
            return false;
        }
        this.put(x.getId(), x);
        saved = false;
        return true;
    }
    
public boolean update(Customer x){
    if (x == null || !this.containsKey(x.getId())){ // ← Fix: !containsKey
        return false;
    }
    this.put(x.getId(), x);
    saved = false;
    return true;
}

    
    public Customer searchByID(String id){
        return this.get(id);
    }
    
    public List<Customer> filterByName(String name){
        List<Customer> l = new ArrayList<>();
        for (Customer i : this.values()){
            if (i.getName().toLowerCase().endsWith(name.toLowerCase()))
                l.add(i);
        }
        return l; 
    }
    
    public List<Customer> findbyNameExact(String name){
        List<Customer> result = new ArrayList<>();
        String lowerName = name.toLowerCase();
        
        for(Customer customer : this.values()){
            if( customer.getName().equalsIgnoreCase(name)){
                result.add(customer);
            }
        }
        return result;
    }

    
    public void showAll(){
        showAll((List<Customer>) this.values());
    }
    public void showAll(List<Customer>l){
                System.out.println("Danh Sach Khach Hang");
        for( Customer i : this.values()){
            System.out.println(i);
        }
    }
    
    public void saveToFile(){
        ObjectOutputStream oos = null;
        try {
            //        --1. Tạo file ở trên đĩa
            File f = new File(pathFile);
            //        --2.Ánh xạ một Outputstream vào tập tin đã tạo
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(f);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
            }
//        --3.Tạo bộ đệm để truyền dữ liệu
            oos = new ObjectOutputStream(fos);
            //        --4.Lặp để lấy dữ liệu đưa vào danh sách
            for(Customer i : this.values()){
                oos.writeObject(i);
            }
            //        5.
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    public void readFromFile(){
        FileInputStream fis = null;
        try {
            //--1.
            File f = new File(pathFile);
            //--2.
            fis = new FileInputStream(f);
            //--3.
            ObjectInputStream ois = new ObjectInputStream(fis);
            //--4.
            while(fis.available()>0){
                Customer x  =(Customer) ois.readObject();
                this.put(x.getId(), x);
            }   //--5. 
            ois.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
