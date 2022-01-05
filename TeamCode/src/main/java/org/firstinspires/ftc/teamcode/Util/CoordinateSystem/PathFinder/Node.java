package org.firstinspires.ftc.teamcode.Util.CoordinateSystem.PathFinder;

import org.firstinspires.ftc.teamcode.Util.CoordinateSystem.Coordinate;

// Node class for convenience
public class Node extends Coordinate implements Comparable<Node> {
    public Node parent;

    public Node(Node parent, int xPos, int yPos, double g, double h) {
        super(xPos, yPos, g, h);
        this.parent = parent;
    }

    // Compare by f value (g + h)
    @Override
    public int compareTo(Node node) {
        return (int) ((this.g + this.h) - (node.g + node.h));
    }
}
