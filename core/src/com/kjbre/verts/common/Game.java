package com.kjbre.verts.common;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kjbre.verts.background.BackgroundRenderHandler;
import com.kjbre.verts.background.BackgroundSprite;
import com.kjbre.verts.player.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    SpriteBatch backgroundSprites;
    SpriteBatch projectileSprites;
    SpriteBatch entitySprites;
    Player player;
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
        player = new Player();
        try {
            player.setCurrentChassis(ContentLoader.loadPlayerChassisSprite("base"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void draw(){
        generateStarfield();
        backgroundRenderHandler.draw(backgroundSprites);

        entitySprites.begin();
        player.draw(entitySprites);
        entitySprites.end();
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
