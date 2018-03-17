package com.kjbre.verts.background;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class BackgroundRenderHandler {

    ArrayList<BackgroundSprite> sprites = new ArrayList<BackgroundSprite>();
    public void draw(SpriteBatch batch){
        batch.begin();
        for(BackgroundSprite s:sprites){
            s.draw(batch);
        }
        batch.end();
        sprites.clear();
    }

    public void processSprite(BackgroundSprite sprite){
        sprites.add(sprite);
    }
}
