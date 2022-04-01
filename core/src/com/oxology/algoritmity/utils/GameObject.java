package com.oxology.algoritmity.utils;

import com.badlogic.gdx.graphics.Texture;

public class GameObject {
    int x, y;
    Texture texture;
    boolean visible;

    public GameObject(int x, int y, Texture texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.visible = true;
    }

    public boolean isVisible() {
        return visible;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void update() {}
}
