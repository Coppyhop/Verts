package com.kjbre.verts.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kjbre.verts.Main;

/*
 *	This is the Desktop Launcher
 *	This is a LibGDX class to load up and launch the game in desktop mode.
 *	Most of these values are self-explanatory, and all of their uses are covered by the LibGDX wiki
 */
class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Verts";
		config.width = 640;
		config.height = 360;
		new LwjglApplication(new Main(), config);
	}
}
