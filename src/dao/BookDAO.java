package dao;


import model.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
}
