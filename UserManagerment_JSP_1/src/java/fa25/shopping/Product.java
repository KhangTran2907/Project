/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fa25.shopping;

/**
 *
 * @author MSII
 */
import java.io.Serializable;

public class Product implements Serializable {
    private int no;
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(int no, String id, String name, double price, int quantity) {
        this.no = no;
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

