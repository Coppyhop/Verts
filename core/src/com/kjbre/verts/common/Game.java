package com.kjbre.verts.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kjbre.verts.background.BackgroundRenderHandler;
import com.kjbre.verts.background.BackgroundSprite;
import com.kjbre.verts.player.Player;

public class Game {

    SpriteBatch backgroundSprites;
    SpriteBatch projectileSprites;
    SpriteBatch entitySprites;
    Player player;
    BackgroundRenderHandler backgroundRenderHandler;


    BackgroundSprite test;
    Texture loadingScreen;
    Sprite loadingSprite;

    private int lastStar = 0, starInterval = 10;
    private ContentLibrary library = new ContentLibrary();
    boolean once = false;

    public Game(){
        System.out.println("[INFO] Verts Engine Starting.");
        backgroundSprites = new SpriteBatch();
        projectileSprites = new SpriteBatch();
        entitySprites = new SpriteBatch();
        backgroundRenderHandler = new BackgroundRenderHandler();

        System.out.println("[INFO] Setting up Asset Collection Environment.");
        loadingScreen = new Texture(Gdx.files.internal("loading.png"));
        loadingSprite = new Sprite(loadingScreen);
        loadingSprite.setPosition(0,0);

        library.initialize();

        player = new Player();



    }
    public void draw(){
        if(library.getLoaded()) {

            if (library.loaded) {
                if(!once){
                    System.out.println("[INFO] Entering game State");
                    player.setCurrentChassis(library.getRandomChassisSprite());
                    once = true;
                }
                generateStarfield();
                backgroundRenderHandler.draw(backgroundSprites);

                entitySprites.begin();
                player.draw(entitySprites);
                entitySprites.end();
            } else {
                library.finalizedLoading();
            }
        } else {
            backgroundSprites.begin();
            loadingSprite.draw(backgroundSprites);
            backgroundSprites.end();
        }
    }

    public void dispose(){
        backgroundSprites.dispose();
        projectileSprites.dispose();
        entitySprites.dispose();
    }

    public void generateStarfield(){
        if(lastStar >= starInterval){
            lastStar = 0;
            backgroundRenderHandler.processSprite(library.getRandomBackgroundSprite());
        } else {
            lastStar++;
        }
    }


}
