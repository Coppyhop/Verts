package com.kjbre.verts.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kjbre.verts.background.BackgroundSprite;
import com.kjbre.verts.player.ChassisSprite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ContentLoader {

    private static BackgroundSprite generateBackgroundSprite(DefinitionFile file){

        Texture texture = new Texture(Gdx.files.internal(file.location + file.properties.getProperty("sprite") + ".png"));
        float movementScale = Float.valueOf(file.getProperties().getProperty("speedScale"));
        return new BackgroundSprite(texture, movementScale);
    }

    public static BackgroundSprite loadBackgroundSprite(String name) throws IOException {
        String defFile = "gamedefs/sprites/background/" + name + ".def";

        Properties props = new Properties();
        props.load(new FileInputStream(defFile));

        DefinitionFile defs = new DefinitionFile("sprites/background/", DefinitionType.BACKGROUND, name, props);

        return  generateBackgroundSprite(defs);
    }

    private static ChassisSprite generatePlayerChassisSprite(DefinitionFile file){
        Texture texture = new Texture(Gdx.files.internal(file.location + file.properties.getProperty("sprite") + ".png"));
        float centerX = Float.valueOf(file.getProperties().getProperty("centerX"));
        float centerY = Float.valueOf(file.getProperties().getProperty("centerY"));
        return new ChassisSprite(texture, centerX, centerY);
    }

    public static ChassisSprite loadPlayerChassisSprite(String name) throws IOException {
        String defFile = "gamedefs/sprites/chassis/" + name + ".def";

        Properties props = new Properties();
        props.load(new FileInputStream(defFile));

        DefinitionFile defs = new DefinitionFile("sprites/chassis/", DefinitionType.CHASSIS, name, props);

        return  generatePlayerChassisSprite(defs);
    }
}
