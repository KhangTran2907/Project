/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fa25.shopping;

/**
 *
 * @author MSII
 */
import fa25.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT NO, ID, Name, Price, Quantity FROM Product"; // Tên bảng phải đúng với trong DB

        try (Connection conn = DBUtils.getProductConnection();
             PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                int no = rs.getInt("NO");
                String id = rs.getString("ID");
                String name = rs.getString("Name");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                list.add(new Product(no, id, name, price, quantity));
            }

        } catch (SQLException e) {
            System.out.println("Error at ProductDAO.getAllProducts(): " + e.getMessage());
        }

        return list;
    }
    public boolean updateQuantity(String id, int buyQuantity) throws ClassNotFoundException, SQLException {
    boolean check = false;
    String sql = "UPDATE Product SET Quantity = Quantity - ? WHERE ID = ? AND Quantity >= ?";
    try (Connection conn = DBUtils.getProductConnection();
         PreparedStatement stm = conn.prepareStatement(sql)) {
        stm.setInt(1, buyQuantity);
        stm.setString(2, id);
        stm.setInt(3, buyQuantity);
        check = stm.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return check;
}
}



