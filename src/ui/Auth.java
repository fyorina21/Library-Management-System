package ui;

import java.util.Scanner;
import dao.UserDAO;
import model.User;

public class Auth {
    private Scanner scanner = new Scanner(System.in);
    private UserDAO userDAO = new UserDAO();

    public User login() {
        System.out.println("\n=== Login ===");
        System.out.print("Username: ");

        String username = scanner.nextLine();


        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userDAO.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.prinln("Login successful! Welcome " + user.getUsername()+ "(" + user.getRole() + ")");
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
        
    }

    public static void register() {
        System.out.println("\n=== Register ===");

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

        User newUser = new User(username, password, role);
        userDAO.saveUser(newUser);
        System.out.println("User registered successfully. >_<");
    }
}


