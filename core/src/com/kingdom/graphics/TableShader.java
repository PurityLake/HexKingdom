package com.kingdom.graphics;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.graphics.Texture;

public class TableShader implements Shader {
    ShaderProgram program;
    Camera camera;
    RenderContext context;
    int u_projTrans;
    int u_worldTrans;
    int u_texture;
    Matrix4 world;
    Texture texture;

    public void setWorld(Matrix4 world) {
        this.world = world;
    }

    @Override
    public void init () {
        String vert = Gdx.files.internal("tablevert.glsl").readString();
        String frag = Gdx.files.internal("tablefrag.glsl").readString();
        texture = new Texture(Gdx.files.internal("table.jpg"));
        program = new ShaderProgram(vert, frag);
        if (!program.isCompiled())
            throw new GdxRuntimeException(program.getLog());
        u_projTrans = program.getUniformLocation("u_projTrans");
        u_worldTrans = program.getUniformLocation("u_worldTrans");
        u_texture = program.getUniformLocation("u_texture");
        world = new Matrix4();
    }

    @Override
    public void dispose () {
        program.dispose();
    }

    @Override
    public void begin(Camera camera, RenderContext context) {
        this.camera = camera;
        this.context = context;
        program.bind();
        program.setUniformMatrix(u_projTrans, camera.combined);
        context.setDepthTest(GL20.GL_LEQUAL);
        context.setCullFace(GL20.GL_BACK);
    }

    @Override
    public void render(Renderable renderable) {
        program.setUniformMatrix(u_worldTrans, world);
        texture.bind(0);
        program.setUniformf(u_texture, 0);
        renderable.meshPart.render(program);
    }

    @Override
    public void end() { }

    @Override
    public int compareTo (Shader other) {
        return 0;
    }
    @Override
    public boolean canRender (Renderable instance) {
        return true;
    }
}