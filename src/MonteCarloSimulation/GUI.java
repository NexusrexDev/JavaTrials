package MonteCarloSimulation;

public class GUI {
    //This method flushes the UI/Console
    public static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e) {}
    }

    public static void printValues(String title, int[] values, int pauseTime) {
        System.out.print(title);
        GUI.wait(pauseTime);
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);
            if (i < values.length - 1) {
                System.out.print(", ");
            }
            GUI.wait(pauseTime);
        }
        System.out.println();
    }

    public static void printValues(String title, double[] values, int pauseTime) {
        System.out.print(title);
        GUI.wait(pauseTime);
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);
            if (i < values.length - 1) {
                System.out.print(", ");
            }
            GUI.wait(pauseTime);
        }
        System.out.println();
    }

    public static void printValues(String title, double value, int pauseTime) {
        System.out.print(title);
        GUI.wait(pauseTime);
        System.out.print(value);
        System.out.println();
    }

    public static void printLine() {
        for (int i = 0; i < 10; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    public static void wait(int time) {
        try {
            Thread.sleep(time);
        } catch(Exception e) {}
    }
}
