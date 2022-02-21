package org.firstinspires.ftc.teamcode.PurePursuit;

import java.util.ArrayList;

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
