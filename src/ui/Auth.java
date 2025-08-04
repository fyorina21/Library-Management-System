package ui;

import java.util.Scanner;
import dao.UserDAO;
import dao.BorrowDAO;
import dao.BookDAO;
import abstracts.User;
import model.Librarian;
import model.Member;
import services.LibraryService;
import exception.LibraryException;

public class Auth {
    private final Scanner scanner = new Scanner(System.in);
    private final UserDAO userDAO = new UserDAO();
    private final LibraryService libraryService = new LibraryService(new BorrowDAO(), new BookDAO(), userDAO);
    public User login() {
        System.out.println("\n=== Login ===");
        System.out.print("Username: ");

        String username = scanner.nextLine();


        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userDAO.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome " + user.getUsername()+ "(" + user.getRole() + ")");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }      
    }

    public void register() {
        System.out.println("\n=== Register ===");

        System.out.print("Enter full name: ");
        String name = scanner.nextLine();
        if (!libraryService.isValidFullName(name)) {
            System.out.println("Invalid full name. Registration failed.");
            return;
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (!libraryService.isValidEmail(email)) {
            System.out.println("Invalid email format. Registration failed.");
            return;
        }

        System.out.print("Choose a username: ");
        String username = scanner.nextLine();
        if (userDAO.findUserByUsername(username) != null) {
            System.out.println("Username already exists.");
            return;
        }

        System.out.print("Choose a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter role (librarian/member): ");
        String role = scanner.nextLine().toLowerCase();
        if (!role.equals("librarian") && !role.equals("member")) {
            System.out.println("Invalid role. Registration failed.");
            return;
        }

    
            if (role.equals("librarian")) {
                Librarian newUser = new Librarian(name, username, email, password);
                libraryService.registerLibrarian(newUser);
            } else {
                Member newUser = new Member(name, username, email, password);
                libraryService.registerMember(newUser);
            }
            System.out.println("User registered successfully! >_<");
 

