package com.kjbre.verts.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kjbre.verts.background.BackgroundRenderHandler;
import com.kjbre.verts.player.Player;

/*
 *  Game
 *  Here it is, the main game class. This is going to get QUITE complicated I feel.
 *  While I try to keep my code self-explanatory this class will definitely need some explaining.
 */
public class Game {

    private final SpriteBatch backgroundSprites;
    private final SpriteBatch projectileSprites;
    private final SpriteBatch entitySprites;
    private final Player player;
    private final BackgroundRenderHandler backgroundRenderHandler;


    private final Texture loadingScreen, loadingProgress;
    private final Sprite loadingSprite, loadingProgressSprite;

    private int lastStar = 0;
    private final ContentLibrary library = new ContentLibrary();
    private boolean once = false;


    public Game(){
        //Here we setup the basic rendering stuffs
        System.out.println("[INFO] Verts Engine Starting.");
        backgroundSprites = new SpriteBatch();
        projectileSprites = new SpriteBatch();
        entitySprites = new SpriteBatch();
        backgroundRenderHandler = new BackgroundRenderHandler();

        //This is for the loading screen
        System.out.println("[INFO] Setting up Asset Collection Environment.");
        loadingScreen = new Texture(Gdx.files.internal("loading.png"));
        loadingSprite = new Sprite(loadingScreen);
        loadingSprite.setPosition(0,0);

        loadingProgress = new Texture(Gdx.files.internal("lprog.png"));
        loadingProgressSprite = new Sprite(loadingProgress);
        loadingSprite.setPosition(0,0);

        //Finally we start loading textures
        library.initialize();
        player = new Player();



    }
    public void draw(){
        //If all the textures aren't loaded in yet, display the loading screen and continue updating them.
        if(library.getLoaded()) {
            //Once all the textures, sounds, and music have been loaded it loads in the definition files
            if (library.loaded) {
                //Definition Files loaded in
                if(!once){
                    //One-time things to be setup before gameplay
                    System.out.println("[INFO] Entering game State");
                    player.setCurrentChassis(library.getRandomChassisSprite());
                    once = true;
                }

                //And here's the game loop
                library.playRandomSong();
                generateStarfield();
                backgroundRenderHandler.draw(backgroundSprites);

                entitySprites.begin();
                player.draw(entitySprites);
                entitySprites.end();
            } else {
                //Load all of the Definition files
                library.finalizedLoading();
            }
        } else {
            //Display the loading screen

            float progress = library.getProgress();

            backgroundSprites.begin();
            loadingSprite.draw(backgroundSprites);
            loadingProgressSprite.setBounds(5,5, 630 * progress, 24);
            loadingProgressSprite.draw(backgroundSprites);
            backgroundSprites.end();
        }
    }

    public void dispose(){
        backgroundSprites.dispose();
        projectileSprites.dispose();
        entitySprites.dispose();
    }

    //This method generates a background sprite every 10 units of time
    private void generateStarfield(){
        int starInterval = 10;
        if(lastStar >= starInterval){
            lastStar = 0;
            backgroundRenderHandler.processSprite(library.getRandomBackgroundSprite());
        } else {
            lastStar++;
        }
    }


}
