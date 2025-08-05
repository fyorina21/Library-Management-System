package dao;


import model.Book;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:library.db");
    }

    public void saveBook(Book book) {
        String sql = "INSERT INTO books (title, author, category, year_published, is_available, pdf_path) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setInt(4, book.getYearPublished());
            pstmt.setBoolean(5, book.isAvailable());
            pstmt.setString(6, book.getPdfPath());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving book: " + e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(buildBookFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }

        return books;
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return buildBookFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding book by ID: " + e.getMessage());
        }

        return null;
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, category = ?, year_published = ?, is_available = ?, pdf_path = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setInt(4, book.getYearPublished());
            pstmt.setBoolean(5, book.isAvailable());
            pstmt.setString(6, book.getPdfPath());
            pstmt.setInt(7, book.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }

    private Book buildBookFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book(
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("category"),
                rs.getInt("year_published"),
                rs.getBoolean("is_available"),
                rs.getString("pdf_path")
        );
        book.setId(rs.getInt("id"));
        return book;
    }


    public Book findBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setYearPublished(rs.getInt("year_published"));
                book.setIsAvailable(rs.getBoolean("is_available"));
                book.setPdfPath(rs.getString("pdf_path"));
                return book;
            }
        } catch (SQLException e) {
            System.out.println("Error finding book by ID: " + e.getMessage());
        }

        return null;
    }

}
