package org.firstinspires.ftc.teamcode.Subsystems.Drive;

public class Odometry {
    TrcPose2D position;
    TrcPose2D velocity;

    /**
     * Constructor: Create an instance of the object.
     */
    Odometry() {
        position = new TrcPose2D();
        velocity = new TrcPose2D();
    }

    /**
     * Constructor: Create an instance of the object.
     *
     * @param position specifies the initial position.
     * @param velocity specifies the initial velocity.
     */
    Odometry(TrcPose2D position, TrcPose2D velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    /**
     * This method returns the string representation of the object.
     *
     * @return string representation of the object.
     */
    @Override
    public String toString() {
        return "position=" + position.toString() + ", velocity=" + velocity.toString();
    }

    /**
     * This method creates and returns a copy of this odometry.
     *
     * @return a copy of this odometry.
     */
    public Odometry clone() {
        return new Odometry(position.clone(), velocity.clone());
    }

    /**
     * This method sets the position info of the odometry to the given pose.
     *
     * @param pose specifies the pose to set the position info to.
     */
    void setPositionAs(TrcPose2D pose) {
        this.position.setAs(pose);
    }

    /**
     * This method sets the velocity info of the odometry to the given pose.
     *
     * @param pose specifies the pose to set the velocity info to.
     */
    void setVelocityAs(TrcPose2D pose) {
        this.velocity.setAs(pose);
    }
}
