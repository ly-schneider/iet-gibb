package ch.iet_gibb;

import java.util.ArrayList;
import java.util.List;

import ch.iet_gibb.controller.HeatCalculatorController;
import ch.iet_gibb.model.CylindricalTank;
import ch.iet_gibb.model.SquareTank;
import ch.iet_gibb.model.Tank;
import ch.iet_gibb.view.HeatCalculatorView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Levyn Schneider
 * @version 4.1.0
 */
public class HeatCalculator extends Application {
    /**
     * Main method
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        List<Tank> models = createModels();
        HeatCalculatorController controller = new HeatCalculatorController(models);
        stage.setTitle("HeatCalculator");
        HeatCalculatorView view = new HeatCalculatorView(stage, controller);
        controller.setView(view);
        controller.startView();
    }

    /**
     * Creates the models.
     * 
     * @return the models
     */
    public List<Tank> createModels() {
        List<Tank> models = new ArrayList<>();
        models.add(new SquareTank("Square Tank", 20, 20, 20, 100.0, 2.0));
        models.add(new CylindricalTank("Cylindrical Tank", 50, 20, 100.0, 2.0));

        return models;
    }
}