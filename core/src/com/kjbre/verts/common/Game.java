package com.kjbre.verts.common;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kjbre.verts.background.BackgroundRenderHandler;
import com.kjbre.verts.background.BackgroundSprite;

import java.io.File;
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
        loadStars();


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

    public void loadStars(){
        File folder = new File("gamedefs/sprites/background/");
        File[] listOfFiles = folder.listFiles();
        try {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    validStars.add(ContentLoader.loadBackgroundSprite(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().lastIndexOf('.'))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
