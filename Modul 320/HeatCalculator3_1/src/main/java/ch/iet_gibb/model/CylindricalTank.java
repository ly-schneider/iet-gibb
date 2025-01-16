package ch.iet_gibb.model;

/**
 * @author Levyn Schneider
 * @version 3.0.0
 */
public class CylindricalTank extends Tank {
    private double height;
    private double diameter;

    /**
     * Default constructor
     *
     * @param name           the name of the tank
     * @param height         the height of the tank
     * @param diameter       the diameter of the tank
     * @param maxTemperature the maximum temperature of the tank
     * @param energyPerDay   the energy per day of the tank
     */
    public CylindricalTank(String name, double height, double diameter, double maxTemperature, double energyPerDay) {
        setName(name);
        setHeight(height);
        setDiameter(diameter);
        setMaxTemperature(maxTemperature);
        setEnergyPerDay(energyPerDay);
    }

    /**
     * Returns the height of the tank.
     *
     * @return the height of the tank
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the tank.
     *
     * @param height the height of the tank
     */
    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Höhe muss grösser als 0 sein.");
        }
        this.height = height;
    }

    /**
     * Returns the diameter of the tank.
     *
     * @return the diameter of the tank
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * Sets the diameter of the tank.
     *
     * @param diameter the diameter of the tank
     */
    public void setDiameter(double diameter) {
        if (diameter <= 0) {
            throw new IllegalArgumentException("Durchmesser muss grösser als 0 sein.");
        }
        this.diameter = diameter;
    }

    @Override
    public double calculateVolume() {
        return Math.PI * Math.pow(diameter / 2, 2) * height;
    }

    /**
     * Returns the tank as a string.
     *
     * @return the tank as a string
     */
    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "Höhe: " + getHeight() + "m\n" +
                "Durchmesser: " + getDiameter() + "m\n" +
                "Fassungsvermögen: " + calculateVolume() + "m³\n" +
                "Tägliche Energie: " + getEnergyPerDay() + "kWh\n" +
                "Gespeicherte Energie: " + calculateSavedEnergy() + "kWh\n" +
                "Maximale Anzahl Heiztage: " + calculateMaxDaysOfHeating();
    }
}
