package ch.iet_gibb.controller;

import java.util.List;

import ch.iet_gibb.model.Tank;
import ch.iet_gibb.view.HeatCalculatorView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Levyn Schneider
 * @version 4.1.0
 */
public class HeatCalculatorController implements EventHandler<ActionEvent> {
    protected List<Tank> models;
    protected HeatCalculatorView view;
    protected int currentModel = 0;

    /**
     * Default constructor
     * 
     * @param models
     */
    public HeatCalculatorController(List<Tank> models) {
        this.models = models;
    }

    public void setView(HeatCalculatorView view) {
        this.view = view;
    }

    public void startView() {
        view.startView(models.get(currentModel));
    }

    public void showNextLabel() {
        if (currentModel < models.size() - 1) {
            currentModel++;
        } else {
            currentModel = 0;
        }

        view.startView(models.get(currentModel));
    }

    public String getModel() {
        return models.get(currentModel).toString();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        showNextLabel();
    }
}
