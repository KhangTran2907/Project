/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author MSII
 */
public interface Acceptable {
    
public final String CUS_ID_VALID = "^[cgkKGC]\\d{4}$";
public final String NAME_VALID = "^[A-Za-z ]{2,25}$";
public final String PHONE_VALID = "^0\\d{9}$";
public final String INTEGER_VALID = "^\\d+$";
public final String POSITIVE_INT_VALID = "^[1-9]\\d*$";
public final String DOUBLE_VALID = "^\\d+(\\.\\d+)?$";
public final String POSITIVE_VALID = "^[+]?(?:\\d*\\.\\d+|\\d+)$";
public final String EMAIL_VALID = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    
    public static boolean isValid(String data, String pattern) {
    return data.matches(pattern); 
    }
}






