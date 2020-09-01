package com.kingdom.board;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public class Board {
    private float x, y;
    private ArrayList<ArrayList<Tile>> tiles;

    private ModelBatch modelBatch;    

    public Board(int rows, int cols, float x, float y) {
        modelBatch = new ModelBatch();
        this.x = x;
        this.y = y;
        tiles = new ArrayList<>();
        for (int i = 0; i < rows; ++i) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; (i % 2 == 0) ? (j < cols) : (j < cols + 1); ++j) {
                if ((i + j) % 2 == 0) {
                    row.add(new Tile(j, i));
                } else {
                    row.add(new Tile(j, i));
                }
            }
            tiles.add(row);
        }
    }

    public void draw(Camera camera) {
        modelBatch.begin(camera);
        for (ArrayList<Tile> row : tiles) {
            for (Tile t : row) {
                modelBatch.render(t.getInstance(), t.getShader());
            }
        }
        modelBatch.end();
    }
    public void addToX(float dx) {
        x += dx;
    }
    public void addToY(float dy) {
        y += dy;
    }
}