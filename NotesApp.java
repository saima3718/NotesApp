import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String NOTES_FILE = "notes.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Java Notes App!");
        
        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    viewNotes();
                    break;
                case 2:
                    addNote();
                    break;
                case 3:
                    clearNotes();
                    break;
                case 4:
                    System.out.println("Exiting Notes App!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. View Notes");
        System.out.println("2. Add Note");
        System.out.println("3. Clear All Notes");
        System.out.println("4. Exit");
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number!");
            scanner.next(); 
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }

    private static void viewNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
            System.out.println("\n===== YOUR NOTES =====");
            String line;
            int noteNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(noteNumber++ + ". " + line);
            }
            if (noteNumber == 1) {
                System.out.println("No notes found.");
            }
        
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. File doesn't exist yet.");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    private static void addNote() {
        scanner.nextLine(); 
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();
        
        try (FileWriter writer = new FileWriter(NOTES_FILE, true)) {
            writer.write(note + "\n");
            System.out.println("Note added successfully!");
        } catch (IOException e) {
            System.out.println("Error saving note: " + e.getMessage());
        }
    }

    private static void clearNotes() {
        System.out.print("Are you sure you want to clear all notes? (y/n): ");
        scanner.nextLine(); 
        String confirmation = scanner.nextLine().toLowerCase();
        
        if (confirmation.equals("y")) {
            try (FileWriter writer = new FileWriter(NOTES_FILE)) {
                writer.write(""); 
                System.out.println("All notes cleared successfully!");
            } catch (IOException e) {
                System.out.println("Error clearing notes: " + e.getMessage());
            }
        } else {
            System.out.println("Operation cancelled.");
        }
    }

}
