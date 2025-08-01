package ui;

import java.util.Scanner;
import model.Librarian;
import model.Book;
import dao.BookDAO;
import dao.UserDAO;
import model.Member;

import java.util.ArrayList;
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
                case 5 -> userDAO.viewAllMembers();();
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
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Year Published: ");
        int year = Integer.parseInt(scanner.nextLine());

        Book book = new Book(title, author, category, year, true);
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
                        b.isAvailable() ? "Available" : "Borrowed");
            }
        }
    }

    private void updateBook() {
        viewBooks();
        System.out.print("Enter the ID of the book to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        Book existing = bookDAO.findBookById(id);
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

        bookDAO.updateBook(existing);
        System.out.println("Book updated.");
    }

    private void removeBook() {
        viewBooks();
        System.out.print("Enter the ID of the book to remove: ");
        int id = Integer.parseInt(scanner.nextLine());

        bookDAO.deleteBook(id);
        System.out.println("Book removed.");
    } 
    
    private void viewUsers() {
        UserDAO userDAO = new UserDAO();
        var allUsers = userDAO.loadAllUsers();

        List<Member> members = new ArrayList<>();
        for (var user : allUsers) {
            if (user instanceof Member member) {
                members.add(member);
            }
        }

        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            System.out.println("\n=== Registered Members ===");
            for (Member m : members) {
                System.out.printf("- [%d] %s (%s) | Email: %s%n",
                        m.getId(),
                        m.getName(),
                        m.getUsername(),
                        m.getEmail()
                );
            }
        }
    }
}