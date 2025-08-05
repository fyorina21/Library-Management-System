package services;

import model.Member;
import model.Book;
import java.util.List;

public class ReportService {

    /**
     * Generates a report showing each member's current borrowed books.
     * Assumes Member has a method getBorrowedBooks() returning List<Book>
     * and a getName() method for member identification.
     */
    public void generateMemberActivityReport(List<Member> members) {
        System.out.println("=== Member Activity Report ===");
        for (Member member : members) {
            System.out.println("Member: " + member.getName());

            List<Book> borrowedBooks = member.getBorrowedBooks();

            if (borrowedBooks == null || borrowedBooks.isEmpty()) {
                System.out.println("  No books currently borrowed.");
            } else {
                System.out.println("  Borrowed Books:");
                for (Book book : borrowedBooks) {
                    System.out.println("    - " + book.getTitle() + " by " + book.getAuthor());
                }
            }
            System.out.println();
        }
    }
}

