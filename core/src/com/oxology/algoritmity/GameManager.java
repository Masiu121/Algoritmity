package com.oxology.algoritmity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.algoritmity.blocks.Block;
import com.oxology.algoritmity.utils.GameObject;

import java.util.ArrayList;
import java.util.List;

import static com.oxology.algoritmity.blocks.Block.BLOCK_HEIGHT;

public class GameManager {
    List<GameObject> gameObjects;
    List<Block> blocks;

    public GameManager() {
        this.gameObjects = new ArrayList<>();
        this.blocks = new ArrayList<>();
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        for(GameObject gameObject : gameObjects) {
            if(gameObject.isVisible()) {
                if(gameObject instanceof Block) {
                    Block current = (Block) gameObject;
                    Block toConnect = current.getToConnect();
                    if(current.isSnapped()) {
                        if (toConnect != null) {
                            if (current.getShadow() != null) {
                                batch.draw(current.getShadow(), toConnect.getX(), toConnect.getY() - BLOCK_HEIGHT);

                                int blockNumber = 2;
                                Block nextBlock = current.getNextBlock();
                                while(nextBlock != null) {
                                    if(nextBlock.getShadow() != null)
                                        batch.draw(current.getShadow(), toConnect.getX(), toConnect.getY() - BLOCK_HEIGHT*blockNumber);

                                    blockNumber++;
                                    nextBlock = nextBlock.getNextBlock();
                                }
                            }
                        }
                    }
                }
                batch.draw(gameObject.getTexture(), gameObject.getX(), gameObject.getY());
            }
        }
        batch.end();
    }

    public void addBlock(Block block) {
        gameObjects.add(block);
        updateBlocks();
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

    public void updateBlocks() {
        blocks.clear();
        for(GameObject gameObject : gameObjects) {
            if(gameObject instanceof Block) {
                blocks.add((Block) gameObject);
            }
        }
    }

    public void update() {
        for(GameObject gameObject : gameObjects) {
            gameObject.update();

            if(gameObject instanceof Block) {
                ((Block) gameObject).checkForConnection(blocks);
            }
        }
    }
}
