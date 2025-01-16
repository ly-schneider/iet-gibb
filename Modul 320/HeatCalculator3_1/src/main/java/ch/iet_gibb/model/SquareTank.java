package ch.iet_gibb.model;

/**
 * @author Levyn Schneider
 * @version 3.0.0
 */
public class SquareTank extends Tank {
    private double height;
    private double width;
    private double length;

    /**
     * Default constructor
     *
     * @param name           the name of the tank
     * @param height         the height of the tank
     * @param width          the width of the tank
     * @param length         the length of the tank
     * @param maxTemperature the maximum temperature of the tank
     * @param energyPerDay   the energy per day of the tank
     */
    public SquareTank(String name, double height, double width, double length, double maxTemperature, double energyPerDay) {
        setName(name);
        setHeight(height);
        setWidth(width);
        setLength(length);
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
     * Returns the width of the tank.
     *
     * @return the width of the tank
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the tank.
     *
     * @param width the width of the tank
     */
    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Breite muss grösser als 0 sein.");
        }
        this.width = width;
    }

    /**
     * Returns the length of the tank.
     *
     * @return the length of the tank
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets the length of the tank.
     *
     * @param length the length of the tank
     */
    public void setLength(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Länge muss grösser als 0 sein.");
        }
        this.length = length;
    }

    /**
     * Calculates the volume of the tank.
     *
     * @return the volume of the tank
     */
    @Override
    public double calculateVolume() {
        return height * width * length;
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
                "Breite: " + getWidth() + "m\n" +
                "Länge: " + getLength() + "m\n" +
                "Fassungsvermögen: " + calculateVolume() + "m³\n" +
                "Tägliche Energie: " + getEnergyPerDay() + "kWh\n" +
                "Gespeicherte Energie: " + calculateSavedEnergy() + "kWh\n" +
                "Maximale Anzahl Heiztage: " + calculateMaxDaysOfHeating();
    }
}