// package com.example;

// import weka.classifiers.Classifier;
// import weka.classifiers.evaluation.Evaluation;
// import weka.classifiers.trees.J48;
// import weka.core.Instances;
// import weka.core.converters.CSVLoader;
// import weka.filters.Filter;
// import weka.filters.unsupervised.attribute.NumericToNominal;

// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.List;
// import java.util.Random;

// public class DecisionTreeClassifier {

//     private Classifier tree;
//     private Instances trainData;
//     private Instances testData;

//     public void loadTrainingData(String filePath) throws Exception {
//         CSVLoader loader = new CSVLoader();
//         loader.setSource(new File(filePath));
//         this.trainData = loader.getDataSet();
//         this.trainData.setClassIndex(trainData.numAttributes() - 1); // Set the class attribute
//         this.trainData = convertNumericClassToNominal(this.trainData); // Convert class to nominal
//     }

//     public void loadTestData(String filePath) throws Exception {
//         CSVLoader loader = new CSVLoader();
//         loader.setSource(new File(filePath));
//         this.testData = loader.getDataSet();
//         this.testData.setClassIndex(testData.numAttributes() - 1); // Set the class attribute
//         this.testData = convertNumericClassToNominal(this.testData); // Convert class to nominal
//     }

//     public void train() throws Exception {
//         this.tree = new J48();
//         this.tree.buildClassifier(trainData);
//     }

//     public void evaluate() throws Exception {
//         Evaluation eval = new Evaluation(trainData);
//         eval.evaluateModel(tree, testData);

//         System.out.println("Evaluation results:");
//         System.out.println(eval.toSummaryString());
//         System.out.println("Confusion Matrix:");
//         double[][] confusionMatrix = eval.confusionMatrix();
//         for (double[] row : confusionMatrix) {
//             for (double value : row) {
//                 System.out.print(value + " ");
//             }
//             System.out.println();
//         }
//         System.out.println(eval.toClassDetailsString());
//         System.out.println(eval.toMatrixString());
//     }

//     private Instances convertNumericClassToNominal(Instances data) throws Exception {
//         NumericToNominal convert = new NumericToNominal();
//         convert.setAttributeIndices("last"); // Convert the last attribute (class attribute)
//         convert.setInputFormat(data);
//         return Filter.useFilter(data, convert);
//     }

//     public static void saveToCSV(List<String[]> X, List<String> y, String filePath) throws IOException {
//         try (FileWriter writer = new FileWriter(filePath)) {
//             for (int i = 0; i < X.size(); i++) {
//                 String[] row = X.get(i);
//                 String line = String.join(",", row) + "," + y.get(i);
//                 writer.write(line + "\n");
//             }
//         }
//     }
// }

package com.example;

import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class DecisionTreeClassifier {

    private Classifier tree;
    private Instances trainData;
    private Instances testData;

    // Method to load training data from a CSV file
    public void loadTrainingData(String filePath) throws Exception {
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(filePath));
        this.trainData = loader.getDataSet();
        this.trainData.setClassIndex(trainData.numAttributes() - 1); // Set the class attribute
        this.trainData = convertNumericClassToNominal(this.trainData); // Convert class to nominal
    }

    // Method to load test data from a CSV file
    public void loadTestData(String filePath) throws Exception {
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(filePath));
        this.testData = loader.getDataSet();
        this.testData.setClassIndex(testData.numAttributes() - 1); // Set the class attribute
        this.testData = convertNumericClassToNominal(this.testData); // Convert class to nominal
    }

    // Method to train the decision tree classifier using the training data
    public void train() throws Exception {
        this.tree = new J48(); // Initialize the J48 decision tree classifier
        this.tree.buildClassifier(trainData); // Build the classifier with the training data
    }

    // Method to evaluate the decision tree classifier using the test data
    public void evaluate() throws Exception {
        Evaluation eval = new Evaluation(trainData); // Initialize the evaluation with the training data
        eval.evaluateModel(tree, testData); // Evaluate the model with the test data

        // Print evaluation results
        System.out.println("Evaluation results:");
        System.out.println(eval.toSummaryString());
        System.out.println("Confusion Matrix:");
        double[][] confusionMatrix = eval.confusionMatrix();
        for (double[] row : confusionMatrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println(eval.toClassDetailsString());
        System.out.println(eval.toMatrixString());
    }

    // Method to convert the class attribute from numeric to nominal
    private Instances convertNumericClassToNominal(Instances data) throws Exception {
        NumericToNominal convert = new NumericToNominal();
        convert.setAttributeIndices("last"); // Convert the last attribute (class attribute)
        convert.setInputFormat(data);
        return Filter.useFilter(data, convert);
    }

    // Method to save data to a CSV file
    public static void saveToCSV(List<String[]> X, List<String> y, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < X.size(); i++) {
                String[] row = X.get(i);
                String line = String.join(",", row) + "," + y.get(i);
                writer.write(line + "\n");
            }
        }
    }
}
