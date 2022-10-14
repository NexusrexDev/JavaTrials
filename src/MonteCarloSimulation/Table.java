package MonteCarloSimulation;

public class Table {
    private int[][] givenValues, randomIntervals;
    private int totalFreq;
    private double[] probOfOccurrence, cumulativeProb;

    private double expectedValue;

    Table(int tableSize) {
        givenValues = new int[2][tableSize];
        randomIntervals = new int[2][tableSize];
        probOfOccurrence = new double[tableSize];
        cumulativeProb = new double[tableSize];
        totalFreq = 0;
        expectedValue = 0;
    }

    public void setGivenValues(int[][] values) {
        //Setting the values
        givenValues = values;
        //Setting the total frequency
        for (int i = 0; i < givenValues[0].length; i++) {
            totalFreq += givenValues[1][i];
        }
        //Setting the prob. of occurrence
        setProbOfOccurrence();
        //Setting the cumulative prob.
        setCumulativeProb();
        //Setting the random intervals
        setRandomIntervals();
        //Setting the expected value
        setExpectedValue();
    }

    private void setProbOfOccurrence() {
        /**Calculates the probability of occurrence by dividing the given values by the total frequency*/
        for (int i = 0; i < probOfOccurrence.length; i++) {
            probOfOccurrence[i] = (double)givenValues[1][i] / totalFreq;
        }
    }

    private void setCumulativeProb() {
        /**Calculates the cumulative probability w/ sum_prev + current_prob*/
        //First cumulative prob. is the first prob. of occurrence
        cumulativeProb[0] = probOfOccurrence[0];
        for (int i = 1; i < cumulativeProb.length; i++) {
            cumulativeProb[i] = cumulativeProb[i-1] + probOfOccurrence[i];
        }
        //Fixing the values to a .2 decimal
        for (int i = 0; i < cumulativeProb.length; i++) {
            cumulativeProb[i] = ((int)(cumulativeProb[i] * 100) / 100.0);
        }
    }

    private void setRandomIntervals() {
        /**Calculates the random intervals (index 0 being the minimum and 1 being the maximum)
         * through multiplying the cumulative prob. by 100.
         */
        //First variable's interval is always 1
        randomIntervals[0][0] = 1;
        randomIntervals[1][0] = (int)(cumulativeProb[0] * 100);
        for (int i = 1; i < randomIntervals[0].length; i++) {
            //Minimum limit = previous' maximum + 1
            randomIntervals[0][i] = randomIntervals[1][i-1] + 1;
            //Maximum limit = cumulative prob. * 100
            randomIntervals[1][i] = (int)(cumulativeProb[i] * 100);
        }
    }

    private void setExpectedValue() {
        for (int i = 0; i < probOfOccurrence.length; i++) {
            expectedValue += probOfOccurrence[i] * givenValues[0][i];
        }
    }

    public int findClass(int randomValue) {
        /**Finds the class through given random value
         * by searching in every random range
         */
        for (int i = 0; i < givenValues[0].length; i++) {
            if (randomValue >= randomIntervals[0][i] && randomValue <= randomIntervals[1][i]) {
                return i;
            }
        }
        return -1;
    }

    public int[][] getGivenValues() { return givenValues; }

    public double[] getProbOfOccurrence() {
        return probOfOccurrence;
    }

    public double[] getCumulativeProb() {
        return cumulativeProb;
    }

    public int[][] getRandomIntervals() {
        return randomIntervals;
    }

    public double getExpectedValue() { return expectedValue; }
}
