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
public interface Workable <T> {
    void addNew(T x);
    void update(T x);
    void searchById(String id);
    void showAll();
}
