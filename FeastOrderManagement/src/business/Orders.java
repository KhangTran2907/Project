/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author MSII
 */
import java.text.SimpleDateFormat;
import java.util.HashSet;
import model.Order;


public class Orders extends HashSet<Order> implements Workable<Order> {
    private final String TABLE_HEADER = 
            "-----------------------------------------------------------------------------\n" +
            "Order ID| Event Date| Customer Code| Set Menu| Price| Tables| Cost           \n" +
            "-----------------------------------------------------------------------------";
    private final String TABLE_FOOTER = 
            "-----------------------------------------------------------------------------\n";
    private final String TABLE_ROW_FORMAT = "%-8s|%12s|%-14s|%-9s|%10s|%8d|%14s\n";
    private String pathFile;
    private boolean isSaved;
    
    public Orders(){
        super();
    }
    
    public boolean isSaved(){
        return this.isSaved();
    }
    
    public boolean isDuplicate(Order x){
        return this.contains(x);
    }

    @Override
    public void addNew(Order x) {
        if(!this.isDuplicate(x))
            this.add(x);
            this.isSaved = false;
    }

    @Override
    public void update(Order x) {
        if(this.contains(x)){
            this.remove(x);
            this.add(x);
        }
    }

    @Override
    public void searchById(String id) {
        for (Order order : this) {
        if (order.getOrderCode().equals(id)) {
            System.out.println(TABLE_HEADER);
            System.out.printf(TABLE_ROW_FORMAT, order.getOrderCode(), new SimpleDateFormat("dd/MM/yyyy").format(order.getEventDate()),order.getCustomerId(), order.getMenuId(), order.getProvince(), order.getNumOfTables());
            System.out.println(TABLE_FOOTER);
            return;
        }
    }
    System.out.println("Order not found!");
    }

    @Override
    public void showAll() {
        showAll(this);
    }
    
    public void showAll(HashSet<Order> k){
        System.out.println(TABLE_HEADER);
        for(Order i: k )
            System.out.println(i);
        System.out.println(TABLE_FOOTER);
    }
    
}
