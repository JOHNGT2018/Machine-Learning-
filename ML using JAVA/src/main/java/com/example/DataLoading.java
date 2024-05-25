// package com.example;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// public class DataLoading {
//     public static class CSVData {
//         private String[] columnNames;
//         private List<String[]> data;

//         public CSVData(String[] columnNames, List<String[]> data) {
//             this.columnNames = columnNames;
//             this.data = data;
//         }

//         public String[] getColumnNames() {
//             return columnNames;
//         }

//         public List<String[]> getData() {
//             return data;
//         }

//         public void printData() {
//             for (String colName : columnNames) {
//                 System.out.print(colName + "\t");
//             }
//             System.out.println();
//             for (String[] row : data) {
//                 for (String value : row) {
//                     System.out.print(value + "\t");
//                 }
//                 System.out.println();
//             }
//         }

//         public int getRowCount() {
//             return data.size();
//         }

//         public int getColumnCount() {
//             return columnNames.length;
//         }
//     }

//     public static CSVData loadCSV(String filePath) throws IOException {
//         BufferedReader reader = new BufferedReader(new FileReader(filePath));
//         String headerLine = reader.readLine();
//         String[] columnNames = headerLine.split(",");

//         List<String[]> data = new ArrayList<>();
//         String line;
//         while ((line = reader.readLine()) != null) {
//             String[] row = line.split(",");
//             data.add(row);
//         }
//         reader.close();
//         return new CSVData(columnNames, data);
//     }
// }

package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoading {

    // Inner class to represent CSV data structure
    public static class CSVData {
        private String[] columnNames; // Array to store column names
        private List<String[]> data; // List to store CSV rows

        // Constructor to initialize CSVData with column names and data
        public CSVData(String[] columnNames, List<String[]> data) {
            this.columnNames = columnNames;
            this.data = data;
        }

        // Getter for column names
        public String[] getColumnNames() {
            return columnNames;
        }

        // Getter for data
        public List<String[]> getData() {
            return data;
        }

        // Method to print the CSV data
        public void printData() {
            for (String colName : columnNames) {
                System.out.print(colName + "\t");
            }
            System.out.println();
            for (String[] row : data) {
                for (String value : row) {
                    System.out.print(value + "\t");
                }
                System.out.println();
            }
        }

        // Method to get the number of rows in the data
        public int getRowCount() {
            return data.size();
        }

        // Method to get the number of columns in the data
        public int getColumnCount() {
            return columnNames.length;
        }
    }

    // Method to load CSV data from a file
    public static CSVData loadCSV(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String headerLine = reader.readLine(); // Read the header line
        String[] columnNames = headerLine.split(","); // Split header line into column names

        List<String[]> data = new ArrayList<>(); // List to store CSV rows
        String line;
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(","); // Split each line into a row
            data.add(row); // Add row to data list
        }
        reader.close(); // Close the reader
        return new CSVData(columnNames, data); // Return a new CSVData object
    }
}
