import java.util.Scanner;

public class MainMenu{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
            System.out.println("Main Menu");
            System.out.println("1. Librarian");
            System.out.println("2. Member");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            if (option == 1) {
                libMenu.ShowMenu();
            }else if (option == 2) {
                System.out.println("Opening member page....");
            }else if (option == 3) {
                System.out.println("Exiting...");
                return;
            } else {
                System.out.println("Invalid input, please choose from the Main Menu.");
            }
        }
    }
}
