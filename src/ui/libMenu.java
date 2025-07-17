package ui;

import java.util.Scanner;
import model.Librarian;
import model.Book;
import model.User;


public class libMenu {
    public static void ShowMenu(User user) {
        if (!(user instanceof Librarian)) {
            System.out.println("Access denied.");
            return;
        }

        Librarian librarian = (Librarian) user;
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Librarian Menu");
            System.out.println("1. Add Book");
            System.out.println("2. View Users");
            System.out.println("3. Back to Main Menu");
            
            
            System.out.print("What would you like to do? ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter book title: ");
                String title = scanner.nextLine();
                System.out.print("Enter author: ");
                String author = scanner.nextLine();

                System.out.print("Enter genre: ");
                String genre = scanner.nextLine();

                System.out.print("Enter published year: ");
                int year = scanner.nextInt();
                scanner.nextLine();

                Book newBook = new Book("B" + System.currentTimeMillis(), title, author, true, genre, year);

                try {
                    librarian.addBook(newBook);
                    System.out.println("Book added successfully.");
                } catch (Exception e) {
                    System.out.println("Failed to add book: " + e.getMessage());
                }
            } else if(choice == 2) {
                librarian.viewAllBooks();
            } else if(choice == 3) {
                System.out.println("Returning to Library Menu...");
                return;
            } else {
                System.out.println("Invalid input, please choose from the menu.");
            }
        }                    
    } 
}