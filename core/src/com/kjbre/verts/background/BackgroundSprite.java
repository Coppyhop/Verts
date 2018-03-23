package com.kjbre.verts.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/*
 *  Background Sprite
 *  the basic definition for an object you'd see floating down in the background (Stars, asteroids, planets)
 *  TODO: Refactor to 'Background object'
 */
public class BackgroundSprite {

    private final Texture texture;
    final Sprite sprite;
    private float movementScale;

    public BackgroundSprite(Texture texture, float movementScale){
        Random r = new Random();
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.movementScale = movementScale;
        sprite.setPosition(r.nextInt(640),500);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void update(float delta){
        sprite.setPosition(sprite.getX(), sprite.getY() - (48 * delta * movementScale));
    }

    public BackgroundSprite clone(){
        return new BackgroundSprite(this.texture, movementScale);
    }
}
