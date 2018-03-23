package com.kjbre.verts.common;

import com.badlogic.gdx.assets.AssetManager;
import com.kjbre.verts.background.BackgroundSprite;
import com.kjbre.verts.player.ChassisSprite;
import com.badlogic.gdx.graphics.Texture;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ContentLibrary {
    Random random = new Random();
    ContentLoader contentLoader;
    AssetManager assetManager;

    private ArrayList<BackgroundSprite> validBackgroundSprites = new ArrayList<BackgroundSprite>();
    private ArrayList<ChassisSprite> validChassisSprites = new ArrayList<ChassisSprite>();
    public boolean loaded = false, error = false;
    String errorMessage = "";

    public ContentLibrary(){
        assetManager = new AssetManager();
        contentLoader = new ContentLoader(assetManager);
    }

    public void initialize(){

        System.out.println("[INFO] Beginning initial asset collection.");
        System.out.println("[INFO] Now on: Sprites.");
        loadTextureFolder("sprites");
        System.out.println("[INFO] Finished asset collection.");


    }

    private void loadDefsFromFolder(String name, DefinitionType type) throws IOException{
        File folder = new File("gamedefs/sprites/"+name+"/");
        File[] listOfFiles = folder.listFiles();

        System.out.println("[INFO] Loading Defintions from '" + folder.getPath() + "'");
        if(type == DefinitionType.BACKGROUND){
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("[INFO] Loading Defintion file '" + listOfFiles[i].getName() + "' for type 'BACKGROUND'");
                    validBackgroundSprites.add(contentLoader.loadBackgroundSprite(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().lastIndexOf('.'))));
                }
            }
        }

        if(type == DefinitionType.CHASSIS){
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("[INFO] Loading Defintion file '" + listOfFiles[i].getName() + "' for type 'CHASSIS'");
                    validChassisSprites.add(contentLoader.loadPlayerChassisSprite(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().lastIndexOf('.'))));
                }
            }
        }

    }

    public boolean getLoaded(){
        return assetManager.update();
    }

    public void finalizedLoading(){
        System.out.println("[INFO] Now Beginning Definiton File collection.");
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

    public void loadTextureFolder(String folderName){
        File folder = new File(folderName);


        File[] listOfFiles = folder.listFiles();

        if(listOfFiles.length == 0){
            System.out.println("[INFO] Resource folder '" + folder.getPath() + "' is empty. ");
        } else {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("[INFO] Loading sprite '" + listOfFiles[i].getName() + "' from '" + listOfFiles[i].getPath() + "'");
                    assetManager.load(listOfFiles[i].getPath(), Texture.class);
                } else {
                    System.out.println("[INFO] Found a resource folder at: " + listOfFiles[i].getPath());
                    loadTextureFolder(listOfFiles[i].getPath());
                }
            }
        }
    }

    public BackgroundSprite getRandomBackgroundSprite(){
        return validBackgroundSprites.get(random.nextInt(validBackgroundSprites.size())).clone();
    }

    public ChassisSprite getRandomChassisSprite(){
        return validChassisSprites.get(random.nextInt(validChassisSprites.size())).clone();
    }
}
