package ch.iet_gibb;

import ch.iet_gibb.model.CylindricalTank;
import ch.iet_gibb.model.SquareTank;
import ch.iet_gibb.model.Tank;

/**
 * @author Levyn Schneider
 * @version 1.0.2
 * @since 1.0.0
 */
public class Main {
    /**
     * Main method
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SquareTank squareTank = new SquareTank("Square Tank", 10.0, 30.0, 20.0, 100.0, 2.0);
        System.out.println(squareTank);

        /* Cylindrical Tank setup */
        CylindricalTank cylindricalTank = new CylindricalTank("Cylindrical Tank", 10.0, 30.0, 100.0, 2.0);
        System.out.println("\n" + cylindricalTank);

        /* Tank setup */
        Tank tank = new Tank("Tank", 1000.0, 100.0, 2.0);
        System.out.println("\n" + tank);
    }
}