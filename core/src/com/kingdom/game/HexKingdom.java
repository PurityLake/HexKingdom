package com.kingdom.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;

import com.kingdom.board.Board;

public class HexKingdom extends ApplicationAdapter {
	Board board;
	PolygonSpriteBatch batch;
	
	@Override
	public void create() {
		batch = new PolygonSpriteBatch();
		board = new Board(5, 5, 0.0f, 0.0f);
	}

	@Override
	public void render() {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			board.addToX(-30.0f * Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			board.addToX(30.0f * Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			board.addToY(30.0f * Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			board.addToY(-30.0f * Gdx.graphics.getDeltaTime());
		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		board.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}
