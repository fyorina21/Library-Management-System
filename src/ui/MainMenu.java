package ui;


import model.Librarian;
import model.Member;
import ui.libMenu;
import ui.memberMenu;
import abstracts.User;
import ui.Auth;
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


            if (option == 1) {
                User user = auth.login(); 

                if (user == null) {
                    System.out.println("Login failed. Goodbye.");
                    return;
                }

                if (user instanceof Librarian) {
                    libMenu.ShowMenu((Librarian) user);
                } else if (user instanceof Member) {
                    memberMenu.ShowMenu((Member) user);
                } else {
                    System.out.println("Unknown user role. Access denied.");
                }

            }else if (option == 2) {
                auth.register();
            }else if (option == 3) {
                System.out.println("Exiting...");
                return ;
            } else {
                System.out.println("Invalid input, please choose from the Main Menu.");
            }
     }
    }
}

