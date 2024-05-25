

// package com.example;

// import java.util.HashSet;
// import java.util.Iterator;
// import java.util.Set;

// public class CSVDataCleaner {

//     public static void cleanCSVData(DataLoading.CSVData csvData) {
//         int nullValueCount = checkAndHandleNullValues(csvData);
//         int duplicateRowCount = removeDuplicateRows(csvData);

//         if (nullValueCount == 0) {
//             System.out.println("No null values found.");
//         } else {
//             System.out.println("Found and replaced " + nullValueCount + " null values.");
//         }

//         if (duplicateRowCount == 0) {
//             System.out.println("No duplicate rows found.");
//         } else {
//             System.out.println("Found and removed " + duplicateRowCount + " duplicate rows.");
//         }

//         System.out.println("Data cleaning completed.");
//     }

//     private static int checkAndHandleNullValues(DataLoading.CSVData csvData) {
//         int nullValueCount = 0;
//         for (String[] row : csvData.getData()) {
//             for (int i = 0; i < row.length; i++) {
//                 if (row[i] == null || row[i].trim().isEmpty()) {
//                     row[i] = "0"; // Replace null or empty values with "0"
//                     nullValueCount++;
//                 }
//             }
//         }
//         return nullValueCount;
//     }

//     private static int removeDuplicateRows(DataLoading.CSVData csvData) {
//         Set<String> seenRows = new HashSet<>();
//         int duplicateRowCount = 0;
//         Iterator<String[]> iterator = csvData.getData().iterator();
//         while (iterator.hasNext()) {
//             String[] row = iterator.next();
//             String rowString = String.join(",", row);
//             if (seenRows.contains(rowString)) {
//                 iterator.remove();
//                 duplicateRowCount++;
//             } else {
//                 seenRows.add(rowString);
//             }
//         }
//         return duplicateRowCount;
//     }

// }

package com.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CSVDataCleaner {

    // Main method to clean CSV data
    public static void cleanCSVData(DataLoading.CSVData csvData) {
        // Check and handle null values in the CSV data
        int nullValueCount = checkAndHandleNullValues(csvData);
        // Remove duplicate rows from the CSV data
        int duplicateRowCount = removeDuplicateRows(csvData);

        // Print the results of null value handling
        if (nullValueCount == 0) {
            System.out.println("No null values found.");
        } else {
            System.out.println("Found and replaced " + nullValueCount + " null values.");
        }

        // Print the results of duplicate row removal
        if (duplicateRowCount == 0) {
            System.out.println("No duplicate rows found.");
        } else {
            System.out.println("Found and removed " + duplicateRowCount + " duplicate rows.");
        }

        System.out.println("Data cleaning completed.");
    }

    // Method to check for and handle null or empty values in the CSV data
    private static int checkAndHandleNullValues(DataLoading.CSVData csvData) {
        int nullValueCount = 0; // Counter for null or empty values
        for (String[] row : csvData.getData()) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] == null || row[i].trim().isEmpty()) {
                    row[i] = "0"; // Replace null or empty values with "0"
                    nullValueCount++;
                }
            }
        }
        return nullValueCount;
    }

    // Method to remove duplicate rows from the CSV data
    private static int removeDuplicateRows(DataLoading.CSVData csvData) {
        Set<String> seenRows = new HashSet<>(); // Set to keep track of unique rows
        int duplicateRowCount = 0; // Counter for duplicate rows
        Iterator<String[]> iterator = csvData.getData().iterator();
        while (iterator.hasNext()) {
            String[] row = iterator.next();
            String rowString = String.join(",", row); // Convert row to a string
            if (seenRows.contains(rowString)) {
                iterator.remove(); // Remove duplicate row
                duplicateRowCount++;
            } else {
                seenRows.add(rowString); // Add unique row to the set
            }
        }
        return duplicateRowCount;
    }


}
