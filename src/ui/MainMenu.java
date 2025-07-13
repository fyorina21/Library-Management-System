import java.util.Scanner;


public class MainMenu{
    Scanner scanner = new Scanner(System.in);
    
    while(true) {
        System.out.println("======== Main Menu ========");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();


        if (option == 1) {
            User user = auth.login();
            if (user != null) {
                if (user.getRole().equals("librarian")) {
                    new libMenu().display();
                }
            }
            
        }else if (option == 2) {
            auth.register();
        }else if (option == 3) {
            System.out.println("Exiting...");
            return;
        } else {
            System.out.println("Invalid input, please choose from the Main Menu.");
        }
    } 
}
