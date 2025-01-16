package ch.iet_gibb.model;

/**
 * @author Levyn Schneider
 * @version 3.0.0
 */
public abstract class Tank {
    private String name; // Name of the tank
    private double maxTemperature; // Maximum temperature of the tank
    private double energyPerDay; // Energy per day of the tank

    public abstract double calculateVolume();

    /**
     * Returns the stored energy in the tank.
     *
     * @return Energy in kWh
     */
    public double calculateSavedEnergy() {
        return calculateVolume() * 4.18 * (maxTemperature - 23) / 3600.0;
    }

    /**
     * Calculates the maximum number of heating days.
     *
     * @return the maximum number of heating days
     */
    public int calculateMaxDaysOfHeating() {
        return (int) Math.floor(calculateSavedEnergy() / energyPerDay);
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
