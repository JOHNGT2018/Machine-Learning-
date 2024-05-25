package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String option;

        // File path to the CSV file containing weather data
        String csvFilePath = "src\\main\\resources\\seattle-weather.csv";

        try {
            // Load CSV data
            DataLoading.CSVData csvData = DataLoading.loadCSV(csvFilePath);

            // Clean CSV data by handling missing values and other inconsistencies
            CSVDataCleaner.cleanCSVData(csvData);

            // Drop the 0th index column (date) to exclude it from the analysis
            List<String[]> cleanedDataList = csvData.getData();
            List<String[]> cleanedDataWithoutDate = new ArrayList<>();
            for (String[] row : cleanedDataList) {
                String[] newRow = new String[row.length - 1];
                System.arraycopy(row, 1, newRow, 0, row.length - 1);
                cleanedDataWithoutDate.add(newRow);
            }

            // Encode weather data to make it suitable for machine learning
            String[] cleanedDataArray = cleanedDataWithoutDate.stream()
                    .map(row -> String.join(",", row))
                    .toArray(String[]::new);
            String[] encodedDataArray = DataPreprocessing.encodeWeather(cleanedDataArray);

            // Convert encoded data back to List<String[]> for further processing
            List<String[]> encodedDataList = new ArrayList<>();
            for (String row : encodedDataArray) {
                encodedDataList.add(row.split(","));
            }

            // Split encoded data into features (X) and target variable (y)
            List<String[]> X = DataPreprocessing.splitX(encodedDataArray);
            List<String> y = DataPreprocessing.splitY(encodedDataArray);

            // Perform train-test split with 80% training data and 20% test data
            DataPreprocessing.DataSplit dataSplit = DataPreprocessing.splitTrainTest(X, y, 0.8);

            List<String[]> X_train = dataSplit.getX_train();
            List<String[]> X_test = dataSplit.getX_test();
            List<String> y_train = dataSplit.getY_train();
            List<String> y_test = dataSplit.getY_test();

            // Save training data to CSV for use with Weka
            DecisionTreeClassifier.saveToCSV(X_train, y_train, "train_data.csv");
            // Save test data to CSV for use with Weka
            DecisionTreeClassifier.saveToCSV(X_test, y_test, "test_data.csv");

            // Create and train the decision tree classifier
            DecisionTreeClassifier model = new DecisionTreeClassifier();
            model.loadTrainingData("train_data.csv");
            model.loadTestData("test_data.csv");
            model.train();
            model.evaluate(); // Evaluate the model and print the report

            do {
                // Display the menu options for user interaction
                System.out.println("=============================================================");
                System.out.println("                WEATHER PREDICTION & DATA ANALYSIS           ");
                System.out.println("=============================================================");
                System.out.println("|                       MENU OPTIONS                        |");
                System.out.println("|-----------------------------------------------------------|");
                System.out.println("|  1. PRINT LOADED DATA                                     |");
                System.out.println("|  2. PRINT CLEANED DATA                                    |");
                System.out.println("|  3. PRINT ENCODED DATA                                    |");
                System.out.println("|  4. PRINT X AND Y                                         |");
                System.out.println("|  5. PRINT TRAIN AND TEST DATA                             |");
                System.out.println("|  6. FIT USING DECISION TREE                               |");
                System.out.println("|  7. VISUALIZATION                                         |");
                System.out.println("|  8. EXIT                                                  |");
                System.out.println("=============================================================");
                System.out.print("Enter your option: ");
                int choice = Integer.parseInt(scan.nextLine());

                switch (choice) {
                    case 1:
                        // Print the original loaded data (first 10 rows) and row/column counts
                        System.out.println("          DATASET        ");
                        System.out.println("-------------------------- ");
                        System.out.println("\nOriginal Data (first 10 rows):");
                        printFirst10Rows(csvData.getData());
                        System.out.println("Original Row count: " + csvData.getRowCount());
                        System.out.println("Original Column count: " + csvData.getColumnCount());
                        break;

                    case 2:
                        // Print cleaned data (first 10 rows) and row/column counts
                        System.out.println("      CLEANED DATASET      ");
                        System.out.println("-------------------------- ");
                        System.out.println("\nCleaned Row count: " + csvData.getData().size());
                        System.out.println("Cleaned Data (first 10 rows):");
                        printFirst10Rows(cleanedDataList);
                        System.out.println("Column Names:");
                        for (String columnName : csvData.getColumnNames()) {
                            System.out.println(columnName);
                        }
                        break;

                    case 3:
                        // Print encoded data (first 10 rows)
                        System.out.println("       ENCODED DATA       ");
                        System.out.println("-------------------------- ");
                        System.out.println("Encoded data (first 10 rows):");
                        printFirst10Rows(encodedDataList);
                        break;

                    case 4:
                        // Print features (X) and target variable (y) (first 10 rows/elements)
                        System.out.println("          X & Y            ");
                        System.out.println("-------------------------- ");
                        System.out.println("Print X and y");
                        System.out.println("X (first 10 rows):");
                        printFirst10Rows(X);
                        System.out.println("y (first 10 elements):");
                        printFirst10Elements(y);
                        break;

                    case 5:
                        // Print training and test data (first 10 rows/elements)
                        System.out.println("        TRAIN & TEST        ");
                        System.out.println("-------------------------- ");
                        System.out.println("Print train and test");
                        System.out.println("X_train (first 10 rows):");
                        printFirst10Rows(X_train);
                        System.out.println("X_test (first 10 rows):");
                        printFirst10Rows(X_test);
                        System.out.println("y_train (first 10 elements):");
                        printFirst10Elements(y_train);
                        System.out.println("y_test (first 10 elements):");
                        printFirst10Elements(y_test);
                        break;

                    case 6:
                        // Train and evaluate the decision tree classifier again
                        System.out.println("*================================================================================*");
                        System.out.println("______________________________DECISION TREE______________________________________");
                        model.train();
                        model.evaluate(); // Evaluate and print the report
                        break;

                    case 7:
                        // Visualize the weather data using various chart types
                        System.out.println("Visualization");
                        List<String[]> cleanedData = csvData.getData();
                        String[] features = { "weather" }; // Adjust feature names as per your CSV columns
                        String[] chartTypes = { "scatter", "bar", "line", "pie" };

                        for (String feature : features) {
                            for (String chartType : chartTypes) {
                                String frameTitle = "Weather Data Visualization - " + feature + " (" + chartType + ")";
                                DataVisualizer.visualizeData(cleanedData, feature, frameTitle, chartType);
                            }
                        }
                        break;

                    case 8:
                        // Exit the program
                        System.out.println("Exiting...");
                        break;

                    default:
                        // Handle invalid options
                        System.out.println("Invalid option");
                        break;
                }

                if (choice == 8) {
                    break;
                }

                System.out.println("Do you want to continue? (y/no)?");
                option = scan.nextLine();
            } while (option.equalsIgnoreCase("y"));
        } catch (IOException e) {
            // Handle errors related to loading the CSV file
            System.err.println("Error loading CSV file: " + e.getMessage());
        } catch (Exception e) {
            // Handle all other exceptions
            e.printStackTrace();
        } finally {
            // Close the scanner resource
            scan.close();
        }
    }

    // Method to print the first 10 rows of a given list of data
    private static void printFirst10Rows(List<String[]> data) {
        int rowCount = Math.min(data.size(), 10);
        for (int i = 0; i < rowCount; i++) {
            System.out.println(String.join(",", data.get(i)));
        }
    }

    // Method to print the first 10 elements of a given list of data
    private static void printFirst10Elements(List<String> data) {
        int elementCount = Math.min(data.size(), 10);
        for (int i = 0; i < elementCount; i++) {
            System.out.println(data.get(i));
        }
    }
}
