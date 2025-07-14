import exception.UserNotFoundException;
import exception.BookNotFoundException;
import model.Book;
import model.Librarian;
import model.Member;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // ✅ Create a librarian
        Librarian librarian = new Librarian("L001", "librarian1", "Librarian1@gmail.com", "Librarian");

        // ✅ Create a member
        Member member = new Member("M001", "member1", "member1@gmail.com", "Member");

        // ✅ Register the member
        try {
            librarian.registerMember(member);
            System.out.println("Member successfully  registered.");
        } catch (UserNotFoundException e) {
            System.out.println("Error registering member: " + e.getMessage());
        }

        // ✅ Create a book
        Book book = new Book("B001", "Book1", "author1", true, "OOP", 2005);

        // ✅ Add the book
        try {
            librarian.addBook(book);
            System.out.println("✔ Book added.");
        } catch (BookNotFoundException e) {
            System.out.println("✘ Error adding book: " + e.getMessage());
        }

        // ✅ View all books
        librarian.viewAllBooks();

        // ✅ Display member list
        System.out.println("\nRegistered Members:");
        ArrayList<Member> allMembers = librarian.getMembers();
        for (Member m : allMembers) {
            m.displayInfo();  // Make sure Member overrides displayInfo()
        }

        // ✅ Display librarian info
        System.out.println("\nLibrarian Info:");
        librarian.displayInfo();
    }
}
