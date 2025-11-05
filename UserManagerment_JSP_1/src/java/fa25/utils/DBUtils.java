/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fa25.utils;
/**
 *
 * @author MSII
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    // Database chính cho UserDAO
    private static final String DB_NAME = "UserManagement";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "12345";

    // Giữ nguyên cho UserDAO
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME + ";encrypt=false";
        conn = DriverManager.getConnection(url, USER_NAME, PASSWORD);
        return conn;
    }

    // ✨ Thêm mới — cho ProductDAO
    public static Connection getProductConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Product;encrypt=false";
        String user = "sa";
        String pass = "12345";
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
}

