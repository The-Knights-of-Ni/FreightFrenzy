package org.firstinspires.ftc.teamcode.Util.CoordinateSystem;

import org.firstinspires.ftc.teamcode.Util.CoordinateSystem.PathFinder.PathFinder;

import java.util.ArrayList;

public class Path {
    ArrayList<Coordinate> path;
    ArrayList<Move> moves;

    public Path() {
        path = new ArrayList<>();
    }

    public Path(ArrayList<Coordinate> path) {
        this.path = path;
        updateMoves();
    }

    public ArrayList<Coordinate> getPath() {
        return this.path;
    }

    public void add(Coordinate c) {
        path.add(c);
    }

    public void add(int index, Coordinate c) {
        path.add(index, c);
    }

    public void add(Path p) {
        path.addAll(p.getPath());
    }

    public Coordinate get(int index) {
        return path.get(index);
    }

    public int length() {
        return path.size();
    }

    public Move next() {
        moves.remove(0);
        return moves.get(0);
    }

    public void goTo(Coordinate place, PathFinder pF) {
        add(pF.getShortestPath(path.get(path.size() - 1), place));
    }

    public void goTo(FieldObject place, PathFinder pF) {
        goTo(place.getClosestCoordinate(path.get(path.size() - 1), pF), pF);
    }

    /**
     * Optimizes the moves such that two moves that do the same thing are combined into one.
     */
    private void optimizeMoves() {
        Move previousMove = new Move(0, -1, 0);
        int previousMovePlace;
        for (Move move : moves) {
            if (move.getAngle() == previousMove.getAngle()) { // Checks if two consecutive moves have the same angle
                moves.remove(move); // removes the current move
                previousMovePlace = moves.indexOf(previousMove); // gets the index of the previous move
                moves.get(previousMovePlace).setDistance(moves.get(previousMovePlace).getDistance() +
                        move.getDistance()); // Sets the previous moves distance to the sum of its distance and the
                                             // current moves distance
            }
            previousMove = move;
        }
    }

    private void updateMoves() {
        Coordinate previousMove = null;
        int x;
        int y;
        for (Coordinate currentCoordinate : path) {
            if (!(previousMove == null)) {
                if (currentCoordinate.getX() == previousMove.getX()) {
                    x = 0;
                } else if (currentCoordinate.getX() == previousMove.getX() + 1) {
                    x = 1;
                } else {
                    x = -1;
                }

                if (currentCoordinate.getY() == previousMove.getY()) {
                    y = 0;
                } else if (currentCoordinate.getY() == previousMove.getY() + 1) {
                    y = 1;
                } else {
                    y = -1;
                }

                if (!(x == 0 && y == 0)) {
                    if (x == 0 && y == 1) {
                        moves.add(new Move(x, y, 90, 1));
                    } else if (x == 0 && y == -1) {
                        moves.add(new Move(x, y, -90, 1));
                    } else if (x == 1 && y == 0) {
                        moves.add(new Move(x, y, 0, 1));
                    } else if (x == -1 && y == 0) {
                        moves.add(new Move(x, y, 180, 1));
                    } else if (x == 1 && y == 1) {
                        moves.add(new Move(x, y, 45, 1));
                    } else if (x == -1 && y == 1) {
                        moves.add(new Move(x, y, 135, 1));
                    } else if (x == 1 && y == -1) {
                        moves.add(new Move(x, y, -45, 1));
                    } else {
                        moves.add(new Move(x, y, -135, 1));
                    }
                }
            }
            previousMove = currentCoordinate;
        }
        optimizeMoves();
    }
}
