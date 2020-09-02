package com.kingdom.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

import com.kingdom.board.Board;
import com.kingdom.board.GameTable;

public class HexKingdom extends ApplicationAdapter {
	Board board;
	GameTable table;
	CameraInputController camController;
	PerspectiveCamera cam;
	ModelBatch modelBatch;
	
	@Override
	public void create() {
		table = new GameTable();
		board = new Board(5, 5, 0.0f, 0.0f);
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(10f, 10f, 0);
		cam.lookAt(0, 0, 0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();
		modelBatch = new ModelBatch();
		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor((camController));
	}

	@Override
	public void render() {
		camController.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_STENCIL_BUFFER_BIT);
		board.draw(cam);
		modelBatch.begin(cam);
		modelBatch.render(table.getInstance(), table.getShader());
		modelBatch.end();
		//table.draw(cam);
	}
	
	@Override
	public void dispose() {
		modelBatch.dispose();
	}
}
