/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fa25.shopping;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author MSII
 */
public class Cart {
    private Map<String, Product> cart;

    public Cart() {
        this.cart = new HashMap<>();
    }

    public Cart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public boolean add(Product product) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }

            if (this.cart.containsKey(product.getId())) {
                // Nếu sản phẩm đã có trong giỏ => tăng số lượng
                int currentQuantity = this.cart.get(product.getId()).getQuantity();
                product.setQuantity(currentQuantity + product.getQuantity());
            }

            this.cart.put(product.getId(), product);
            check = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean edit(String id, int quantity) {
        boolean check = false;
        try {
            if (this.cart != null && this.cart.containsKey(id)) {
                this.cart.get(id).setQuantity(quantity);
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean remove(String id) {
        boolean check = false;
        try {
            if (this.cart != null && this.cart.containsKey(id)) {
                this.cart.remove(id);
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public double getTotal() {
        double total = 0;
        if (cart != null) {
            for (Product p : cart.values()) {
                total += p.getPrice() * p.getQuantity();
            }
        }
        return total;
    }
}

