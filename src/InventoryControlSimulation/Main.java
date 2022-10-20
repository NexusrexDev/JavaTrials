package InventoryControlSimulation;

import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int simDays, simReorder, simQuantity, days;
        double orderCost, holdCost, lossCost;
        Simulation sim;
        Table demandTable = new Table();
        Table leadTimeTable = new Table();

        //Demand table
        tableFill(demandTable, "demand table", "demand", "frequency");

        //Lead time table
        tableFill(leadTimeTable, "lead time table", "leadtime", "frequency");

        //Controllable variables
        System.out.print("Enter the number of days to simulate: ");
        simDays = input.nextInt();
        System.out.print("Enter the reordering point: ");
        simReorder = input.nextInt();
        System.out.print("Enter the order quantity: ");
        simQuantity = input.nextInt();

        //Simulation starting and output
        sim = new Simulation(simDays, simReorder, simQuantity, demandTable, leadTimeTable);
        sim.startSimulation();
        String[][] simulationOutput = sim.toTable();

        //Cost calculation
        System.out.print("Enter the number of active days per year: ");
        days = input.nextInt();
        System.out.print("Enter the ordering cost: ");
        orderCost = input.nextInt();
        System.out.print("Enter the holding cost (per year): ");
        holdCost = input.nextInt();
        System.out.print("Enter the lost sales cost: ");
        lossCost = input.nextInt();

        double[] costs = sim.calculateCosts(days, orderCost, holdCost, lossCost);

        //Output
        final String[] columnNames = {"Days", "Units Received", "Beginning Inventory", "Random Number",
                "Demand", "Ending Inventory", "Lost Sales", "Order", "Random Number", "Lead Time"};
        final String[] costNames = {"Daily order cost = ", "Daily holding cost = ",
                "Daily stockout cost = ", "Total daily inventory cost = ", "Total yearly inventory cost = "};

        new outputFrame(simReorder, simQuantity, columnNames, simulationOutput, costNames, costs);
    }

    static void tableFill(Table tableRef, String tableName, String className, String freqName) {
        //Abstract table filling
        int tableSize;
        int[] classValues, freqValues;
        System.out.print("Enter the " + tableName + "size: ");
        tableSize = input.nextInt();
        tableRef.setSize(tableSize);
        classValues = new int[tableSize];
        freqValues = new int[tableSize];
        System.out.println("Enter the data in the form of (" + className + " " + freqName + "):");
        for (int i = 0; i < tableSize; i++) {
            classValues[i] = input.nextInt();
            freqValues[i] = input.nextInt();
        }
        tableRef.enterValue(classValues, freqValues);
    }
}
