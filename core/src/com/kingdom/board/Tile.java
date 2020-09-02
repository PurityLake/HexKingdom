package com.kingdom.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import com.kingdom.graphics.TileShader;

public class Tile {
    private int x, y;

    private static boolean first = true;
    private static Model model;
    private static ModelInstance instance;
    private Matrix4 world;
    private TileShader shader;

    public Matrix4 getWorld() {
        return world;
    }

    public void setWorld(Matrix4 w) {
        world.set(w);
    }

    public Vector3 getColor() {
        return shader.getColor();
    }

    public void setColor(Vector3 w) {
        shader.getColor().set(w);
    }

    public Tile(int x, int y) {
        if (first) {
            ModelLoader loader = new ObjLoader();
            model = loader.loadModel(Gdx.files.internal("tile.obj"));
            first = false;
            instance = new ModelInstance(model);
        }
        world = new Matrix4();
        shader = new TileShader();
        this.x = x;
        this.y = y;
        if (y % 2 == 0) {
            world.set(world.translate(x * 1.85f, 0, y * 1.5f));
        } else {
            world.set(world.translate(x * 1.85f - 0.75f, 0, y * 1.5f));
        }
        shader.init();
        shader.setWorld(world);
        if ((x + y) % 2 == 0) {
            shader.setColor(new Vector3(1f, 0f, 0f));
        } else {
            shader.setColor(new Vector3(0f, 1f, 0f));
        }
    }

    public ModelInstance getInstance() {
        return instance;
    }
    public TileShader getShader() {
        return shader;
    }
}