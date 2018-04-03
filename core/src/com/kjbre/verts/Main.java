package com.kjbre.verts;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.kjbre.verts.common.Game;

/*
 *	This is the main class for the game
 *	Not much goes on in here, as it's all deferred to the 'Game' class
 */
public class Main extends ApplicationAdapter {

	private Game game;

	@Override
	public void create () {
		game = new Game();
	}

	@Override
	public void render () {

		//Set the screen to black on every frame refresh
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Then immediatly pass it to the game
		game.draw();

	}

	@Override
	public void dispose () {
		game.dispose();
	}
}
