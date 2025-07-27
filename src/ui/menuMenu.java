import java.util.Scanner;
import java.util.List;
import model.Member;

public class memberMenu {
    public static void showMenu(Member member) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\n=== Member Menu ===");
            System.out.println("1. View Available Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Borrowed Books");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Book> availableBooks = member.viewAvailableBooks();
                    if (availableBooks.isEmpty()) {
                        System.out.println("No books available at the moment.");
                    } else {
                        System.out.println("Available Books:");
                        for (Book book : availableBooks) {
                            System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter the title of the book to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    boolean borrowed = member.borrowBook(borrowTitle);
                    if (borrowed) {
                        System.out.println("Book borrowed successfully.");
                    } else {
                        System.out.println("Book not available or doesn't exist.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    boolean returned = member.returnBook(returnTitle);
                    if (returned) {
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("You haven’t borrowed that book.");
                    }
                    break;

                case 4:
                    List<Book> borrowedBooks = member.getBorrowedBooks();
                    if (borrowedBooks.isEmpty()) {
                        System.out.println("You haven't borrowed any books.");
                    } else {
                        System.out.println("Your Borrowed Books:");
                        for (Book book : borrowedBooks) {
                            System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
                        }
                    }
                    break;

                case 5:
                    System.out.println("Returning to Main Menu...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        }
    }
}