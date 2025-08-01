package ui;


import java.util.Scanner;
import java.util.List;
import model.Member;
import model.Book;
import dao.BookDAO;

public class memberMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final BookDAO bookDAO = new BookDAO();


    public static void ShowMenu(Member member) {
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
                case 4 -> viewBorrowedBooks();
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }

                default:
                    System.out.println("Invalid choice. Try again.");
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
            book.getId(), book.getTitke(), book.getAuthor(),
            book.getYearPublished(), book.getGenre());
        }
    }

    private void borrowBook(Memeber member) {
        viewAvailableBooks();
        System.out.print("Enter the ID of the book to borrow: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        Book book = bookDAO.findBookByTitleById(bookId);
        if (book == null || !book.isAvailable()) {
            System.out.println("Book not available or invalid ID.");
            return;
        }

        bookDAO.updateAvailability(bookId, false);


        System.out.println("You have borrowed \"" + book.getTitle() + "\".");
    }

    private void returnBook(Member member) {
        System.out.print("Enter the ID of the book to return: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        Book book = bookDAO.findBookByTitleById(bookId);
        if (book == null) {
            System.out.println("Invalid book ID.");
            return;
        }

        bookDAO.updateAvailability(bookId, true);

        
        System.out.println("You have returned \"" + book.getTitle() + "\".");

    }
}