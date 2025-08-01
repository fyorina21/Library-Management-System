package dao;


import model.Book;
import utill.DBUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static final String FILE_PATH = "book.dat";

    public void saveBook (Book book) {
        List<Book> books = loadAllBooks();
        books.add(book);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
            out.writeObject(books);
        }catch (IOException e) {
            System.out.println ("Error: " + e.getMessage());
        }
    }
    
    public List<Book> loadAllBooks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))){
            return (List<Book>) in.readObject();
        }catch (IOException  | ClassNotFoundException e){
            System.out.println("Error : " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public Book findBookByTitle(String title){
    for (Book book: loadAllBooks()){
        if (book.getTitle().equalsIgnoreCase(title))return book;
    }
    return null;
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
                book.setAvailable(rs.getBoolean("is_available"));
                return book;
            }
        } catch (SQLException e) {
            System.out.println("Error finding book by ID: " + e.getMessage());
        }

        return null;
    }

}
