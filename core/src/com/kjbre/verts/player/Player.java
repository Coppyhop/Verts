package com.kjbre.verts.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

    ChassisSprite currentChassis;
    float x = 320, y = 240;
    public void setCurrentChassis(ChassisSprite sprite){
        this.currentChassis = sprite;
    }

    public void draw(SpriteBatch spriteBatch){
        currentChassis.draw(spriteBatch, x, y);
    }
}
