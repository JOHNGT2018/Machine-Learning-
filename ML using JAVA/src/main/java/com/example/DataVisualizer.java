// package com.example;

// import org.jfree.chart.ChartFactory;
// import org.jfree.chart.ChartPanel;
// import org.jfree.chart.JFreeChart;
// import org.jfree.chart.plot.PlotOrientation;
// import org.jfree.data.category.DefaultCategoryDataset;
// import org.jfree.data.general.DefaultPieDataset;
// import org.jfree.data.xy.DefaultXYDataset;

// import javax.swing.*;
// import java.awt.*;
// import java.util.List;
// import java.util.HashMap;
// import java.util.Map;

// public class DataVisualizer {

//     public static void visualizeData(List<String[]> cleanedData, String feature, String frameTitle, String chartType) {
        
//         SwingUtilities.invokeLater(() -> {
//             JFreeChart chart = null;
//             switch (chartType) {
//                 case "bar":
//                     chart = createBarChart(createBarDataset(cleanedData, feature), feature);
//                     break;
//                 case "line":
//                     chart = createLineChart(createLineDataset(cleanedData, feature), feature);
//                     break;
//                 case "pie":
//                     chart = createPieChart(createPieDataset(cleanedData, feature), feature);
//                     break;
//                 default:
                    
//                     break;
//             }

//             if (chart != null) {
//                 ChartPanel chartPanel = new ChartPanel(chart);
//                 chartPanel.setPreferredSize(new Dimension(800, 600));

//                 JFrame frame = new JFrame(frameTitle);
//                 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                 frame.getContentPane().add(chartPanel);
//                 frame.pack();
//                 frame.setVisible(true);
//             }
//         });
//     }


//     private static boolean isNumericFeature(List<String[]> cleanedData, String feature) {
//         int featureIndex = getFeatureIndex(feature);
//         if (featureIndex == -1) return false;
    
//         // Check if the feature is numeric based on column name
//         switch (feature) {
//             case "precipitation":
//             case "temp_max":
//             case "temp_min":
//             case "wind":
//                 return true;
//             default:
//                 return false;
//         }
//     }
    

//     private static DefaultXYDataset createScatterDataset(List<String[]> cleanedData, String feature) {
//         DefaultXYDataset dataset = new DefaultXYDataset();
//         int featureIndexX = getFeatureIndex(feature);
//         int featureIndexY = 1; // Replace with actual index of a numeric feature if available

//         if (featureIndexX != -1 && featureIndexY != -1) {
//             double[][] data = new double[2][cleanedData.size()];
//             for (int i = 0; i < cleanedData.size(); i++) {
//                 data[0][i] = Double.parseDouble(cleanedData.get(i)[featureIndexX]);
//                 data[1][i] = Double.parseDouble(cleanedData.get(i)[featureIndexY]);
//             }
//             dataset.addSeries(feature, data);
//         }

//         return dataset;
//     }

//     private static DefaultCategoryDataset createBarDataset(List<String[]> cleanedData, String feature) {
//         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//         int featureIndex = getFeatureIndex(feature);

//         if (featureIndex != -1) {
//             Map<String, Integer> frequencyMap = new HashMap<>();
//             for (String[] data : cleanedData) {
//                 String featureValue = data[featureIndex];
//                 frequencyMap.put(featureValue, frequencyMap.getOrDefault(featureValue, 0) + 1);
//             }
//             for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
//                 dataset.addValue(entry.getValue(), "Count", entry.getKey());
//             }
//         }

//         return dataset;
//     }

//     private static DefaultCategoryDataset createLineDataset(List<String[]> cleanedData, String feature) {
//         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//         int featureIndex = getFeatureIndex(feature);

//         if (featureIndex != -1) {
//             Map<String, Integer> frequencyMap = new HashMap<>();
//             for (String[] data : cleanedData) {
//                 String featureValue = data[featureIndex];
//                 frequencyMap.put(featureValue, frequencyMap.getOrDefault(featureValue, 0) + 1);
//             }
//             for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
//                 dataset.addValue(entry.getValue(), "Count", entry.getKey());
//             }
//         }

//         return dataset;
//     }

//     private static DefaultPieDataset createPieDataset(List<String[]> cleanedData, String feature) {
//         DefaultPieDataset dataset = new DefaultPieDataset();
//         int featureIndex = getFeatureIndex(feature);

//         if (featureIndex != -1) {
//             Map<String, Integer> frequencyMap = new HashMap<>();
//             for (String[] data : cleanedData) {
//                 String featureValue = data[featureIndex];
//                 frequencyMap.put(featureValue, frequencyMap.getOrDefault(featureValue, 0) + 1);
//             }
//             for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
//                 dataset.setValue(entry.getKey(), entry.getValue());
//             }
//         }

//         return dataset;
//     }

//     private static int getFeatureIndex(String feature) {
//         // Adjust this method according to your dataset's column names
//         switch (feature) {
//             case "weather":
//                 return 5; // Assuming "weather" is in the 6th column (index 5)
//             // Add cases for other features here
//             default:
//                 System.err.println("Invalid feature.");
//                 return -1;
//         }
//     }

