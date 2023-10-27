package individualassignment4;

import java.io.*;
import java.util.*;

/**
 * CSC 340-02, Individual Assignment #4
 *
 * A simple text based interface running on the output console.
 *
 * Stores any inputted data in data.txt, which will be located in project folder
 * (file is created if not found).
 *
 * @since 10/23/23
 * @author Cindy Sandoval
 */
public class IndividualAssignment4 {

    //where data will be stored
    private static final String DATA_FILE = "data.txt";

    /**
     * initializes the data by loading it from a file, then enters an infinite
     * loop where it displays a menu of options to the user - who can choose to
     * add new data, display existing data, updating that data, and delete the
     * data - or exit the program.
     *
     * When data is added/updated/deleted, the changes are saved to the file
     * data.txt
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //load existing data from the file
        List<String> data = loadData();

        //infinite loop - keeps the program running until the user chooses to exit
        while (true) {
            System.out.println("Choose an option: [1] Add, [2] Display, [3] Update, [4] Delete, [5] Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: //adding data
                    System.out.println("Enter new data: ");
                    String newData = scanner.nextLine();
                    addData(data, newData);
                    break;

                case 2: //displaying data
                    displayData(data);
                    break;

                case 3: //updating data
                    //if empty:
                    if (data.isEmpty()) {
                        System.out.println("There is no data yet, cannot update - add data or exit the program.");
                        break;
                    } else {
                        System.out.println("Enter index to update: ");
                        int updateIndex = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter new data: ");
                        String updatedData = scanner.nextLine();
                        updateData(data, updateIndex, updatedData);
                    }
                    break;

                case 4: //deleting data
                    //if empty:
                    if (data.isEmpty()) {
                        System.out.println("There is no data in this file to delete - select [1] to add data or select [5] to exit the program.");
                        break;
                    } else {
                        System.out.println("Enter data index to delete: ");
                        int deleteIndex = scanner.nextInt();
                        deleteData(data, deleteIndex);
                    }
                    break;

                case 5: //exiting program
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice - select numbers between [1] and [5]");
            }
        }
    }

    /**
     * load data from the file into a list
     *
     * @return a list containing the data loaded from the file
     */
    private static List<String> loadData() {
        List<String> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;

            //read each line from the file and add to the list
            while ((line = br.readLine()) != null) {
                data.add(line);
            }

        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }

        return data;
    }

    /**
     * saves data from the list to the file
     *
     * @param data The list of data to save to the file
     */
    private static void saveData(List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {

            // Write each item in the list to the file
            for (String datum : data) {
                bw.write(datum);
                bw.newLine();
            }
            
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * adds new data to the list and saves the updated list to the file
     *
     * @param data The list of data
     * @param newData The new data to add
     */
    private static void addData(List<String> data, String newData) {
        data.add(newData);
        saveData(data);
    }

    /**
     * displays data from the list to the user
     *
     * @param data The list of data to display
     */
    private static void displayData(List<String> data) {
        if (data.isEmpty()) {
            System.out.println("There is no data in this file to display yet - select [1] to add data or select [5] to exit the program.");
            
        } else {
            for (int i = 0; i < data.size(); i++) {
                System.out.println(i + ": " + data.get(i));
            }
        }
    }

    /**
     * update the data at the specified index with the specified new data.
     *
     * @param data The list of data
     * @param index The index of the data to update
     * @param newData The new data to replace the existing data
     */
    private static void updateData(List<String> data, int index, String newData) {
        if (index >= 0 && index < data.size()) {
            data.set(index, newData);
            saveData(data);
            
        } else {
            System.out.println("Invalid index!");
        }
    }

    /**
     * deletes the data from the list at the specified index
     *
     * @param data The list of data
     * @param index The index of the data to delete
     */
    private static void deleteData(List<String> data, int index) {
        if (index >= 0 && index < data.size()) {
            data.remove(index);
            saveData(data);
            
        } else {
            System.out.println("Invalid index!");
        }
    }
}
