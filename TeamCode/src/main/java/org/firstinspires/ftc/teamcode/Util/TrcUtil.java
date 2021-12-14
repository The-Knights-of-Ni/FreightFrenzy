package org.firstinspires.ftc.teamcode.Util;

/**
 * This class contains platform independent utility methods. All methods in this class are static.
 * It is not necessary to instantiate this class to call its methods.
 */
public class TrcUtil {
  public static final double INCHES_PER_CM = 0.393701;
  public static final double MM_PER_INCH = 25.4;
  public static final double EARTH_GRAVITATIONAL_CONSTANT = 9.807; // in m/s2

  /**
   * This interface provides the method to get data of the specified type. This is to replaced the
   * Supplier interface that Java SDK provides but Android API level 19 does not have.
   */
  public interface DataSupplier<T> {
    /**
     * This method returns the data of the designated type.
     *
     * @return data of the designated type.
     */
    T get();
  }
}
