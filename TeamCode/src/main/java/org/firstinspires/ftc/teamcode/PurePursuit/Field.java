package org.firstinspires.ftc.teamcode.PurePursuit;

import org.firstinspires.ftc.teamcode.Util.Coordinate;

import java.util.ArrayList;

/**
 * Represents a Field with game objects.
 */
public class Field {
    private ArrayList<GameObject> gameObjects;

    public Field() {
        gameObjects = new ArrayList<>();
    }

    public Field(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void addObject(GameObject object) {
        gameObjects.add(object);
    }

    /** Checks if the coordinate is occupied by an object
     * @param coordinate the coordinate to use
     * @return a boolean, true if the coordinate is occupied, false if not.
     */
    public boolean isOccupied(Coordinate coordinate) {
        for (GameObject object: gameObjects) {
            if (object.occupies(coordinate)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
}
