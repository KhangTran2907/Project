/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fa25.shopping;

/**
 *
 * @author MSII
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fa25.utils.DBUtils;
import java.util.List;

public class OrderDAO {

    public int createOrder(double total) throws ClassNotFoundException, SQLException {
        int orderId = -1;
        String sql = "INSERT INTO Orders(Total) VALUES(?)";

        try (Connection conn = DBUtils.getProductConnection();
             PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setDouble(1, total);
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        }
        return orderId;
    }

    public boolean saveOrderDetails(int orderId, List<Product> items) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO OrderDetail(OrderID, ProductID, ProductName, Quantity, Price) VALUES(?,?,?,?,?)";
        try (Connection conn = DBUtils.getProductConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            for (Product p : items) {
                stm.setInt(1, orderId);
                stm.setString(2, p.getId());
                stm.setString(3, p.getName());
                stm.setInt(4, p.getQuantity());
                stm.setDouble(5, p.getPrice());
                stm.addBatch();
            }
            stm.executeBatch();
            return true;
        }
    }
}

