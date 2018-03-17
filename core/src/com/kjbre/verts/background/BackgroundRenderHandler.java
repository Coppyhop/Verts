package com.kjbre.verts.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class BackgroundRenderHandler {

    ArrayList<BackgroundSprite> sprites = new ArrayList<BackgroundSprite>();
    public void draw(SpriteBatch batch){
        batch.begin();
        for(int i=0;i<sprites.size();i++){
            if(sprites.get(i).sprite.getY() <= -64){
                sprites.remove(i);
            } else {
                sprites.get(i).update(Gdx.graphics.getDeltaTime());
                sprites.get(i).draw(batch);
            }
        }
        batch.end();

    }

    public void processSprite(BackgroundSprite sprite){
        sprites.add(sprite);
    }

}
