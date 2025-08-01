package setup;

import java.sql.Connection;
import java.sql.Statement;
import utill.DBUtil;

public class CreateTables {
    public static void main(String[] args){
        String usersTable = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                username TEXT UNIQUE,
                email TEXT,
                role TEXT,
                password TEXT
            );
        """;

        String booksTable = """
            CREATE TABLE IF NOT EXISTS books (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                author TEXT,
                category TEXT,
                year_published INTEGER,
                is_available BOOLEAN DEFAULT 1,
                ALTER TABLE books ADD COLUMN pdf_path TEXT;
            );
        """;

        String borrowedBooksTable = """
            CREATE TABLE IF NOT EXISTS borrowed_books (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER,
                book_id INTEGER,
                borrow_date TEXT,
                return_date TEXT,
                FOREIGN KEY(user_id) REFERENCES users(id),
                FOREIGN KEY(book_id) REFERENCES books(id)
            );
        """;

        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(usersTable);
            stmt.execute(booksTable);
            stmt.execute(borrowedBooksTable);
            System.out.println("All tables created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }
}
