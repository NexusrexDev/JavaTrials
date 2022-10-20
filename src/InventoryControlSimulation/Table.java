package InventoryControlSimulation;

import java.util.ArrayList;

public class Table {
    //Variables
    private int[] classValues, freqValues, intervalEnd;
    private double[] probOfOccurrence, cumulativeProb;
    private int totalFreq = 0;

    //Methods
    public void setSize(int tableSize) {
        classValues = new int[tableSize];
        freqValues = new int[tableSize];
        intervalEnd = new int[tableSize];
        probOfOccurrence = new double[tableSize];
        cumulativeProb = new double[tableSize];
    }

    public void enterValue(int[] givenClass, int[] givenFreq) {
        for (int i = 0; i < givenClass.length; i++) {
            //Setting values
            classValues[i] = givenClass[i];
            freqValues[i] = givenFreq[i];
            totalFreq += givenFreq[i];
        }
        for (int i = 0; i < givenClass.length; i++) {
            //Calculating the probability of occurrence
            probOfOccurrence[i] = Misc.fixValue((double)freqValues[i] / totalFreq);
            //Calculating the cumulative probability
            if (i == 0) {
                cumulativeProb[i] = probOfOccurrence[i];
            } else {
                cumulativeProb[i] = Misc.fixValue(cumulativeProb[i-1] + probOfOccurrence[i]);
            }
            //Calculating the interval end
            intervalEnd[i] = (int)(cumulativeProb[i] * 100);
        }
    }

    public int findClass(int randomValue) {
        int foundClass = -1;
        for (int i = 0; i < intervalEnd.length; i++) {
            if (i == 0) {
                if (randomValue >= 1 && randomValue <= intervalEnd[i]) {
                    foundClass = classValues[i];
                }
            } else {
                if (randomValue >= intervalEnd[i-1] + 1 && randomValue <= intervalEnd[i]) {
                    foundClass = classValues[i];
                }
            }
        }
        return foundClass;
    }

    //Getters
    public double[] getCumulativeProb() {
        return cumulativeProb;
    }

    public double[] getProbOfOccurrence() {
        return probOfOccurrence;
    }

    public int getTotalFreq() {
        return totalFreq;
    }

    public int[] getClassValues() {
        return classValues;
    }

    public int[] getFreqValues() {
        return freqValues;
    }

    public int[] getIntervalEnd() {
        return intervalEnd;
    }
}
