package ui;


import model.Member;

import java.util.Scanner;


public class MainMenu{
    Scanner scanner = new Scanner(System.in);
    public void showMenu(){
         while(true) {
            System.out.println("======== Main Menu ========");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();


            if (option == 1) {
                Member user = Auth.login();
                if (user != null) {
                    if (user.getRole().equals("librarian")) {
                        libMenu.ShowMenu();
                    }
                }

            }else if (option == 2) {
                Auth.register();
            }else if (option == 3) {
                System.out.println("Exiting...");
                return ;
            } else {
                System.out.println("Invalid input, please choose from the Main Menu.");
            }
     }
    }
}

