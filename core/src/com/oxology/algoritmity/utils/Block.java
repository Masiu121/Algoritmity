package com.oxology.algoritmity.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Block extends GameObject {
    BlockType type;
    Block previous;
    Block next;
    boolean snapped;
    public int[] mousePos;

    public Block(int x, int y, Texture texture, BlockType type) {
        super(x, y, texture);
        this.type = type;
        this.previous = null;
        this.next = null;
        this.snapped = false;
        mousePos = new int[] {0, 0};
    }

    public boolean checkForHover() {
        boolean horizontal = Gdx.input.getX() >= x && Gdx.input.getX() <= x+texture.getWidth();
        boolean vertical = Gdx.graphics.getBackBufferHeight()-Gdx.input.getY() >= y && Gdx.graphics.getBackBufferHeight()-Gdx.input.getY() <= y+texture.getHeight();

        return horizontal && vertical;
    }

    private int[] getMousePos() {
        int mouseX = Gdx.input.getX() - this.x;
        int mouseY = Gdx.graphics.getBackBufferHeight()-Gdx.input.getY() - this.y;

        return new int[] {mouseX, mouseY};
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

    public void update() {
        if(Gdx.input.justTouched() && this.checkForHover()) {
            this.toggleSnap(true);
            this.mousePos = this.getMousePos();
        }
        if(this.isSnapped()) {
            this.move(Gdx.input.getX() - mousePos[0], Gdx.graphics.getBackBufferHeight()-Gdx.input.getY() - mousePos[1]);
        }

        if(!Gdx.input.isTouched()) {
            this.toggleSnap(false);
        }
    }
}
