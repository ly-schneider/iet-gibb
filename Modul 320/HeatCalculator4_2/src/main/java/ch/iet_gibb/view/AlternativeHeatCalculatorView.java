package ch.iet_gibb.view;

import ch.iet_gibb.controller.HeatCalculatorController;
import ch.iet_gibb.model.InterfaceTank;
import ch.iet_gibb.property.Property;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Represents an alternative view for the heat calculator application.
 * 
 * @author Ivo Graber
 * @version 1.0
 * @since 2024
 */
public class AlternativeHeatCalculatorView implements ControllerViewInterface {
    protected HeatCalculatorController controller;
    protected Stage stage;

    /**
     * Constructs an AlternativeHeatCalculatorView with the specified stage and
     * controller.
     *
     * @param stage      the stage for the view
     * @param controller the controller for handling actions
     */
    public AlternativeHeatCalculatorView(Stage stage, HeatCalculatorController controller) {
        this.controller = controller;
        this.stage = stage;
        stage.setTitle("HeatCalculator");
    }

    /**
     * Starts the view with the specified tank data.
     *
     * @param data the tank data to display
     */
    @Override
    public void startView(InterfaceTank data) {
        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(10);

        Text labelText = getLabelNode(data.getTitle());
        layout.getChildren().add(labelText);

        VBox propertiesLayout = new VBox();
        data.getProperties().forEach(property -> addPropertyToPane(property, propertiesLayout));
        layout.getChildren().add(propertiesLayout);

        Button btnNext = new Button("Next");
        btnNext.setOnAction(controller);
        layout.getChildren().add(btnNext);

        Scene scene = new Scene(layout, 600, 300);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates a text node for the label with the specified title.
     *
     * @param title the title of the label
     * @return the text node for the label
     */
    protected Text getLabelNode(String title) {
        Text labelText = new Text();
        labelText.setText(title);
        labelText.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 20));
        labelText.setStyle(
                "-fx-font-family: monospace;-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);");
        return labelText;
    }

    /**
     * Adds a property to the specified pane.
     *
     * @param property the property to add
     * @param layout   the pane to add the property to
     */
    protected void addPropertyToPane(Property property, Pane layout) {
        Font propertiesFont = Font.font("Tahoma", FontWeight.BOLD, 16);

        Text keyText = new Text();
        keyText.setText(property.getKey() + " = " + property.getValue());
        keyText.setFont(propertiesFont);
        keyText.setWrappingWidth(400);
        keyText.setTextAlignment(TextAlignment.LEFT);
        layout.getChildren().add(keyText);
    }
}
