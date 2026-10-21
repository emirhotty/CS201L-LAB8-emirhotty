
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {

    public static void readFile(String inputFile, String errorFile, ArrayList<Instrument> inventory) {
        try (Scanner file = new Scanner(new File(inputFile));
             PrintWriter errors = new PrintWriter(new FileWriter(errorFile))) {

            if (file.hasNextLine()) file.nextLine(); //skip CSV header

            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] fields = line.split(",", -1);

                try {
                    String type = fields[0].trim();
                    String name = fields[1].trim();
                    String manufacturer = fields[2].trim();
                    int year = Integer.parseInt(fields[3].trim());
                    int cost = Integer.parseInt(fields[4].trim());

                    if (fields[5].trim().isEmpty() && fields[6].trim().isEmpty()
                            && fields[7].trim().isEmpty()) {
                        inventory.add(new Instrument(type, name, manufacturer, year, cost));
                    } else {
                        int strings = Integer.parseInt(fields[5].trim());
                        int frets = Integer.parseInt(fields[6].trim());
                        int bowed = Integer.parseInt(fields[7].trim());

                        if (bowed != 0 && bowed != 1) {
                            throw new NumberFormatException("Bowed must be 0 or 1");
                        }

                        inventory.add(new StringInstrument(type, name, manufacturer,
                                year, cost, strings, frets, bowed == 1));
                    }

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    errors.println("Error: " + e.getMessage() + " — " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }

    public static void displayMenu() {
        System.out.println("\n1. List all instruments");
        System.out.println("2. Find instruments by type");
        System.out.println("3. Find instruments by manufacturer");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    public static void printHeader(PrintWriter writer) {
        writer.printf("%-20s%-24s%-22s%8s%10s  %s%n",
                "TYPE", "NAME", "MANUFACTURER", "YEAR", "COST $", "STRING INFO");
    }

    public static void listInstruments(ArrayList<Instrument> inventory, PrintWriter writer) {
        writer.println("FULL INVENTORY LIST");
        printHeader(writer);

        for (Instrument instrument : inventory) {
            writer.println(instrument);
        }

        writer.println();
        writer.flush();
    }

    public static void findInstruments(ArrayList<Instrument> inventory, String type, PrintWriter writer) {
        writer.println("INVENTORY OF TYPE: " + type);
        printHeader(writer);

        int count = 0;

        for (Instrument instrument : inventory) {
            if (instrument.getType().toLowerCase().contains(type.trim().toLowerCase())) {
                writer.println(instrument);
                count++;
            }
        }

        if (count == 0) {
            String message = "No instruments of type \"" + type + "\" found.";
            System.out.println(message);
            writer.println(message);
        }

        writer.println();
        writer.flush();
    }

    public static void listManufacturers(ArrayList<Instrument> inventory, String manufacturer, PrintWriter writer) {
        writer.println("INVENTORY FROM MAKER: " + manufacturer);
        printHeader(writer);

        int count = 0;

        for (Instrument instrument : inventory) {
            if (instrument.getManufacturer().equalsIgnoreCase(manufacturer.trim())) {
                writer.println(instrument);
                count++;
            }
        }

        if (count == 0) {
            String message = "No instruments by \"" + manufacturer + "\" found.";
            System.out.println(message);
            writer.println(message);
        }

        writer.println();
        writer.flush();
    }
}
