package ch.iet_gibb;

import ch.iet_gibb.model.Tank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Levyn Schneider
 * @version 1.0.0
 * @since 1.0.0
 */
public class TankTest {
    private Tank tank;

    @BeforeEach
    public void setUp() {
        tank = new Tank("Tank", 1000.0, 100.0, 2.0);
    }

    @Test
    public void testConstructor() {
        assert tank.getName().equals("Tank");
        assert tank.getVolume() == 1000.0;
        assert tank.getMaxTemperature() == 100.0;
        assert tank.getEnergyPerDay() == 2.0;
    }

    @Test
    public void testCalculateSavedEnergy() {
        assert tank.calculateSavedEnergy() == 89.40555555555555;
    }

    @Test
    public void testCalculateMaxDaysOfHeating() {
        assert tank.calculateMaxDaysOfHeating() == 44;
    }

    @Test
    public void testToString() {
        assert tank.toString().equals("Name: Tank\n" + "Fassungsvermögen: 1000.0m³\n" + "Tägliche Energie: 2.0kWh\n" + "Gespeicherte Energie: 89.40555555555555kWh\n" + "Maximale Anzahl Heiztage: 44");
    }

    @Test
    public void testInvalidNameInput() {
        try {
            tank.setName("");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Name darf nicht leer sein.");
        }
    }

    @Test
    public void testInvalidVolumeInput() {
        try {
            tank.setVolume(-1);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Volumen muss grösser als 0 sein.");
        }
    }

    @Test
    public void testInvalidMaxTemperatureInput() {
        try {
            tank.setMaxTemperature(29);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Maximale Temperatur muss mindestens 30 sein.");
        }
    }

    @Test
    public void testInvalidEnergyPerDayInput() {
        try {
            tank.setEnergyPerDay(-1);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Energie pro Tag muss grösser als 0 sein.");
        }
    }
}
