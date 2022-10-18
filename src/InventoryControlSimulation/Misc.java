package InventoryControlSimulation;

public class Misc {
    public static double fixValue(double value) {
        //Fixes numbers to .2f type format
        return (int)(value * 100) / 100.0;
    }
}
