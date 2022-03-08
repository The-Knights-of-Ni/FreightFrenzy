package org.firstinspires.ftc.teamcode.PurePursuit;

import org.firstinspires.ftc.teamcode.PurePursuit.Waypoints.GeneralWaypoint;
import org.firstinspires.ftc.teamcode.Util.Geometry.Pose2d;
import org.firstinspires.ftc.teamcode.PurePursuit.Types.WaypointType;

/**
 * A pure pursuit Waypoint is a point in which the robot traverses. Using Waypoints
 * one can construct a pure pursuit path for their robot to follow.
 *
 * @author Michael Baljet, Team 14470
 * @version 1.2
 * @see GeneralWaypoint , EndWaypoint, StartWaypoint, PointTurnWaypoint, InterruptWaypoint
 */
public interface Waypoint {

    /**
     * Returns this WayPoint's type.
     *
     * @return this WayPoint's type.
     */
    public WaypointType getType();

    /**
     * Returns this Waypoint's position.
     *
     * @return this Waypoint's position.
     */
    public Pose2d getPose();

    /**
     * Returns the follow distance for this waypoint.
     *
     * @return the follow distance for this waypoint.
     */
    public double getFollowDistance();

    /**
     * Returns the timeout period of this waypoint.
     *
     * @return the timeout period of this waypoint.
     */
    public long getTimeout();

}
