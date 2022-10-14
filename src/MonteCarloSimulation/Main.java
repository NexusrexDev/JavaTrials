package MonteCarloSimulation;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Vars
        Table t;
        Scanner input = new Scanner(System.in);
        int tableSize, simCount;
        double expectedValue, simulatedAverage = 0;
        int[] randomValues, simulatedValues;
        int[][] values, tableValues, tableRandomIntervals;

        //Size setting
        System.out.print("Enter the table size: ");
        tableSize = input.nextInt();
        values = new int[2][tableSize];
        //Table entry
        System.out.println("Enter the table in the format (Class Frequency)");
        for (int i = 0; i < tableSize; i++) {
            values[0][i] = input.nextInt();
            values[1][i] = input.nextInt();
        }

        //Table variable setting
        t = new Table(tableSize);
        t.setGivenValues(values);
        //Print table values, *d r a m a t i c a l l y*
        tableValues = t.getGivenValues();
        tableRandomIntervals = t.getRandomIntervals();
        GUI.printLine();
        //Table
        GUI.printValues("Class: ", tableValues[0], 200);
        GUI.printValues("Frequency: ", tableValues[1], 200);
        GUI.printLine();
        //Prob. of Occurrence
        GUI.printValues("Prob. of Occurrence: ", t.getProbOfOccurrence(), 200);
        GUI.printLine();
        //Cumulative prob.
        GUI.printValues("Cumulative prob.: ", t.getCumulativeProb(), 200);
        GUI.printLine();
        //Random Number Intervals
        GUI.printValues("Min. Interval: ", tableRandomIntervals[0], 200);
        GUI.printValues("Max. Interval: ", tableRandomIntervals[1], 200);
        GUI.printLine();

        //Random number generation and simulation
        System.out.println("Enter the number of simulation times: ");
        simCount = input.nextInt();
        randomValues = new int[simCount];
        simulatedValues = new int[simCount];
        for (int i = 0; i < simCount; i++) {
            //Random number creation
            randomValues[i] = (int)(Math.random() * 100) + 1;
            //Checking
            simulatedValues[i] = t.findClass(randomValues[i]);
        }
        //Printing values
        GUI.printLine();
        GUI.printValues("Random values: ", randomValues, 200);
        GUI.printValues("Simulated values: ", simulatedValues, 200);
        GUI.printLine();

        //Expected and simulated values
        expectedValue = t.getExpectedValue();
        for (int i = 0; i < simCount; i++) {
            simulatedAverage += simulatedValues[i];
        }
        simulatedAverage = simulatedAverage / simCount;
        GUI.printValues("Avg. Simulated value: ", simulatedAverage, 200);
        GUI.printValues("Expected value: ", expectedValue, 200);
    }
}
