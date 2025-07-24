/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.DecimalFormat;


/**
 *
 * @author MSII
 */
public class SetMenu implements Serializable{
    
    public String menuId;
    public  String menuName;
    public  double price;
    public  String ingredients;
    
     public SetMenu(){
    }
     
    public SetMenu(String menuId, String menuName, double price, String ingredients) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }      

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0");
        return String.format("Code        :%s\nName        :\nPrice        :\nIngredients        :\n",this.menuId,this.menuName,df.format(this.price),this.ingredients);
    }
    
    
}
