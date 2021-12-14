package org.firstinspires.ftc.teamcode.Util;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import static org.firstinspires.ftc.teamcode.Util.MathUtil.maxMagnitude;

/**
 * This class contains platform independent utility methods. All methods in this class are static.
 * It is not necessary to instantiate this class to call its methods.
 */
public class TrcUtil {
  public static final double INCHES_PER_CM = 0.393701;
  public static final double MM_PER_INCH = 25.4;
  public static final double EARTH_GRAVITATIONAL_CONSTANT = 9.807; // in m/s2

  /**
   * This method sets a bitmask with the given bit positions.
   *
   * @param bitPositions specifies the bit positions to be set to 1. Bit positions are 0-based.
   * @return bit mask.
   */
  public static int setBitMask(int... bitPositions) {
    int bitMask = 0;

    for (int pos : bitPositions) {
      bitMask |= 1 << pos;
    }

    return bitMask;
  }

  /**
   * This method normalizes the given array of numbers such that no number exceeds +/- 1.0. If no
   * number exceeds the magnitude of 1.0, nothing will change, otherwise the original nums array
   * will be modified in place.
   *
   * @param nums specifies the number array.
   */
  public static void normalizeInPlace(double[] nums) {
    double maxMag = maxMagnitude(nums);

    if (maxMag > 1.0) {
      for (int i = 0; i < nums.length; i++) {
        nums[i] /= maxMag;
      }
    }
  }

  /**
   * This method checks if the given value is within the specified range.
   *
   * @param value The value to be checked.
   * @param low The low limit of the range.
   * @param high The high limit of the range.
   * @param inclusive specifies true if the range is inclusive [low, high], otherwise the range is
   *     exclusive (low, high).
   * @return true if the value is within range, false otherwise.
   */
  public static boolean inRange(int value, int low, int high, boolean inclusive) {
    return inclusive ? value >= low && value <= high : value > low && value < high;
  }

  /**
   * This method checks if the given value is within the specified range inclusive.
   *
   * @param value The value to be checked.
   * @param low The low limit of the range.
   * @param high The high limit of the range.
   * @return true if the value is within range, false otherwise.
   */
  public static boolean inRange(int value, int low, int high) {
    return inRange(value, low, high, true);
  }

  /**
   * This method checks if the given value is within the specified range.
   *
   * @param value The value to be checked.
   * @param low The low limit of the range.
   * @param high The high limit of the range.
   * @param inclusive specifies true if the range is inclusive [low, high], otherwise the range is
   *     exclusive (low,high).
   * @return true if the value is within range, false otherwise.
   */
  public static boolean inRange(double value, double low, double high, boolean inclusive) {
    return inclusive ? value >= low && value <= high : value > low && value < high;
  }

  /**
   * This method checks if the given value is within the specified range inclusive.
   *
   * @param value The value to be checked.
   * @param low The low limit of the range.
   * @param high The high limit of the range.
   * @return true if the value is within range, false otherwise.
   */
  public static boolean inRange(double value, double low, double high) {
    return inRange(value, low, high, true);
  }

  /**
   * This method clips the given value to the range limited by the given low and high limits.
   *
   * @param value specifies the value to be clipped
   * @param lowLimit specifies the low limit of the range.
   * @param highLimit specifies the high limit of the range.
   * @return the result of the clipped value.
   */
  public static int clipRange(int value, int lowLimit, int highLimit) {
    return Math.min(Math.max(value, lowLimit), highLimit);
  }

  /**
   * This method clips the given value to the range limited by the given low and high limits.
   *
   * @param value specifies the value to be clipped
   * @param lowLimit specifies the low limit of the range.
   * @param highLimit specifies the high limit of the range.
   * @return the result of the clipped value.
   */
  public static double clipRange(double value, double lowLimit, double highLimit) {
    return Math.min(Math.max(value, lowLimit), highLimit);
  }

  /**
   * This method clips the given value to the range between -1.0 and 1.0.
   *
   * @param value specifies the value to be clipped
   * @return the result of the clipped value.
   */
  public static double clipRange(double value) {
    return clipRange(value, -1.0, 1.0);
  }

  /**
   * This method scales the given value from the source range to the target range.
   *
   * @param value specifies the value to be scaled.
   * @param lowSrcRange specifies the low limit of the source range.
   * @param highSrcRange specifies the high limit of the source range.
   * @param lowDstRange specifies the low limit of the target range.
   * @param highDstRange specifies the high limit of the target range
   * @return the result of the scaled value.
   */
  public static int scaleRange(
      int value, int lowSrcRange, int highSrcRange, int lowDstRange, int highDstRange) {
    return lowDstRange + (value - lowSrcRange) * (highDstRange - lowDstRange) / (highSrcRange - lowSrcRange);
  }

  /**
   * This method scales the given value from the source range to the target range.
   *
   * @param value specifies the value to be scaled.
   * @param lowSrcRange specifies the low limit of the source range.
   * @param highSrcRange specifies the high limit of the source range.
   * @param lowDstRange specifies the low limit of the target range.
   * @param highDstRange specifies the high limit of the target range
   * @return the result of the scaled value.
   */
  public static double scaleRange(double value, double lowSrcRange, double highSrcRange, double lowDstRange,
                                  double highDstRange) {
    return lowDstRange + (value - lowSrcRange) * (highDstRange - lowDstRange) / (highSrcRange - lowSrcRange);
  }

  /**
   * This method checks if the given value is within the deadband range. If so, it returns 0.0 else
   * it returns the unchanged value.
   *
   * @param value specifies the value to be checked.
   * @param deadband specifies the deadband zone.
   * @return the value 0.0 if within deadband, unaltered otherwise.
   */
  public static double applyDeadband(double value, double deadband) {
    return Math.abs(value) >= deadband ? value : 0.0;
  }

  /**
   * This method returns the indexed byte of an integer.
   *
   * @param data specifies the integer value.
   * @param index specifies the byte index.
   * @return indexed byte of the integer.
   */
  public static byte intToByte(int data, int index) {
    return (byte) (data >> (8 * index));
  }

  /**
   * This method combines two bytes into an integer.
   *
   * @param data1 specifies the lowest byte.
   * @param data2 specifies the next lowest byte.
   * @param data3 specifies the next byte.
   * @param data4 specifies the highest byte.
   * @return the converted integer.
   */
  public static int bytesToInt(byte data1, byte data2, byte data3, byte data4) {
    return (data4 << 24) & 0xff000000
        | (data3 << 16) & 0x00ff0000
        | (data2 << 8) & 0x0000ff00
        | data1 & 0x000000ff;
  } // bytesToInt

  /**
   * This method combines two bytes into an integer.
   *
   * @param low specifies the low byte.
   * @param high specifies the high byte.
   * @return the converted integer.
   */
  public static int bytesToInt(byte low, byte high) {
    return bytesToInt(low, high, (byte) 0, (byte) 0);
  } // bytesToInt

  /**
   * This method converts a byte into an integer.
   *
   * @param data specifies the byte data.
   * @return the converted integer.
   */
  public static int bytesToInt(byte data) {
    return (int) data;
  } // bytesToInt

  /**
   * This method combines two bytes into a short.
   *
   * @param low specifies the low byte.
   * @param high specifies the high byte.
   * @return the converted short.
   */
  public static short bytesToShort(byte low, byte high) {
    return (short) bytesToInt(low, high);
  } // bytesToShort

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
