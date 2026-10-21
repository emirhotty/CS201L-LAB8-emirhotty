
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class Main {
    public static void main(String[] args) {

        //create an arraylist of instrument objects
        ArrayList<Instrument> inventory = new ArrayList<>(); 

        //set input for menu choices
        Scanner scanner = new Scanner(System.in); 
        String inputFile  = "instrument.csv";
        String errorFile  = "error.txt";
        String reportFile = "report.txt";

        //readFile will open & close error file
        InventoryManager.readFile(inputFile, errorFile, inventory); 
        System.out.println("Inventory loaded: " + inventory.size() + " items.\n");

        //try to open the output file for the report
        try (PrintWriter writer = new PrintWriter(new FileWriter(reportFile))) { 
            int choice;

            //print the menu & stay in loop until '4' is chosen
            //add logic (switch?) to choose which function to do based on input
            do { 
                InventoryManager.displayMenu();
                String input = scanner.nextLine();

                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    choice = 0;
                }

                switch (choice) {
                    case 1:
                        InventoryManager.listInstruments(inventory, writer);
                        break;
                    case 2:
                        System.out.print("Enter instrument type to search: ");
                        InventoryManager.findInstruments(inventory, scanner.nextLine(), writer);
                        break;
                    case 3:
                        System.out.print("Enter manufacturer to search: ");
                        InventoryManager.listManufacturers(inventory, scanner.nextLine(), writer);
                        break;
                    case 4:
                        System.out.println("Exit");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 4);

            scanner.close();

        } catch (IOException e) { 
            System.err.println("Could not write report: " + e.getMessage());
        }
    }
}
// for some reason the concole/terminal is not showing the output of the menu, but I already ran and tested the code, here is the output for the inventory, and a search for guitar in the csv file