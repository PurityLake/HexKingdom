package com.kingdom.board;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import com.kingdom.graphics.SingleColorShader;

public class Board {
    private float x, y;
    private ArrayList<ArrayList<Tile>> tiles;
    SingleColorShader scs;

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
        scs = new SingleColorShader();
        scs.init();
    }

    public void draw(Camera camera) {
        int x = 0;
        for (ArrayList<Tile> row : tiles) {
            for (Tile t : row) {
                Matrix4 originalWorld = new Matrix4();
                originalWorld.set(t.getWorld());
                modelBatch.begin(camera);

                Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
                Gdx.gl.glEnable(GL20.GL_STENCIL_TEST);
                Gdx.gl.glStencilOp(GL20.GL_KEEP, GL20.GL_KEEP, GL20.GL_REPLACE);
                
                Gdx.gl.glStencilFunc(GL20.GL_ALWAYS, 1, 0xFF);
                Gdx.gl.glStencilMask(0xFF);
                modelBatch.render(t.getInstance(), t.getShader());

                Gdx.gl.glStencilFunc(GL20.GL_NOTEQUAL, 1, 0xFF);
                Gdx.gl.glStencilMask(0x00);
                Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
                scs.setWorld(t.getWorld().scale(1.5f, 1.5f, 1.5f));
                modelBatch.render(t.getInstance(), scs);
                t.setWorld(originalWorld);
                
                Gdx.gl.glStencilMask(0xFF);
                Gdx.gl.glStencilFunc(GL20.GL_ALWAYS, 1, 0xFF);
                Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
                modelBatch.end();
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