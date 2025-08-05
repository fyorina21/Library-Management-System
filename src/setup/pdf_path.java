package setup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class pdf_path {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:library.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to the database");

                String alterSQL = "ALTER TABLE books ADD COLUMN pdf_path TEXT";
            
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(alterSQL);
                    System.out.println("Column added successfully.");
                } catch (SQLException e) {
                    System.out.println("Connection failed: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection fauked: " + e.getMessage());
        }
    }
}
