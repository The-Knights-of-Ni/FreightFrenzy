package org.firstinspires.ftc.teamcode.Util;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/**
 * This class contains platform independent utility methods. All methods in this class are static.
 * It is not necessary to instantiate this class to call its methods.
 */
public class TrcUtil {
  public static final double INCHES_PER_CM = 0.393701;
  public static final double MM_PER_INCH = 25.4;
  public static final double EARTH_GRAVITATIONAL_CONSTANT = 9.807; // in m/s2

  /**
   * Convert a point from a polar coordinate system to a cartesian coordinate system.
   *
   * @param r Magnitude of vector
   * @param theta Direction of vector, in degrees clockwise from 0 (+y)
   * @return Vector in a cartesian coordinate system representing the same point.
   */
  public static RealVector polarToCartesian(double r, double theta) {
    double thetaRad = Math.toRadians(theta);
    return MatrixUtils.createRealVector(
        new double[] {r * Math.sin(thetaRad), r * Math.cos(thetaRad)});
  } // polarToCartesian

  /**
   * Rotate a point counter-clockwise about the origin.
   *
   * @param vector The vector to rotate.
   * @param angle The angle in degrees to rotate by.
   * @return The vector after the rotation transformation.
   */
  public static RealVector rotateCCW(RealVector vector, double angle) {
    return createCCWRotationMatrix(angle).operate(vector);
  } // rotateCCW

  /**
   * Rotate a point clockwise about the origin.
   *
   * @param vector The vector to rotate.
   * @param angle The angle in degrees to rotate by.
   * @return The vector after the rotation transformation.
   */
  public static RealVector rotateCW(RealVector vector, double angle) {
    return createCWRotationMatrix(angle).operate(vector);
  }

  /**
   * Create a rotation matrix that will rotate a point counter-clockwise about the origin by a
   * specific number of degrees.
   *
   * @param angle The angle in degrees to rotate by.
   * @return A rotation matrix describing a counter-clockwise rotation by <code>angle</code>
   *     degrees.
   */
  public static RealMatrix createCCWRotationMatrix(double angle) {
    double angleRad = Math.toRadians(angle);
    return MatrixUtils.createRealMatrix(
        new double[][] {
          {Math.cos(angleRad), -Math.sin(angleRad)}, {Math.sin(angleRad), Math.cos(angleRad)}
        });
  }

  /**
   * Create a rotation matrix that will rotate a point clockwise about the origin by a specific
   * number of degrees.
   *
   * @param angle The angle in degrees to rotate by.
   * @return A rotation matrix describing a clockwise rotation by <code>angle</code> degrees.
   */
  public static RealMatrix createCWRotationMatrix(double angle) {
    return createCCWRotationMatrix(angle).transpose();
  }

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
