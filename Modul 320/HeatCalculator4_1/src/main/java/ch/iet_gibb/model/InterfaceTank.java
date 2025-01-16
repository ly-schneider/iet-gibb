package ch.iet_gibb.model;

import java.util.List;

import ch.iet_gibb.property.Property;

/**
 * Interface for the Tank class.
 * 
 * @author Levyn Schneider
 * @version 4.1.0
 */
public interface InterfaceTank {
  String getTitle();

  List<Property> getProperties();
}
