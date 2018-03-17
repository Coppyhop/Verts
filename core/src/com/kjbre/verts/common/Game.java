package com.kjbre.verts.common;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kjbre.verts.background.BackgroundRenderHandler;
import com.kjbre.verts.background.BackgroundSprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    SpriteBatch backgroundSprites;
    SpriteBatch projectileSprites;
    SpriteBatch entitySprites;

    BackgroundRenderHandler backgroundRenderHandler;


    BackgroundSprite test;

    private int lastStar = 0, starInterval = 10;
    private ArrayList<BackgroundSprite> validStars = new ArrayList<BackgroundSprite>();
    public Game(){
        backgroundSprites = new SpriteBatch();
        projectileSprites = new SpriteBatch();
        entitySprites = new SpriteBatch();
        backgroundRenderHandler = new BackgroundRenderHandler();
        try {
            validStars.add(ContentLoader.loadBackgroundSprite("starLarge"));
            validStars.add(ContentLoader.loadBackgroundSprite("starMedium"));
            validStars.add(ContentLoader.loadBackgroundSprite("starSmall"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void draw(){
        generateStarfield();
        backgroundRenderHandler.draw(backgroundSprites);
    }

    public void dispose(){
        backgroundSprites.dispose();
        projectileSprites.dispose();
        entitySprites.dispose();
    }

    public void generateStarfield(){
        if(lastStar >= starInterval){
            lastStar = 0;
            backgroundRenderHandler.processSprite(validStars.get(new Random().nextInt(validStars.size())).clone());
        } else {
            lastStar++;
        }
    }
}
