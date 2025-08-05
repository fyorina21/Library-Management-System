package ui;

import java.util.Scanner;
import model.Librarian;
import model.Book;
import dao.BookDAO;
import dao.UserDAO;

import java.util.List;

public class libMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final BookDAO bookDAO = new BookDAO();
    private final UserDAO userDAO = new UserDAO();
    
    public void ShowMenu(Librarian librarian) {
        while(true) {
            System.out.println("\n=== Librarian Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Update Book");
            System.out.println("4. Remove Book");
            System.out.println("5. View All Members");
            System.out.println("6. Logout");
            
            
            System.out.print("What would you like to do? ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> updateBook();
                case 4 -> removeBook();
                case 5 -> userDAO.viewAllMembers();
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }                       
    } 

    private void addBook() {
        System.out.println("\n=== Add Book ===");
        scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        int year;
        while (true) {
            try {
                System.out.print("Year Published: ");
                year = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid year. Please enter a valid number.");
            }
        }

        System.out.print("PDF Path or URL (leave empty if not applicable): ");
        String pdfPath = scanner.nextLine();

        Book book = new Book(title, author, category, year, true, pdfPath);
        bookDAO.saveBook(book);
        System.out.println("Book added.");
    }

    private void viewBooks() {
        List<Book> books = bookDAO.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\n=== Books in Library ===");
            for (Book b : books) {
                System.out.printf("- [%d] %s by %s (%d) [%s]%n",
                        b.getId(), b.getTitle(), b.getAuthor(), b.getYearPublished(), b.getCategory(),
                        b.isAvailable() ? "Available" : "Borrowed", b.getPdfPath());
            }
        }
    }

    private void updateBook() {
        viewBooks();
        System.out.print("Enter the ID of the book to update: ");
        int id;

         try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        if (id == 0) {
            System.out.println("Returning to menu...");
            return;
        }



        Book existing = bookDAO.getBookById(id);
        if (existing == null) {
            System.out.println("Book not found.");
            return;
        }

        System.out.print("New Title (leave blank to keep: " + existing.getTitle() + "): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) existing.setTitle(title);

        System.out.print("New Author (leave blank to keep: " + existing.getAuthor() + "): ");
        String author = scanner.nextLine();
        if (!author.isEmpty()) existing.setAuthor(author);

        System.out.print("New Category (leave blank to keep: " + existing.getCategory() + "): ");
        String category = scanner.nextLine();
        if (!category.isEmpty()) existing.setCategory(category);

        System.out.print("New Year Published (enter 0 to keep: " + existing.getYearPublished() + "): ");
        int year = Integer.parseInt(scanner.nextLine());
        if (year > 0) existing.setYearPublished(year);

        try {
            year = Integer.parseInt(scanner.nextLine());
            if (year > 0) existing.setYearPublished(year);
        } catch (NumberFormatException e) {
            System.out.println("Invalid year. Keeping original.");
        }


        System.out.print("New URL (leave blank to keep: " + existing.getPdfPath() + "): ");
        String path = scanner.nextLine();
        existing.setPdfPath(path);

        bookDAO.updateBook(existing);
        System.out.println("Book updated.");
    }

    private void removeBook() {
        viewBooks();
        System.out.print("Enter the ID of the book to remove: ");
        int id; 

        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }
        if (id == 0) {
            System.out.println("Returning to menu...");
            return;
        }

        Book existing = bookDAO.findBookById(id);
        if (existing == null) {
            System.out.println("Book not found.");
            return;
        }


        bookDAO.deleteBook(id);
        System.out.println("Book removed.");
    } 
}
