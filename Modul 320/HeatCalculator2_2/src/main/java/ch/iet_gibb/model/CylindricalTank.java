package ch.iet_gibb.model;

/**
 * @author Levyn Schneider
 * @version 1.0.2
 * @since 1.0.0
 */
public class CylindricalTank {
    private String name; // Name of the tank
    private double radius; // Radius of the tank
    private double height; // Height of the tank
    private double maxTemperature; // Maximum temperature of the tank
    private double energyPerDay; // Energy per day of the tank

    /**
     * Default constructor
     *
     * @param name           the name of the tank
     * @param radius         the radius of the tank
     * @param height         the height of the tank
     * @param maxTemperature the maximum temperature of the tank
     * @param energyPerDay   the energy per day of the tank
     */
    public CylindricalTank(String name, double radius, double height, double maxTemperature, double energyPerDay) {
        setName(name);
        setRadius(radius);
        setHeight(height);
        setMaxTemperature(maxTemperature);
        setEnergyPerDay(energyPerDay);
    }

    /**
     * Returns the stored energy in the tank.
     *
     * @return Energy in kWh
     */
    public double calculateSavedEnergy() {
        return calculateVolume() * 4.18 * (maxTemperature - 23) / 3600.0;
    }

    /**
     * Calculates the volume of the cylindrical tank in m³.
     *
     * @return the volume of the cylindrical tank in m³
     */
    public double calculateVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    /**
     * Calculates the maximum number of heating days.
     *
     * @return the maximum number of heating days
     */
    public int calculateMaxDaysOfHeating() {
        return (int) Math.floor(calculateSavedEnergy() / energyPerDay);
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Fassungsvermögen: " + calculateVolume() + "m³\n" +
                "Tägliche Energie: " + energyPerDay + "kWh\n" +
                "Gespeicherte Energie: " + calculateSavedEnergy() + "kWh\n" +
                "Maximale Anzahl Heiztage: " + calculateMaxDaysOfHeating();
    }

    /**
     * Returns the name of the tank.
     *
     * @return the name of the tank
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the radius of the tank.
     *
     * @return the radius of the tank
     */
    public double getRadius() {
        return radius;
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
     * Returns the maximum temperature the tank can handle.
     *
     * @return the maximum temperature of the tank
     */
    public double getMaxTemperature() {
        return maxTemperature;
    }

    /**
     * Returns the energy consumption of the tank per day.
     *
     * @return the energy consumption per day
     */
    public double getEnergyPerDay() {
        return energyPerDay;
    }

    /**
     * Sets the name of the tank.
     *
     * @param name the name of the tank
     */
    public void setName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name darf nicht leer sein.");
        }
        this.name = name;
    }

    /**
     * Sets the radius of the tank.
     *
     * @param radius the radius of the tank
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius muss grösser als 0 sein.");
        }
        this.radius = radius;
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
     * Sets the maximum temperature of the tank.
     *
     * @param maxTemperature the maximum temperature of the tank
     */
    public void setMaxTemperature(double maxTemperature) {
        if (maxTemperature < 30) {
            throw new IllegalArgumentException("Maximale Temperatur muss mindestens 30 sein.");
        }
        this.maxTemperature = maxTemperature;
    }

    /**
     * Sets the energy per day of the tank.
     *
     * @param energyPerDay the energy per day of the tank
     */
    public void setEnergyPerDay(double energyPerDay) {
        if (energyPerDay <= 0) {
            throw new IllegalArgumentException("Energie pro Tag muss grösser als 0 sein.");
        }
        this.energyPerDay = energyPerDay;
    }
}
