package com.kjbre.verts.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 *  Player class
 *  This is the class that holds all of the player information, such as the current Chassis, weapons, and such
 */
public class Player {

    private ChassisSprite currentChassis;
    private final float x = 320;
    private final float y = 180;
    public void setCurrentChassis(ChassisSprite sprite){
        this.currentChassis = sprite;
    }

    public void draw(SpriteBatch spriteBatch){
        currentChassis.draw(spriteBatch, x, y);
    }
}
