import java.sql.*;

// Menghubungkan Java dengan MySQL 
public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/indri_modies";
        return DriverManager.getConnection(url, "root", "");
    }
}