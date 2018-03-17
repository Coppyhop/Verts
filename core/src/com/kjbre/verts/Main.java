package com.kjbre.verts;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kjbre.verts.common.Game;

public class Main extends ApplicationAdapter {

	Game game;
	
	@Override
	public void create () {
		game = new Game();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.draw();

	}
	
	@Override
	public void dispose () {
		game.dispose();
	}
}
