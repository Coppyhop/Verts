package com.kjbre.verts.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import com.kjbre.verts.projectile.*;
/*
 *  Player class
 *  This is the class that holds all of the player information, such as the current Chassis, weapons, and such
 */
public class Player {

    private Chassis currentChassis;
    private float x = 320;
    private float y = 180;
    private int numJumps = 2;
    private float regenProgress = 0;
    private int numWeapons = 0;
    private Sound warp;
    private float momentumX =0, momentumY=0;
    private Weapon[] weapons;
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    public void setCurrentChassis(Chassis sprite){
        this.currentChassis = sprite;
        this.numWeapons = (int) sprite.getExtraWeapon();
        weapons = new Weapon[numWeapons];
    }

    public void setWeaponInSlot(int slot, Weapon weapon){
      if(slot < weapons.length){
      weapons[slot] = weapon;
    }
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
        return regenProgress/(9-(currentChassis.getMoveSpeed()*2));
    }
    public void logic(){
        for(Weapon w:weapons){
            w.checkFire();
        }

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

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
          fire();
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
            if(regenProgress < (9-(currentChassis.getMoveSpeed()*2))) {
                regenProgress += currentChassis.getRegenMulti() * Gdx.graphics.getDeltaTime();
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
                x += momentumX * 40 * currentChassis.getMoveSpeed() * Gdx.graphics.getDeltaTime();
                y += momentumY * 40 * currentChassis.getMoveSpeed() * Gdx.graphics.getDeltaTime();
        }

        if(x < 16){
            x=16;
        } else if (x > 624){
            x = 624;
        }

        if(y < 16){
            y=16;
        } else if (y > 344){
            y = 344;
        }



    }

    private void fire(){



      for(Weapon w:weapons){
        if(w != null){
            if(w.checkFire()){
            w.getFireSound().play();
            w.fire();
            projectiles.add(w.getProjectileType().makeClone());
          }

      }
      }
    }
}
