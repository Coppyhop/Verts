package com.kjbre.verts.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 *  Player class
 *  This is the class that holds all of the player information, such as the current Chassis, weapons, and such
 */
public class Player {

    private ChassisSprite currentChassis;
    private float x = 320;
    private float y = 180;
    float moveSpeed = 120;
    int numJumps = 2;
    float regenProgress = 0;
    private Sound warp;
    private float momentumX =0, momentumY=0;

    public void setCurrentChassis(ChassisSprite sprite){
        this.currentChassis = sprite;
    }

    public void setWarpSound(Sound sound){
        warp = sound;
    }

    public void draw(SpriteBatch spriteBatch){
        currentChassis.draw(spriteBatch, x, y);
    }

    public float getJumps(){
        return numJumps;
    }

    public float getNextJump(){
        return regenProgress/5f;
    }
    public void logic(){

        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            momentumX += 1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            momentumX -= 10;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
            momentumY += 1;


        }

        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            momentumY -= 10;

        }

        if(momentumX >=1){
            momentumX = 1;
        }

        if(momentumY <=-1){
            momentumY = -1;
        }

        if(momentumY >=1){
            momentumY = 1;
        }

        if(momentumX <=-1){
            momentumX = -1;
        }

        momentumY*=0.9;
        momentumX*=0.9;

        if(numJumps < 2){
            if(regenProgress < 5) {
                regenProgress += Gdx.graphics.getDeltaTime();
            } else {
                numJumps++;
                regenProgress = 0;
            }
        } else {
            regenProgress = 0;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)){
                if(numJumps >0) {
                    numJumps--;
                    warp.play();
                    x += momentumX * 96;
                    y += momentumY * 96;
                }
        } else {
                x += momentumX * moveSpeed * Gdx.graphics.getDeltaTime();
                y += momentumY * moveSpeed * Gdx.graphics.getDeltaTime();
        }



    }
}
