package com.oxology.algoritmity.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oxology.algoritmity.GameManager;
import com.oxology.algoritmity.utils.GameObject;

import java.util.List;

public class Block extends GameObject {
    public static int BLOCK_HEIGHT = 28;

    BlockType type;
    Block previous;
    Block next;
    boolean snapped;
    public int[] mousePos;
    Texture shadow;
    Block toConnect;
    GameManager gameManager;

    public Block(int x, int y, Texture texture, BlockType type, Texture shadow, GameManager gameManager) {
        super(x, y, texture);
        this.type = type;
        this.previous = null;
        this.next = null;
        this.snapped = false;
        this.mousePos = new int[] {0, 0};
        this.shadow = shadow;
        this.toConnect = null;
        this.gameManager = gameManager;
    }

    public boolean checkForHover(int xOffset, int yOffset) {
        boolean horizontal = Gdx.input.getX()+xOffset >= super.getX() && Gdx.input.getX()+xOffset <= super.getX()+super.getTexture().getWidth();
        boolean vertical = Gdx.graphics.getBackBufferHeight()-Gdx.input.getY()+yOffset >= super.getY() && Gdx.graphics.getBackBufferHeight()-Gdx.input.getY()+yOffset <= super.getY()+super.getTexture().getHeight();

        return horizontal && vertical;
    }

    private int[] getMousePos() {
        int mouseX = Gdx.input.getX() - super.getX();
        int mouseY = Gdx.graphics.getBackBufferHeight()-Gdx.input.getY() - super.getY();

        return new int[] {mouseX, mouseY};
    }

    public void checkForConnection(List<Block> blocks) {
        for(Block block : blocks) {
            if(toConnect == null) {
                if (this.isSnapped() && block.checkForHover(0, BLOCK_HEIGHT) && block != this && block.getNextBlock() == null) {
                    toConnect = block;
                }
            } else if(!toConnect.checkForHover(0, BLOCK_HEIGHT) && block != this) {
                toConnect = null;
            }
        }

        if(!isSnapped() && toConnect != null) {
            setPreviousBlock(toConnect);
            toConnect.setNextBlock(this);
            toConnect = null;

            int moveX = getPreviousBlock().getX();
            int moveY = getPreviousBlock().getY()-BLOCK_HEIGHT;
            moveWithNextBlocks(moveX, moveY);
        }
    }

    public void toggleSnap(boolean snapped) {
        this.snapped = snapped;
    }

    public boolean isSnapped() {
        return snapped;
    }

    public void setNextBlock(Block next) {
        this.next = next;
    }

    public void unsetNextBlock() {
        this.next = null;
    }

    public Block getNextBlock() {
        return next;
    }

    public void setPreviousBlock(Block previous) {
        this.previous = previous;
    }

    public void unsetPreviousBlock() {
        this.previous = null;
    }

    public Block getPreviousBlock() {
        return previous;
    }

    public Block getToConnect() {
        return toConnect;
    }

    public Texture getShadow() {
        return shadow;
    }

    private void moveWithNextBlocks(int moveX, int moveY) {
        this.move(moveX, moveY);

        Block nextBlock = getNextBlock();
        int blockNumber = 1;
        while(nextBlock != null) {
            nextBlock.move(moveX, moveY-BLOCK_HEIGHT*blockNumber);
            blockNumber++;
            nextBlock = nextBlock.getNextBlock();
        }
    }

    public void update() {
        if(Gdx.input.justTouched() && this.checkForHover(0, 0)) {
            this.toggleSnap(true);
            this.mousePos = this.getMousePos();
        }
        if(this.isSnapped()) {
            int moveX = Gdx.input.getX() - mousePos[0];
            int moveY = Gdx.graphics.getBackBufferHeight()-Gdx.input.getY() - mousePos[1];
            moveWithNextBlocks(moveX, moveY);

            if(getPreviousBlock() != null) {
                getPreviousBlock().setNextBlock(null);
                setPreviousBlock(null);
            }
        }

        if(!Gdx.input.isTouched()) {
            this.toggleSnap(false);
        }
    }
}