//     private static JFreeChart createScatterChart(DefaultXYDataset dataset, String feature) {
//         return ChartFactory.createScatterPlot(
//                 "Scatter Plot",
//                 feature,
//                 "Numeric Feature",
//                 dataset,
//                 PlotOrientation.VERTICAL,
//                 true,
//                 true,
//                 false
//         );
//     }

//     private static JFreeChart createBarChart(DefaultCategoryDataset dataset, String feature) {
//         return ChartFactory.createBarChart(
//                 "Bar Chart",
//                 feature,
//                 "Frequency",
//                 dataset,
//                 PlotOrientation.VERTICAL,
//                 true,
//                 true,
//                 false
//         );
//     }

//     private static JFreeChart createLineChart(DefaultCategoryDataset dataset, String feature) {
//         return ChartFactory.createLineChart(
//                 "Line Chart",
//                 feature,
//                 "Frequency",
//                 dataset,
//                 PlotOrientation.VERTICAL,
//                 true,
//                 true,
//                 false
//         );
//     }

//     private static JFreeChart createPieChart(DefaultPieDataset dataset, String feature) {
//         return ChartFactory.createPieChart(
//                 "Pie Chart",
//                 dataset,
//                 true,
//                 true,
//                 false
//         );
//     }
// }

package com.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class DataVisualizer {

    // Method to visualize data using JFreeChart
    public static void visualizeData(List<String[]> cleanedData, String feature, String frameTitle, String chartType) {
        
        SwingUtilities.invokeLater(() -> {
            JFreeChart chart = null;
            switch (chartType) {
                case "bar":
                    chart = createBarChart(createBarDataset(cleanedData, feature), feature);
                    break;
                case "line":
                    chart = createLineChart(createLineDataset(cleanedData, feature), feature);
                    break;
                case "pie":
                    chart = createPieChart(createPieDataset(cleanedData, feature), feature);
                    break;
                default:
                    
                    break;
            }

            if (chart != null) {
                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new Dimension(800, 600));

                JFrame frame = new JFrame(frameTitle);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(chartPanel);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    // Method to create a bar dataset from cleaned data for visualization
    private static DefaultCategoryDataset createBarDataset(List<String[]> cleanedData, String feature) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int featureIndex = getFeatureIndex(feature);

        if (featureIndex != -1) {
            Map<String, Integer> frequencyMap = new HashMap<>();
            for (String[] data : cleanedData) {
                String featureValue = data[featureIndex];
                frequencyMap.put(featureValue, frequencyMap.getOrDefault(featureValue, 0) + 1);
            }
            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                dataset.addValue(entry.getValue(), "Count", entry.getKey());
            }
        }

        return dataset;
    }

    // Method to create a line dataset from cleaned data for visualization
    private static DefaultCategoryDataset createLineDataset(List<String[]> cleanedData, String feature) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int featureIndex = getFeatureIndex(feature);

        if (featureIndex != -1) {
            Map<String, Integer> frequencyMap = new HashMap<>();
            for (String[] data : cleanedData) {
                String featureValue = data[featureIndex];
                frequencyMap.put(featureValue, frequencyMap.getOrDefault(featureValue, 0) + 1);
            }
            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                dataset.addValue(entry.getValue(), "Count", entry.getKey());
            }
        }

        return dataset;
    }

    // Method to create a pie dataset from cleaned data for visualization
    private static DefaultPieDataset createPieDataset(List<String[]> cleanedData, String feature) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int featureIndex = getFeatureIndex(feature);

        if (featureIndex != -1) {
            Map<String, Integer> frequencyMap = new HashMap<>();
            for (String[] data : cleanedData) {
                String featureValue = data[featureIndex];
                frequencyMap.put(featureValue, frequencyMap.getOrDefault(featureValue, 0) + 1);
            }
            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                dataset.setValue(entry.getKey(), entry.getValue());
            }
        }

        return dataset;
    }

    // Method to get the index of the specified feature in the dataset
    private static int getFeatureIndex(String feature) {
        // Adjust this method according to your dataset's column names
        switch (feature) {
            case "weather":
                return 5; // Assuming "weather" is in the 6th column (index 5)
            // Add cases for other features here
            default:
                System.err.println("Invalid feature.");
                return -1;
        }
    }

    // Method to create a bar chart visualization
    private static JFreeChart createBarChart(DefaultCategoryDataset dataset, String feature) {
        return ChartFactory.createBarChart(
                "Bar Chart",
                feature,
                "Frequency",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    // Method to create a line chart visualization
    private static JFreeChart createLineChart(DefaultCategoryDataset dataset, String feature) {
        return ChartFactory.createLineChart(
                "Line Chart",
                feature,
                "Frequency",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    // Method to create a pie chart visualization
    private static JFreeChart createPieChart(DefaultPieDataset dataset, String feature) {
        return ChartFactory.createPieChart(
                "Pie Chart",
                dataset,
                true,
                true,
                false
        );
    }
}
