// import exception.UserNotFoundException;
// import exception.BookNotFoundException;
// import model.Book;
import model.Librarian;
import model.Member;
import ui.Auth;
import abstracts.User;
import ui.libMenu;

// import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // ✅ Create a librarian
        // Librarian librarian = new Librarian("L001", "librarian1", "Librarian1@gmail.com", "Librarian", "L001");

        // ✅ Create a member
     //   Member member = new Member("M001", "member1", "member1@gmail.com", "Member", "M001");

        Auth auth = new Auth();
        auth.register();
        User user = auth.login();
 
    if (user == null) {
            System.out.println("Login failed. Goodbye.");
            return;
        }

        if (user instanceof Librarian) {
            libMenu.ShowMenu((Librarian) user);
        } else if (user instanceof Member) {
            memberMenu.ShowMenu(user);
        } else {
            System.out.println("Unknown user role. Access denied.");
        }

        //         // ✅ Register the member
        // try {
        //     librarian.registerMember(member);
        //     System.out.println("Member successfully  registered.");
        // } catch (UserNotFoundException e) {
        //     System.out.println("Error registering member: " + e.getMessage());
        // }

        // ✅ Create a book
        // Book book = new Book("B001", "Book1", "author1", true, "OOP", 2005);

        // ✅ Add the book
//         try {
//             librarian.addBook(book);
//             System.out.println("✔ Book added.");
//         } catch (BookNotFoundException e) {
//             System.out.println("✘ Error adding book: " + e.getMessage());
//         }

//         // ✅ View all books
        // librarian.viewAllBooks();

//         // ✅ Display member list
//         System.out.println("\nRegistered Members:");
//         ArrayList<Member> allMembers = librarian.getMembers();
//         for (Member m : allMembers) {
//             m.displayInfo();  // Make sure Member overrides displayInfo()
//         }

//         // ✅ Display librarian info
//         System.out.println("\nLibrarian Info:");
//         librarian.displayInfo();
    }
}
