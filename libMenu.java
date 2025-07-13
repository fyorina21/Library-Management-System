import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class libMenu {
    public static void ShowMenu() {
        System.out.println("Librarian Menu");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. View Users");
        System.out.println("4. Back to Main Menu");

        Scanner scanner = new Scanner(System.in);
        System.out.print("What would you like to do? ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Book added successfully.");
        }else if(choice == 2) {
            System.out.println("Book removed successfully");
        }else if(choice == 3) {
            System.out.println("Viewing users...");
        }else if(choice == 4) {
            System.out.println("Returning to Library Menu...");
            return;
        } else {
            System.out.println("Invalid input, please choose from the menu.");
        }
    }

    public static void savedLogs(String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt",true));
        writer.write(message);
        writer.newLine();
        writer.close();
    } 
    public void readLogs() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
        String line;
        while((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}