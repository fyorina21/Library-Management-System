package model;

import abstracts.User;
import exception.BookNotFoundException;
import exception.UserNotFoundException;

import java.util.ArrayList;

public class Librarian extends User {
    private ArrayList<Book> availableBooks = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();

    public Librarian(String name, String username, String email, String password) {
        super(name, username, email, "librarian", password);
    }

    @Override
    public void displayInfo() {
        System.out.println("Librarian info: " + super.toString());
    }


    public void addBook(Book b) throws BookNotFoundException {
        if (b == null || availableBooks.contains(b)) {
            throw new BookNotFoundException("Nothing to add or book already exists!");
        }
        availableBooks.add(b);
    }

    public void removeBook(Book b) throws BookNotFoundException {
        if (b == null || !availableBooks.contains(b)) {
            throw new BookNotFoundException("Nothing to remove or book not found!");
        }
        availableBooks.remove(b);
    }
    public void registerMember(Member m) throws UserNotFoundException {
        if (m == null || members.contains(m)) {
            throw new UserNotFoundException("Invalid member or already registered.");
        }
        members.add(m);
    }
    public ArrayList<Member> getMembers() {
        return members;
    }

    public void viewAllBooks() {
        System.out.println("Books in store:");
        for (Book b : availableBooks) {
            System.out.println("- " + b.getTitle());
        }
    }

    public ArrayList<Book> getAllBooks() {
        return availableBooks;
    }

}
