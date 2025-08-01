package ui;


import model.Librarian;
import model.Member;
import abstracts.User;
import java.util.Scanner;


public class MainMenu{
    Scanner scanner = new Scanner(System.in);
    Auth auth = new Auth();


    public void ShowMenu(){
         while(true) {
            System.out.println("======== Main Menu ========");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    User user = auth.login();
                    if (user != null) {
                        switch (user) {
                            case Librarian librarian -> new libMenu().ShowMenu(librarian);
                            case Member member -> new memberMenu().ShowMenu(member);
                            default -> {
                            }
                        }
                    } else {
                        System.out.println("Login Failed T^T. GoodBye.");
                    }
                }
                case 2 -> auth.register();
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}

