package ch.iet_gibb;

import ch.iet_gibb.model.CylindricalTank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Levyn Schneider
 * @version 1.0.0
 * @since 1.0.0
 */
public class CylindricalTankTest {
    private CylindricalTank cylindricalTank;

    @BeforeEach
    public void setUp() {
        cylindricalTank = new CylindricalTank("Cylindrical Tank", 10.0, 30.0, 100.0, 2.0);
    }

    @Test
    public void testConstructor() {
        assert cylindricalTank.getName().equals("Cylindrical Tank");
        assert cylindricalTank.getRadius() == 10.0;
        assert cylindricalTank.getHeight() == 30.0;
        assert cylindricalTank.getMaxTemperature() == 100.0;
        assert cylindricalTank.getEnergyPerDay() == 2.0;
    }

    @Test
    public void testCalculateSavedEnergy() {
        assert cylindricalTank.calculateSavedEnergy() == 842.6275095703423;
    }

    @Test
    public void testCalculateVolume() {
        assert cylindricalTank.calculateVolume() == 9424.77796076938;
    }

    @Test
    public void testCalculateMaxDaysOfHeating() {
        assert cylindricalTank.calculateMaxDaysOfHeating() == 421;
    }

    @Test
    public void testToString() {
        assert cylindricalTank.toString().equals("Name: Cylindrical Tank\n" + "Fassungsvermögen: 9424.77796076938m³\n" + "Tägliche Energie: 2.0kWh\n" + "Gespeicherte Energie: 842.6275095703423kWh\n" + "Maximale Anzahl Heiztage: 421");
    }

    @Test
    public void testInvalidNameInput() {
        try {
            cylindricalTank.setName("");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Name darf nicht leer sein.");
        }
    }

    @Test
    public void testInvalidRadiusInput() {
        try {
            cylindricalTank.setRadius(-1);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Radius muss grösser als 0 sein.");
        }
    }

    @Test
    public void testInvalidHeightInput() {
        try {
            cylindricalTank.setHeight(-1);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Höhe muss grösser als 0 sein.");
        }
    }

    @Test
    public void testInvalidMaxTemperatureInput() {
        try {
            cylindricalTank.setMaxTemperature(29);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Maximale Temperatur muss mindestens 30 sein.");
        }
    }

    @Test
    public void testInvalidEnergyPerDayInput() {
        try {
            cylindricalTank.setEnergyPerDay(-1);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Energie pro Tag muss grösser als 0 sein.");
        }
    }
}
