package ui;


import java.util.Scanner;
import java.util.List;
import model.Member;
import model.Book;
import dao.BookDAO;
import dao.BorrowDAO;

public class memberMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final BookDAO bookDAO = new BookDAO();
    private final BorrowDAO borrowDAO = new BorrowDAO();


    public void ShowMenu(Member member) {
        while(true) {
            System.out.println("\n=== Member Menu ===");
            System.out.println("1. View Available Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Borrowed Books");
            System.out.println("5. Logout");
            
            
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewAvailableBooks();
                case 2 -> borrowBook(member);
                case 3 -> returnBook(member);
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }

                default -> {
                    System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }

    private void viewAvailableBooks() {
        List<Book> availableBooks = bookDAO.getAvailableBooks();

        if (availableBooks == null) {
            System.out.println("No books available at the moment.");
            return;
        }
        System.out.println("\nAvailable Books: ");
        for (Book book : availableBooks) {
            System.out.printf("- [%d] %s by %s (%d) [%s]%n",
            book.getId(), book.getTitle(), book.getAuthor(),
            book.getYearPublished(), book.getCategory());
        }
    }

    private void borrowBook(Member member) {
        viewAvailableBooks();
        System.out.print("Enter the ID of the book to borrow: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        Book book = bookDAO.findBookById(bookId);
        boolean borrow = member.borrowBook(bookId);
        if (!borrow) {
            System.out.println("Book not available or invalid ID.");
            return;
        }

        System.out.println("You have borrowed \"" + book.getTitle() + "\".");
    }

    private void returnBook(Member member) {
        List<Book> borrowed = borrowDAO.getBorrowedBooks(member.getId());
        if (borrowed.isEmpty()) {
            System.out.println("You have no borrowed books.");
            return;
        }

        System.out.println("\n=== Your Borrowed Books ===");
        for (Book book : borrowed) {
            System.out.printf("- [%d] %s by %s%n", book.getId(), book.getTitle(), book.getAuthor());
        }

        System.out.print("Enter the ID of the book to return: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        Book book = bookDAO.findBookById(bookId);

        if (book == null) {
            System.out.println("Invalid book ID.");
            return;
        }

        borrowDAO.returnBook(member.getId(), bookId);
        bookDAO.updateBook(book);

        
        System.out.println("You have returned \"" + book.getTitle() + "\".");

    }
}