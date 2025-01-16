package ch.iet_gibb;

import ch.iet_gibb.model.SquareTank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Levyn Schneider
 * @version 1.0.0
 * @since 1.0.0
 */
public class SquareTankTest {
    private SquareTank squareTank;

    @BeforeEach
    public void setUp() {
        squareTank = new SquareTank("Square Tank", 10.0, 30.0, 20.0, 100.0, 2.0);
    }

    @Test
    public void testConstructor() {
        assert squareTank.getName().equals("Square Tank");
        assert squareTank.getWidth() == 10.0;
        assert squareTank.getHeight() == 30.0;
        assert squareTank.getLength() == 20.0;
        assert squareTank.getMaxTemperature() == 100.0;
        assert squareTank.getEnergyPerDay() == 2.0;
    }

    @Test
    public void testCalculateSavedEnergy() {
        assert squareTank.calculateSavedEnergy() == 536.4333333333333;
    }

    @Test
    public void testCalculateVolume() {
        assert squareTank.calculateVolume() == 6000.0;
    }

    @Test
    public void testCalculateMaxDaysOfHeating() {
        assert squareTank.calculateMaxDaysOfHeating() == 268;
    }

    @Test
    public void testToString() {
        assert squareTank.toString().equals("Name: Square Tank\n" + "Fassungsvermögen: 6000.0m³\n" + "Tägliche Energie: 2.0kWh\n" + "Gespeicherte Energie: 536.4333333333333kWh\n" + "Maximale Anzahl Heiztage: 268");
    }

    @Test
    public void testInvalidNameInput() {
        try {
            squareTank.setName("");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Name darf nicht leer sein.");
        }
    }

    @Test
    public void testInvalidWidthInput() {
        try {
            squareTank.setWidth(-1);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Breite muss grösser als 0 sein.");
        }
    }

    @Test
    public void testInvalidHeightInput() {
        try {
            squareTank.setHeight(-1);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Höhe muss grösser als 0 sein.");
        }
    }

    @Test
    public void testInvalidLengthInput() {
        try {
            squareTank.setLength(-1);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Länge muss grösser als 0 sein.");
        }
    }

    @Test
    public void testInvalidMaxTemperatureInput() {
        try {
            squareTank.setMaxTemperature(29);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Maximale Temperatur muss mindestens 30 sein.");
        }
    }

    @Test
    public void testInvalidEnergyPerDayInput() {
        try {
            squareTank.setEnergyPerDay(-1);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Energie pro Tag muss grösser als 0 sein.");
        }
    }
}
