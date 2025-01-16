package ch.iet_gibb.property;

/**
 * @author Levyn Schneider
 * @version 4.1.0
 */
public class Property {
  private String key;
  private String value;

  /**
   * Constructor for the Property class.
   * 
   * @param key
   * @param value
   */
  public Property(String key, String value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Getter for the key.
   * 
   * @return the key
   */
  public String getKey() {
    return key;
  }

  /**
   * Getter for the value.
   * 
   * @return the value
   */
  public String getValue() {
    return value;
  }
}
