package InventoryControlSimulation;

import javax.swing.*;

public class outputFrame {
    JFrame frame;

    //Constructor
    outputFrame(int reorderPoint, int orderQuantity, String[] tableTitles,String[][] tableData, String[] costTitles, double[] costValues) {
        frame = new JFrame("Simulation output");
        frame.setSize(664,360);
        //Ends the program when hitting the close button
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Draw label with the controllable data
        JLabel dataLabel = new JLabel("Reorder Point = " + reorderPoint +
                "   Order Quantity = " + orderQuantity);
        dataLabel.setBounds(0,-8,300,30);
        frame.add(dataLabel);

        //Draw table
        JTable table = new JTable(tableData, tableTitles);
        table.setBounds(0,0,650,200);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,16,650,200);
        frame.add(scrollPane);

        //Draw cost values
        JLabel[] costLabels = new JLabel[5];
        for (int i = 0; i < 4; i++) {
            costLabels[i] = new JLabel(costTitles[i] + costValues[i]);
            costLabels[i].setBounds(0,216 + (i*16), 300, 30);
            frame.add(costLabels[i]);
        }

        //Create the table window
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
