package com.kjbre.verts.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.kjbre.verts.background.BackgroundSprite;
import com.kjbre.verts.player.Chassis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.kjbre.verts.projectile.*;
import com.badlogic.gdx.audio.*;
import com.kjbre.verts.player.*;

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

    public BackgroundSprite loadBackgroundSprite(String name) {
        String defFile = "gamedefs/sprites/background/" + name + ".def";

        DefinitionFile defs = manager.get(defFile, DefinitionFile.class);

        return  generateBackgroundSprite(defs);
    }

    private Chassis generatePlayerChassisSprite(DefinitionFile file){
        Texture texture = manager.get(file.location + file.properties.getProperty("sprite") + ".png");
        float moveSpeed = Float.valueOf(file.getProperties().getProperty("speed"));
        float armorLevel = Float.valueOf(file.getProperties().getProperty("armor"));
        float shipClass = Float.valueOf(file.getProperties().getProperty("class"));
        float extraWeapon = Float.valueOf(file.getProperties().getProperty("weapons"));
        float regenMulti = Float.valueOf(file.getProperties().getProperty("regenMulti"));
        float sidekicks = Float.valueOf(file.getProperties().getProperty("sidekicks"));
        return new Chassis(texture, moveSpeed, armorLevel, shipClass, extraWeapon, regenMulti);
    }

    public Chassis loadPlayerChassisSprite(String name){
        String defFile = "gamedefs/sprites/chassis/" + name + ".def";


        DefinitionFile defs = manager.get(defFile, DefinitionFile.class);

        return  generatePlayerChassisSprite(defs);
    }

    public Weapon generateWeapon(String name){
      String defFile = "gamedefs/sprites/weapons/" + name + ".def";
      DefinitionFile defs = manager.get(defFile, DefinitionFile.class);

      float refireRate = Float.valueOf(defs.getProperties().getProperty("refireRate"));
      Projectile weaponProjectile = new Projectile();
      Sound fireSound = manager.get("sound/weapons/" + defs.getProperties().getProperty("fireSound") + ".wav", Sound.class);

      return new Weapon(refireRate, weaponProjectile, fireSound);
    }
}
