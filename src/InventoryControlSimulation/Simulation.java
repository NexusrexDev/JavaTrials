package InventoryControlSimulation;

public class Simulation {
    //Variables
    Table demand, leadTime;
    boolean hasOrder;
    int reorderPoint, orderQuantity, rowCount, expectedOrder;
    int[] receivedUnits, beginningInv, endingInv, lostSales, simDemand, simLeadTime;
    int[][] randomNumbers;

    double[] averageValues;
    boolean[] orderMade;

    //Constructor
    Simulation(int days, int givenReorder, int givenQuantity, Table givenDemand, Table givenLeadTime) {
        //This is truly terrible, an educated programmer would slap me lmao
        demand = givenDemand;
        leadTime = givenLeadTime;
        reorderPoint = givenReorder;
        orderQuantity = givenQuantity;
        receivedUnits = new int[days];
        beginningInv = new int[days];
        endingInv = new int[days];
        lostSales = new int[days];
        simDemand = new int[days];
        simLeadTime = new int[days];
        randomNumbers = new int[days][2];
        averageValues = new double[3];
        orderMade = new boolean[days];
        rowCount = days;
        hasOrder = false;
        expectedOrder = 0;
    }

    //Methods
    public void startSimulation() {
        int endingInvCount = 0, lostSalesCount = 0, ordersCount = 0;
        for (int i = 0; i < rowCount; i++) {
            //Units received
            if (hasOrder && i == expectedOrder) {
                receivedUnits[i] = orderQuantity;
                hasOrder = false;
            } else {
                receivedUnits[i] = 0;
            }

            //Beginning inv
            if (i == 0) {
                beginningInv[i] = orderQuantity;
            } else {
                beginningInv[i] = endingInv[i-1] + receivedUnits[i];
            }

            //Demand simulation
            randomNumbers[i][0] = generateRandom();
            simDemand[i] = demand.findClass(randomNumbers[i][0]);

            //Ending inv and sales lost
            if (beginningInv[i] - simDemand[i] < 0) {
                endingInv[i] = 0;
                lostSales[i] = Math.abs(beginningInv[i] - simDemand[i]);
            } else {
                endingInv[i] = beginningInv[i] - simDemand[i];
            }
            endingInvCount += endingInv[i];
            lostSalesCount += lostSales[i];

            //Order
            if (endingInv[i] <= reorderPoint && !hasOrder) {
                orderMade[i] = true;
                ordersCount++;

                //Lead time simulation
                randomNumbers[i][1] = generateRandom();
                simLeadTime[i] = leadTime.findClass(randomNumbers[i][1]);

                hasOrder = true;
                expectedOrder = i + simLeadTime[i] + 1;
            } else {
                orderMade[i] = false;
                randomNumbers[i][1] = -1;
                simLeadTime[i] = -1;
            }
        }

        ////Calculating average values////
        //Average ending inventory
        averageValues[0] = Misc.fixValue((double)endingInvCount / rowCount);
        //Average lost sales
        averageValues[1] = Misc.fixValue((double)lostSalesCount / rowCount);
        //Average placed orders
        averageValues[2] = Misc.fixValue((double)ordersCount / rowCount);
    }

    public double[] calculateCosts(int daysCount, double orderCost, double holdCost, double lossCost) {
        double[] costs = new double[5];

        //Daily order cost
        costs[0] = Misc.fixValue(orderCost * averageValues[2]);
        //Daily holding cost
        costs[1] = Misc.fixValue((holdCost / daysCount) * averageValues[0]);
        //Daily stockout cost
        costs[2] = Misc.fixValue(lossCost * averageValues[1]);
        //Total daily inventory cost
        costs[3] = Misc.fixValue(costs[0] + costs[1] + costs[2]);
        //Total yearly inventory cost
        costs[4] = Misc.fixValue(costs[3] * daysCount);

        return costs;
    }

    public String[][] toTable() {
        String[][] tableOutput = new String[rowCount][10];
        for (int i = 0; i < rowCount; i++) {
            tableOutput[i][0] = (i + 1) + "";
            tableOutput[i][1] = receivedUnits[i] + "";
            tableOutput[i][2] = beginningInv[i] + "";
            tableOutput[i][3] = randomNumbers[i][0] + "";
            tableOutput[i][4] = simDemand[i] + "";
            tableOutput[i][5] = endingInv[i] + "";
            tableOutput[i][6] = lostSales[i] + "";
            if (orderMade[i]) {
                tableOutput[i][7] = "Yes";
                tableOutput[i][8] = randomNumbers[i][1] + "";
                tableOutput[i][9] = simLeadTime[i] + "";
            } else {
                tableOutput[i][7] = "No";
                tableOutput[i][8] = "";
                tableOutput[i][9] = "";
            }
        }
        return tableOutput;
    }

    private int generateRandom() {
        return (int)(Math.random() * 100) + 1;
    }
}
