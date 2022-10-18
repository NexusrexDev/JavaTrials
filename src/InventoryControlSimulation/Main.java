package InventoryControlSimulation;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int demandSize, leadTimeSize, simDays, simReorder, simQuantity;
        Simulation sim;
        Table demandTable, leadTimeTable;
        Scanner input = new Scanner(System.in);

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

        System.out.print("Enter the number of days to simulate: ");
        simDays = input.nextInt();
        System.out.print("Enter the reordering point: ");
        simReorder = input.nextInt();
        System.out.print("Enter the order quantity: ");
        simQuantity = input.nextInt();

        sim = new Simulation(simDays, simReorder, simQuantity, demandTable, leadTimeTable);
        sim.startSimulation();

        for (String[] strings : sim.toTable()) {
            System.out.println(Arrays.deepToString(strings));
        }

        int days;
        double orderCost, holdCost, lossCost;

        System.out.print("Enter the number of active days per year: ");
        days = input.nextInt();
        System.out.print("Enter the ordering cost: ");
        orderCost = input.nextInt();
        System.out.print("Enter the holding cost (per year): ");
        holdCost = input.nextInt();
        System.out.print("Enter the lost sales cost: ");
        lossCost = input.nextInt();

        String[] str = {"Daily order cost: ", "Daily holding cost: ", "Daily stockout cost: ",
                "Total daily inventory cost: ", "Total yearly inventory cost: "};
        double[] costs = sim.calculateCosts(days, orderCost, holdCost, lossCost);
        for (int i = 0; i < 4; i++) {
            System.out.println(str[i] + costs[i]);
        }
    }
}
