package ch.iet_gibb.model;

import java.util.ArrayList;
import java.util.List;

import ch.iet_gibb.property.Property;

/**
 * @author Levyn Schneider
 * @version 4.1.0
 */
public abstract class Tank implements InterfaceTank {
    private String name; // Name of the tank
    private double maxTemperature; // Maximum temperature of the tank
    private double energyPerDay; // Energy per day of the tank

    public abstract double calculateVolume();

    @Override
    public List<Property> getProperties() {
        List<Property> properties = new ArrayList<>();
        properties.add(new Property("Maximale Temperatur", String.valueOf(getMaxTemperature())));
        properties.add(new Property("Name", getName()));
        properties.add(new Property("Fassungsvermögen", String.valueOf(calculateVolume())));
        properties.add(new Property("Tägliche Energie", String.valueOf(getEnergyPerDay())));
        properties.add(new Property("Tage heizen", String.valueOf(calculateMaxDaysOfHeating())));
        properties.add(new Property("Gespeicherte Energie", String.valueOf(calculateSavedEnergy())));
        return properties;
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
