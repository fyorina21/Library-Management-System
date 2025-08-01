package dao;

import java.io.BufferedInputStream;
import model.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public void saveBook(Book book) {
        List<Book> books = loadAllBooks();
        books.add(book);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_PATH)))) {
            out.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Book> loadAllBooks() {
        }catch (IOException e) {
            System.out.println ("Error: " + e.getMessage());
        }
    }
    
    public List<Book> loadAllBooks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILE_PATH)))) {
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                return (List<Book>) obj;
            } else {
                System.out.println("Error : Data in file is not a List");
                return new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error : " + e.getMessage());
            return new ArrayList<>();
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))){
        return (List<Book>) in.readObject();
        }catch (IOException  | ClassNotFoundException e){
                System.out.println("Error : " + e.getMessage());
                return new ArrayList<>();
        }
    }

    public Book findBookByTitle(String title) {
        for (Book book : loadAllBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) return book;
        }
        return null;
    public Book findBookByTitle(String title){
    for (Book book: loadAllBooks()){
        if (book.getTitle().equalsIgnoreCase(title))return book;
    }
    return null;
    }
}
