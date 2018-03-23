package com.kjbre.verts.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.kjbre.verts.background.BackgroundSprite;
import com.kjbre.verts.player.ChassisSprite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
 *  Content Loader
 *  What this class represents can be confusing.
 *  It's entirely about loading and generating base copies for every sprite (soon to be object) type
 *  TODO: Refactor into 'ContentGenerator' or 'ObjectGenerator' as the current name is confusing, it does not deal with loading any game content.
 */
class ContentLoader {

    private final AssetManager manager;

    ContentLoader(AssetManager assetManager){
        manager = assetManager;
    }

    private BackgroundSprite generateBackgroundSprite(DefinitionFile file){

        Texture texture = manager.get(file.location + file.properties.getProperty("sprite") + ".png");
        float movementScale = Float.valueOf(file.getProperties().getProperty("speedScale"));
        return new BackgroundSprite(texture, movementScale,0);
    }

    public BackgroundSprite loadBackgroundSprite(String name) throws IOException {
        String defFile = "gamedefs/sprites/background/" + name + ".def";

        Properties props = new Properties();
        props.load(new FileInputStream(defFile));

        DefinitionFile defs = new DefinitionFile("sprites/background/", DefinitionType.BACKGROUND, name, props);

        return  generateBackgroundSprite(defs);
    }

    private ChassisSprite generatePlayerChassisSprite(DefinitionFile file){
        Texture texture = manager.get(file.location + file.properties.getProperty("sprite") + ".png");
        float centerX = Float.valueOf(file.getProperties().getProperty("centerX"));
        float centerY = Float.valueOf(file.getProperties().getProperty("centerY"));
        return new ChassisSprite(texture, centerX, centerY);
    }

    public ChassisSprite loadPlayerChassisSprite(String name) throws IOException {
        String defFile = "gamedefs/sprites/chassis/" + name + ".def";

        Properties props = new Properties();
        props.load(new FileInputStream(defFile));

        DefinitionFile defs = new DefinitionFile("sprites/chassis/", DefinitionType.CHASSIS, name, props);

        return  generatePlayerChassisSprite(defs);
    }
}
