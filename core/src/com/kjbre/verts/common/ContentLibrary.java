package com.kjbre.verts.common;

import com.badlogic.gdx.assets.AssetManager;
import com.kjbre.verts.background.BackgroundSprite;
import com.kjbre.verts.player.ChassisSprite;
import com.badlogic.gdx.graphics.Texture;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/*
 * Content Library
 * A kind-of self-explantory class that deals with loading and managing all game assets.
 * This class contains the asset manager and base copies for every game object type
 */
class ContentLibrary {
    private final Random random = new Random();
    private final ContentLoader contentLoader;
    private final AssetManager assetManager;

    private final ArrayList<BackgroundSprite> validBackgroundSprites = new ArrayList<BackgroundSprite>();
    private final ArrayList<ChassisSprite> validChassisSprites = new ArrayList<ChassisSprite>();
    public boolean loaded = false;

    ContentLibrary(){
        assetManager = new AssetManager();
        contentLoader = new ContentLoader(assetManager);
    }

    //This is where content loading is started
    public void initialize(){

        System.out.println("[INFO] Beginning initial asset collection.");
        System.out.println("[INFO] Now on: Sprites.");
        loadTextureFolder("sprites");
        System.out.println("[INFO] Finished asset collection.");


    }

    //Method used to load definition files from a specific folder, takes a definition type to know where to store the defined objects.
    private void loadDefsFromFolder(String name, DefinitionType type) throws IOException{
        File folder = new File("gamedefs/sprites/"+name+"/");
        File[] listOfFiles = folder.listFiles();

        System.out.println("[INFO] Loading Defintions from '" + folder.getPath() + "'");
        if(type == DefinitionType.BACKGROUND){
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    System.out.println("[INFO] Loading Defintion file '" + listOfFile.getName() + "' for type 'BACKGROUND'");
                    validBackgroundSprites.add(contentLoader.loadBackgroundSprite(listOfFile.getName().substring(0, listOfFile.getName().lastIndexOf('.'))));
                }
            }
        }

        if(type == DefinitionType.CHASSIS){
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    System.out.println("[INFO] Loading Defintion file '" + listOfFile.getName() + "' for type 'CHASSIS'");
                    validChassisSprites.add(contentLoader.loadPlayerChassisSprite(listOfFile.getName().substring(0, listOfFile.getName().lastIndexOf('.'))));
                }
            }
        }

    }

    //Get the asset manager's progress on loading
    public boolean getLoaded(){
        return assetManager.update();
    }

    //Asset manager is finished, but the game itself isn't done yet, this is where we load in definitions
    public void finalizedLoading(){
        System.out.println("[INFO] Now Beginning Definition File collection.");
        try {
            loadDefsFromFolder("background", DefinitionType.BACKGROUND);
            loadDefsFromFolder("chassis", DefinitionType.CHASSIS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Definition File collection complete.");

        System.out.println("[INFO] Verts Content Library Creation complete.");
        loaded = true;
    }

    //Method used to recursively load sprites from all subfolders and place them in the asset manager.
    private void loadTextureFolder(String folderName){
        File folder = new File(folderName);


        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        if(listOfFiles.length == 0){
            System.out.println("[INFO] Resource folder '" + folder.getPath() + "' is empty. ");
        } else {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    System.out.println("[INFO] Loading sprite '" + listOfFile.getName() + "' from '" + listOfFile.getPath() + "'");
                    assetManager.load(listOfFile.getPath(), Texture.class);
                } else {
                    System.out.println("[INFO] Found a resource folder at: " + listOfFile.getPath());
                    loadTextureFolder(listOfFile.getPath());
                }
            }
        }
    }

    //These two methods are basic methods to get content out of the library
    //TODO: Random rarity-based, and name searches
    public BackgroundSprite getRandomBackgroundSprite(){
        return validBackgroundSprites.get(random.nextInt(validBackgroundSprites.size())).clone();
    }

    public ChassisSprite getRandomChassisSprite(){
        return validChassisSprites.get(random.nextInt(validChassisSprites.size())).clone();
    }
}
