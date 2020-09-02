package com.kingdom.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Matrix4;

import com.badlogic.gdx.math.Vector3;
import com.kingdom.graphics.TableShader;

public class GameTable {
    private int x, y;

    private static boolean first = true;
    private static Model model;
    private static ModelInstance instance;
    private Matrix4 world;
    private TableShader shader;

    public GameTable() {
        if (first) {
            ModelLoader loader = new ObjLoader();
            model = loader.loadModel(Gdx.files.internal("table.obj"));
            first = false;
            instance = new ModelInstance(model);
        }
        world = new Matrix4();
        shader = new TableShader();
        shader.init();
        shader.setWorld(world.translate(3.5f, -0.1f, 3.0f).scale(15, 1f, 10f));
    }

    public ModelInstance getInstance() {
        return instance;
    }
    public TableShader getShader() {
        return shader;
    }
}