package InventoryControlSimulation;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int demandSize, leadTimeSize, simDays, simReorder, simQuantity, days;
        double orderCost, holdCost, lossCost;
        Simulation sim;
        Table demandTable, leadTimeTable;
        Scanner input = new Scanner(System.in);

        //Demand table
        System.out.print("Enter the demand table size: ");
        demandSize = input.nextInt();
        demandTable = new Table(demandSize);
        int[] demand = new int[demandSize];
        int[] freq = new int[demandSize];
        System.out.println("Enter the data in the form (Demand Freqency): ");
        for (int i = 0; i < demandSize; i++) {
            demand[i] = input.nextInt();
            freq[i] = input.nextInt();
        }
        demandTable.enterValue(demand, freq);

        //Lead time table
        System.out.print("Enter the lead time table size: ");
        leadTimeSize = input.nextInt();
        leadTimeTable = new Table(leadTimeSize);
        int[] leadTime = new int[leadTimeSize];
        int[] orders = new int[leadTimeSize];
        System.out.println("Enter the data in the form (LeadTime Orders): ");
        for (int i = 0; i < leadTimeSize; i++) {
            leadTime[i] = input.nextInt();
            orders[i] = input.nextInt();
        }
        leadTimeTable.enterValue(leadTime, orders);

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
}
