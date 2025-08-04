package dao;

import model.Book;
import utill.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {
    public void borrowBook(int userId, int bookId) {
        String sql = "INSERT INTO borrowed_books (user_id, book_id, borrow_date) VALUES (?, ?, CURRENT_DATE)";

        try (Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error borrowing book: " + e.getMessage());
        }
    }

    public void returnBook(int userId, int bookId) {
        String sql = "DELETE FROM borrowed_books WHERE user_id = ? AND book_id = ?";

        try (Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error returning book: " +e.getMessage());
        }
    }

    public List<Book> getBorrowedBooks(int userId) {
        List<Book> borrowedBooks = new ArrayList<>();
        String sql = """
            SELECT b.id, b.title, b.category, b.year_published, b.is_available, b.pdf_path
            FROM books b
            JOIN borrowed_books bb ON b.id = bb.book_id
            WHERE bb.user_id = ?
        """;

        try (Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setYearPublished(rs.getInt("year_published"));
                book.setIsAvailable(rs.getBoolean("is_available"));
                book.setPdfPath(rs.getString("pdf_path"));
                borrowedBooks.add(book);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching borrowed books: " + e.getMessage());
        }

        return borrowedBooks;
    }
}
