package ui;


import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.List;
import model.Member;
import model.Book;
import dao.BookDAO;
import dao.BorrowDAO;
import java.awt.Desktop;



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
            System.out.println("4. Logout");
            
            
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
        List<Book> availableBooks = bookDAO.getAllBooks();

        if (availableBooks.isEmpty()) {
            System.out.println("No books available at the moment.");
            return;
        }
        System.out.println("\nAvailable Books: ");
        for (Book book : availableBooks) {
            if (book.isAvailable()) {
                System.out.printf("- [%d] %s by %s (%d) [%s]%n",
                book.getId(), book.getTitle(), book.getAuthor(),
                book.getYearPublished(), book.getCategory());
            }
       
        }       

        scanner.nextLine();
    }

    private void borrowBook(Member member) {
        List<Book> availableBooks = bookDAO.getAllBooks();

        if (availableBooks.isEmpty()) {
            System.out.println("No books available to borrow.");
            return;
        }

        viewAvailableBooks();
        System.out.print("Enter the ID of the book to borrow (or 0 to go back): ");

        int bookId = -1;
        try {
            if (bookId == 0) {
                System.out.println("Returning to menu...");
                return;
            } else {
                bookId = Integer.parseInt(scanner.nextLine());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        

        Book book = bookDAO.getBookById(bookId);
        if (book == null || !book.isAvailable()) {
            System.out.println("Book not available or invalid ID.");
            return;
        }

        boolean borrow = member.borrowBook(bookId);
        if (!borrow) {
            System.out.println("Unable to borrow the book.");
            return;
        }

        System.out.println("You have borrowed \"" + book.getTitle() + "\".");
        book.setIsAvailable(false);
        borrowDAO.borrowBook(member.getId(), bookId);
        bookDAO.updateBook(book);

        if (book.getPdfPath() != null && !book.getPdfPath().isEmpty()) {
            System.out.println("PDF link available.");
            System.out.println("1. Open PDF");
            System.out.println("0. Return to menu");

            System.out.print("Choose an option: ");
            String input = scanner.nextLine().trim();

            if (input.equals("1")) {
                openPdf(book.getPdfPath());
            } else {
                System.out.println("Returning to menu...");
            }
        }
    }

    private void openPdf(String path) {
        try {
            File pdf = new File(path);
            if (!pdf.exists()) {
                System.out.println("PDF file not found: " + path);
                return;
            }

            if (!Desktop.isDesktopSupported()) {
                System.out.println("Opening files is not supported on this system.");
                return;
            }

            Desktop.getDesktop().open(pdf);
            System.out.println("Opening PDF...");
        } catch (IOException e) {
            System.out.println("Failed to open PDF: " + e.getMessage());
        }
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
        scanner.nextLine();

        System.out.print("Enter the ID of the book to return: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        Book book = bookDAO.getBookById(bookId);

        if (book == null) {
            System.out.println("Invalid book ID.");
            return;
        }

        borrowDAO.returnBook(member.getId(), bookId);
        book.setIsAvailable(true);
        bookDAO.updateBook(book);

        
        System.out.println("You have returned \"" + book.getTitle() + "\".");

    }
}