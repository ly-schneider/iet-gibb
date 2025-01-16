package ch.iet_gibb.view;

import ch.iet_gibb.controller.HeatCalculatorController;
import ch.iet_gibb.model.InterfaceTank;
import ch.iet_gibb.property.Property;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * @author Levyn Schneider
 * @version 4.1.0
 */
public class HeatCalculatorView {
    private Stage stage;
    private HeatCalculatorController controller;

    /**
     * Constructor for the HeatCalculatorView class.
     * 
     * @param stage
     * @param controller
     */
    public HeatCalculatorView(Stage stage, HeatCalculatorController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    /**
     * Starts the view.
     * 
     * @param data
     */
    public void startView(InterfaceTank data) {
        VBox layout = new VBox();

        layout.setPadding(new Insets(15, 15, 15, 15));
        layout.setSpacing(20);

        Text labelText = new Text();
        labelText.setText(data.getTitle());
        labelText.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 20));
        labelText.setStyle(
                "-fx-font-family: monospace;-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);");
        layout.getChildren().add(labelText);

        TilePane propertiesPane = new TilePane();
        propertiesPane.setPrefColumns(2);
        propertiesPane.setMaxWidth(Region.USE_PREF_SIZE);

        data.getProperties().forEach(property -> addPropertyToPane(property, propertiesPane));

        layout.getChildren().add(propertiesPane);

        Button btnNext;
        btnNext = new Button("→");
        btnNext.setOnAction(controller);
        layout.getChildren().add(btnNext);

        Scene scene = new Scene(layout, 600, 300);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Adds a property to the pane.
     * 
     * @param property
     * @param layout
     */
    private void addPropertyToPane(Property property, Pane layout) {
        Font propertiesFont = Font.font("Tahoma", FontWeight.BOLD, 16);

        Text keyText = new Text();
        keyText.setText(property.getKey() + " = " + property.getValue());
        keyText.setFont(propertiesFont);
        keyText.setWrappingWidth(400);
        keyText.setTextAlignment(TextAlignment.LEFT);
        layout.getChildren().add(keyText);
    }
}
