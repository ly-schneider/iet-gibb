package ch.iet_gibb;

import ch.iet_gibb.controller.HeatCalculatorController;

/**
 * @author Levyn Schneider
 * @version 3.0.0
 */
public class HeatCalculator {
    /**
     * Main method
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        HeatCalculatorController controller = new HeatCalculatorController();
        controller.run();
    }
}