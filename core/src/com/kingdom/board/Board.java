package com.kingdom.board;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;

public class Board {
    private float x, y;
    private ArrayList<ArrayList<Tile>> tiles;

    public Board(int rows, int cols, float x, float y) {
        this.x = x;
        this.y = y;
        tiles = new ArrayList<ArrayList<Tile>>();
        for (int i = 0; i < rows; ++i) {
            ArrayList<Tile> row = new ArrayList<Tile>();
            for (int j = 0; j < cols; ++j) {
                if ((i + j) % 2 == 0) {
                    row.add(new Tile("red.jpg", j, i));
                } else {
                    row.add(new Tile("green.png", j, i));
                }
            }
            tiles.add(row);
        }
    }

    public void draw(PolygonSpriteBatch batch) {
        for (ArrayList<Tile> row : tiles) {
            for (Tile t : row) {
                t.draw(batch, x, y);
            }
        }
    }
    public void addToX(float dx) {
        x += dx;
    }
    public void addToY(float dy) {
        y += dy;
    }
}