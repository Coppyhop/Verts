package com.kjbre.verts.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 *  Chassis Sprite
 *  This class holds the info needed for a Chassis Sprite
 *  TODO: Refactor into 'Chassis.java'
 */
public class ChassisSprite {

    private final Texture texture;
    private final float centerX;
    private final float centerY;
    private final Sprite sprite;

    public ChassisSprite(Texture texture, float centerX, float centerY){
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.sprite.setCenter(centerX, centerY);
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void draw(SpriteBatch batch, float x, float y){
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }

    public ChassisSprite clone(){
        return new ChassisSprite(texture, centerX, centerY);
    }
}
