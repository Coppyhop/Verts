package com.kjbre.verts.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundSprite {

    Texture texture;
    Sprite sprite;

    public BackgroundSprite(Texture texture){
        this.texture = texture;
        this.sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }
}
