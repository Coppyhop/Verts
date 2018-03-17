package com.kjbre.verts.common;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kjbre.verts.background.BackgroundRenderHandler;
import com.kjbre.verts.background.BackgroundSprite;

import java.io.IOException;

public class Game {

    SpriteBatch backgroundSprites;
    SpriteBatch projectileSprites;
    SpriteBatch entitySprites;

    BackgroundRenderHandler backgroundRenderHandler;


    BackgroundSprite test;

    public Game(){
        backgroundSprites = new SpriteBatch();
        projectileSprites = new SpriteBatch();
        entitySprites = new SpriteBatch();
        backgroundRenderHandler = new BackgroundRenderHandler();
        try {
            test = ContentLoader.loadBackgroundSprite("starLarge");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void draw(){
        backgroundRenderHandler.processSprite(test);
        backgroundRenderHandler.draw(backgroundSprites);
    }

    public void dispose(){
        backgroundSprites.dispose();
        projectileSprites.dispose();
        entitySprites.dispose();
    }
}
