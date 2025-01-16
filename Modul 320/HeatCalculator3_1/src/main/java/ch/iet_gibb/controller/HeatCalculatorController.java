package ch.iet_gibb.controller;

import ch.iet_gibb.model.CylindricalTank;
import ch.iet_gibb.model.SquareTank;
import ch.iet_gibb.model.Tank;
import ch.iet_gibb.view.HeatCalculatorView;

import java.util.ArrayList;

/**
 * @author Levyn Schneider
 * @version 1.0.0
 */
public class HeatCalculatorController {
    private final HeatCalculatorView view; // Instance of the HeatCalculatorView class

    /**
     * Default constructor
     */
    public HeatCalculatorController() {
        /* Square Tank setup */
        SquareTank squareTank = new SquareTank("Square Tank", 20, 20, 20, 100.0, 2.0);

        /* Cylindrical Tank setup */
        CylindricalTank cylindricalTank = new CylindricalTank("Cylindrical Tank", 50, 20, 100.0, 2.0);

        ArrayList<Tank> models = new ArrayList<>();
        models.add(squareTank);
        models.add(cylindricalTank);

        /* Initialize the view */
        this.view = new HeatCalculatorView(models);
    }

    /**
     * Run the controller
     */
    public void run() {
        view.display();
    }
}
