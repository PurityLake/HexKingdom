package com.kingdom.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    private static final float[] coords = new float[] {
        50.0f, 50.0f,
        50.0f, 0.0f,
        100.0f, 25.0f,
        100.0f, 75.0f,
        50.0f, 100.0f,
        0.00f, 75.0f,
        0.00f, 25.0f
    };
    
    private static final short[] tris = new short[] {
        0, 1, 2,
        0, 2, 3,
        0, 3, 2,
        0, 4, 3,
        0, 5, 4,
        0, 6, 5,
        0, 1, 6
    };
    public static final int width = 100;
    public static final int height = 100;

    private int x, y;
    private float posX, posY;
    private PolygonSprite sprite;

    public Tile(String filename, int x, int y) {
        this.x = x;
        this.y = y;
        if (y % 2 == 0) {
            this.posX = x * width;
            this.posY = y * 75.0f;
        } else { 
            this.posX = x * width - 50.0f;
            this.posY = y * 75;
        }
        sprite = new PolygonSprite(new PolygonRegion(new TextureRegion(new Texture(Gdx.files.internal(filename))),
            coords, tris));
        sprite.setOrigin(0.5f, 0.5f);
        sprite.setPosition(this.posX, this.posY);
    }

    public void draw(PolygonSpriteBatch batch) {
        sprite.draw(batch);
    }

    public void draw(PolygonSpriteBatch batch, float dx, float dy) {
        float oldX = posX, oldY = posY;
        sprite.setPosition(this.posX + dx, this.posY + dy);
        sprite.draw(batch);
        sprite.setPosition(oldX, oldY);
    }
}