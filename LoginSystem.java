import java.io.*;
import java.io.Console;
import java.util.*;

public class LoginSystem {
    static HashMap<String, String> users = new HashMap<>();
    static String fileName = "users.txt";

    public static void main(String[] args) {
        loadUsers();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Enter the Choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            if (choice == 1) register(sc);
            else if (choice == 2) login(sc);
            else if (choice == 3) { saveUsers(); break; }
            else System.out.println("Invalid choice");
        }
        sc.close();
    }

    static void register(Scanner sc) {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        if (users.containsKey(user)) {
            System.out.println("Username already exists!");
            return;
        }
        Console console = System.console();
        String pass;
        if(console == null){
        System.out.print("Enter password: ");
        // String pass = sc.nextLine();
        pass = sc.nextLine();
        }
        else{
            char[] passwordChars= console.readPassword("Enter Password: ");
            pass = new String(passwordChars);
        }
        users.put(user, pass);
        System.out.println("Registration successful!");
    }

    static void login(Scanner sc) {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        if (pass.equals(users.get(user))) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    static void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                users.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            // File might not exist initially, so do nothing
        }
    }

    static void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users");
        }
    }
}
