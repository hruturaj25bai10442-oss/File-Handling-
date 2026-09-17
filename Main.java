import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileOperations fileOps = new FileOperations();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- FILE HANDLING MENU ---");
            System.out.println("1. Create File");
            System.out.println("2. Write to File");
            System.out.println("3. Read File");
            System.out.println("4. Delete File");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> fileOps.createFile();
                case 2 -> fileOps.writeFile();
                case 3 -> fileOps.readFile();
                case 4 -> fileOps.deleteFile();
                case 5 -> exit = true;
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
        System.out.println("Exiting Program. Goodbye!");
        scanner.close();
    }
}