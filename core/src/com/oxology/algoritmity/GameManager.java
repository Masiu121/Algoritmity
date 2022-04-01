package com.oxology.algoritmity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.algoritmity.utils.Block;
import com.oxology.algoritmity.utils.GameObject;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    List<GameObject> gameObjects;

    public GameManager() {
        gameObjects = new ArrayList<>();
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        for(GameObject gameObject : gameObjects) {
            if(gameObject.isVisible())
                batch.draw(gameObject.getTexture(), gameObject.getX(), gameObject.getY());
        }
        batch.end();
    }

    public void addBlock(Block block) {
        gameObjects.add(block);
    }

    public void addVisibleObject(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void removeVisibleObject(GameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }

    public void clearObjectsToDraw() {
        this.gameObjects.clear();
    }

    public void update() {
        for(GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }
}
