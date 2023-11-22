package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static Connection conn;
    
    private Connect() {
        // private constructor to prevent instantiation
    }

    public static Connection getConnect() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/quanlybanhang";
                String user = "root";
                String password = ""; // Your database password here

                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Kết nối thành công!");
            } catch (SQLException e) {
                System.out.println("Kết nối không thành công!");
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Ngắt kết nối thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
