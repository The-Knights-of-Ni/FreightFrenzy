/*
 * Copyright (c) 2019 Titan Robotics Club (http://www.titanrobotics.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.Subsystems.Drive;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealVector;
import org.firstinspires.ftc.teamcode.Util.MathUtil;

import java.util.Locale;

/** This class implements a 2D pose object that represents the positional state of an object. */
public class Pose2D {
  private static final String moduleName = "Pose2D";

  public double x;
  public double y;
  public double angle;

  /**
   * Constructor: Create an instance of the object.
   *
   * @param x specifies the x component of the position.
   * @param y specifies the y component of the position.
   * @param angle specifies the angle.
   */
  public Pose2D(double x, double y, double angle) {
    this.x = x;
    this.y = y;
    this.angle = angle;
  }

  /**
   * Constructor: Create an instance of the object.
   *
   * @param x specifies the x coordinate of the position.
   * @param y specifies the y coordinate of the position.
   */
  public Pose2D(double x, double y) {
    this(x, y, 0.0);
  }

  /** Constructor: Create an instance of the object. */
  public Pose2D() {
    this(0.0, 0.0, 0.0);
  }

  /**
   * This method returns the string representation of the pose.
   *
   * @return string representation of the pose.
   */
  @Override
  public String toString() {
    return String.format(Locale.US, "(x=%.1f,y=%.1f,angle=%.1f)", x, y, angle);
  }

  /**
   * This method creates and returns a copy of this pose.
   *
   * @return a copy of this pose.
   */
  public Pose2D clone() {
    return new Pose2D(this.x, this.y, this.angle);
  }

  /**
   * This method sets this pose to be the same as the given pose.
   *
   * @param pose specifies the pose to make this pose equal to.
   */
  public void setAs(Pose2D pose) {
    this.x = pose.x;
    this.y = pose.y;
    this.angle = pose.angle;
  }

  /**
   * This method returns a transformed pose relative to the given pose.
   *
   * @param pose specifies the reference pose.
   * @param transformAngle specifies true to also transform angle, false to leave it alone.
   * @return pose relative to the given pose.
   */
  public Pose2D relativeTo(Pose2D pose, boolean transformAngle) {
    double deltaX = x - pose.x;
    double deltaY = y - pose.y;
    RealVector newPos =
        MathUtil.rotateCCW(MatrixUtils.createRealVector(new double[] {deltaX, deltaY}), pose.angle);

    return new Pose2D(
        newPos.getEntry(0), newPos.getEntry(1), transformAngle ? angle - pose.angle : angle);
  }

  /**
   * This method returns a transformed pose relative to the given pose.
   *
   * @param pose specifies the reference pose.
   * @return pose relative to the given pose.
   */
  public Pose2D relativeTo(Pose2D pose) {
    return relativeTo(pose, true);
  }

  /**
   * This method translates this pose with the x and y offset in reference to the angle of the pose.
   *
   * @param xOffset specifies the x offset in reference to the angle of the pose.
   * @param yOffset specifies the y offset in reference to the angle of the pose.
   * @return translated pose.
   */
  public Pose2D translatePose(double xOffset, double yOffset) {
    final String funcName = "translatePose";
    Pose2D newPose = clone();
    double angleRadians = Math.toRadians(newPose.angle);
    double cosAngle = Math.cos(angleRadians);
    double sinAngle = Math.sin(angleRadians);

    newPose.x += xOffset * cosAngle + yOffset * sinAngle;
    newPose.y += -xOffset * sinAngle + yOffset * cosAngle;
    return newPose;
  }
}
