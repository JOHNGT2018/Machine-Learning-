// package com.example;

// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.StringJoiner;

// public class DataPreprocessing {

//     public static String[] encodeWeather(String[] cleanedData) {
//         StringBuilder cleanedDataBuilder = new StringBuilder();
//         Map<String, Integer> WeaEncoding = new HashMap<>();
//         WeaEncoding.put("sun", 0);
//         WeaEncoding.put("drizzle", 1);
//         WeaEncoding.put("snow", 2);
//         WeaEncoding.put("fog", 3);
//         WeaEncoding.put("rain", 4);

//         try {
//             for (String line : cleanedData) {
//                 if (line.trim().isEmpty()) {
//                     continue; // Skip empty lines
//                 }
//                 String[] data = line.split(",");
//                 StringJoiner encodedLine = new StringJoiner(",");

//                 for (int i = 0; i < data.length; i++) {
//                     String value = data[i].trim();
//                     if (i == 4) { // Assuming 'weather' is at index 4
//                         Integer encodedValue = WeaEncoding.get(value);
//                         if (encodedValue != null) {
//                             encodedLine.add(encodedValue.toString());
//                         } else {
//                             // Handle unexpected values (optional)
//                             System.err.println("Unexpected value in column 'weather': " + value);
//                             encodedLine.add("0"); // Default encoding for unexpected value
//                         }
//                     } else {
//                         encodedLine.add(value);
//                     }
//                 }
//                 cleanedDataBuilder.append(encodedLine.toString()).append("\n");
//             }
//         } catch (Exception e) {
//             System.err.println("Error processing data: " + e.getMessage());
//             e.printStackTrace();
//         }

//         return cleanedDataBuilder.toString().split("\n");
//     }

//     public static List<String[]> splitX(String[] encodedDataList) {
//         List<String[]> X = new ArrayList<>();
//         for (String line : encodedDataList) {
//             String[] data = line.split(",");
//             String[] xRow = new String[data.length - 1];
//             System.arraycopy(data, 0, xRow, 0, data.length - 1);
//             X.add(xRow);
//         }
//         return X;
//     }

//     public static List<String> splitY(String[] encodedData) {
//         List<String> y = new ArrayList<>();
//         for (String line : encodedData) {
//             String[] data = line.split(",");
//             y.add(data[data.length - 1]);
//         }
//         return y;
//     }

// public static DataSplit splitTrainTest(List<String[]> X, List<String> y, double trainRatio) {
//         int trainSize = (int) (X.size() * trainRatio);
//         List<String[]> X_train = new ArrayList<>();
//         List<String[]> X_test = new ArrayList<>();
//         List<String> y_train = new ArrayList<>();
//         List<String> y_test = new ArrayList<>();

//         List<Integer> indices = new ArrayList<>();
//         for (int i = 0; i < X.size(); i++) {
//             indices.add(i);
//         }
//         Collections.shuffle(indices);

//         for (int i = 0; i < trainSize; i++) {
//             X_train.add(X.get(indices.get(i)));
//             y_train.add(y.get(indices.get(i)));
//         }
//         for (int i = trainSize; i < X.size(); i++) {
//             X_test.add(X.get(indices.get(i)));
//             y_test.add(y.get(indices.get(i)));
//         }

//         return new DataSplit(X_train, X_test, y_train, y_test);
//     }

//     public static class DataSplit {
//         private final List<String[]> X_train;
//         private final List<String[]> X_test;
//         private final List<String> y_train;
//         private final List<String> y_test;

//         public DataSplit(List<String[]> X_train, List<String[]> X_test, List<String> y_train, List<String> y_test) {
//             this.X_train = X_train;
//             this.X_test = X_test;
//             this.y_train = y_train;
//             this.y_test = y_test;
//         }

//         public List<String[]> getX_train() {
//             return X_train;
//         }

//         public List<String[]> getX_test() {
//             return X_test;
//         }

//         public List<String> getY_train() {
//             return y_train;
//         }

//         public List<String> getY_test() {
//             return y_test;
//         }
//     }
// }

package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class DataPreprocessing {

    // Method to encode the 'weather' column in the dataset
    public static String[] encodeWeather(String[] cleanedData) {
        StringBuilder cleanedDataBuilder = new StringBuilder();
        Map<String, Integer> WeaEncoding = new HashMap<>();
        WeaEncoding.put("sun", 0);
        WeaEncoding.put("drizzle", 1);
        WeaEncoding.put("snow", 2);
        WeaEncoding.put("fog", 3);
        WeaEncoding.put("rain", 4);

        try {
            for (String line : cleanedData) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                String[] data = line.split(",");
                StringJoiner encodedLine = new StringJoiner(",");

                for (int i = 0; i < data.length; i++) {
                    String value = data[i].trim();
                    if (i == 4) { // Assuming 'weather' is at index 4
                        Integer encodedValue = WeaEncoding.get(value);
                        if (encodedValue != null) {
                            encodedLine.add(encodedValue.toString());
                        } else {
                            // Handle unexpected values (optional)
                            System.err.println("Unexpected value in column 'weather': " + value);
                            encodedLine.add("0"); // Default encoding for unexpected value
                        }
                    } else {
                        encodedLine.add(value);
                    }
                }
                cleanedDataBuilder.append(encodedLine.toString()).append("\n");
            }
        } catch (Exception e) {
            System.err.println("Error processing data: " + e.getMessage());
            e.printStackTrace();
        }

        return cleanedDataBuilder.toString().split("\n");
    }

    // Method to split the dataset into features (X)
    public static List<String[]> splitX(String[] encodedDataList) {
        List<String[]> X = new ArrayList<>();
        for (String line : encodedDataList) {
            String[] data = line.split(",");
            String[] xRow = new String[data.length - 1];
            System.arraycopy(data, 0, xRow, 0, data.length - 1);
            X.add(xRow);
        }
        return X;
    }

    // Method to split the dataset into target values (y)
    public static List<String> splitY(String[] encodedData) {
        List<String> y = new ArrayList<>();
        for (String line : encodedData) {
            String[] data = line.split(",");
            y.add(data[data.length - 1]);
        }
        return y;
    }

    // Method to split the dataset into training and testing sets
    public static DataSplit splitTrainTest(List<String[]> X, List<String> y, double trainRatio) {
        int trainSize = (int) (X.size() * trainRatio);
        List<String[]> X_train = new ArrayList<>();
        List<String[]> X_test = new ArrayList<>();
        List<String> y_train = new ArrayList<>();
        List<String> y_test = new ArrayList<>();

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < X.size(); i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        for (int i = 0; i < trainSize; i++) {
            X_train.add(X.get(indices.get(i)));
            y_train.add(y.get(indices.get(i)));
        }
        for (int i = trainSize; i < X.size(); i++) {
            X_test.add(X.get(indices.get(i)));
            y_test.add(y.get(indices.get(i)));
        }

        return new DataSplit(X_train, X_test, y_train, y_test);
    }

    // Inner class to hold the training and testing data splits
    public static class DataSplit {
        private final List<String[]> X_train;
        private final List<String[]> X_test;
        private final List<String> y_train;
        private final List<String> y_test;

        public DataSplit(List<String[]> X_train, List<String[]> X_test, List<String> y_train, List<String> y_test) {
            this.X_train = X_train;
            this.X_test = X_test;
            this.y_train = y_train;
            this.y_test = y_test;
        }

        public List<String[]> getX_train() {
            return X_train;
        }

        public List<String[]> getX_test() {
            return X_test;
        }

        public List<String> getY_train() {
            return y_train;
        }

        public List<String> getY_test() {
            return y_test;
        }
    }
}

