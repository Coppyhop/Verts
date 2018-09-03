package com.kjbre.verts.player;

import com.kjbre.verts.projectile.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.Gdx;
/*
 * Weapon.java
 * This class holds all relevant information to a weapon, such as fire rate and projectile type
 */
public class Weapon{

  private float refireRate;
  private Projectile projectileType;
  private Sound fireSound;
  private float currentRefire = 0f;
  private boolean canFire = true;
  public Weapon(float refireRate, Projectile projectile, Sound fireSound){
    this.refireRate = 0.15f * refireRate;
    this.projectileType = projectile;
    this.fireSound = fireSound;
  }

  public float getRefireRate(){
    return refireRate;
  }

  Projectile getProjectileType(){
    return projectileType;
  }

  Sound getFireSound(){
    return fireSound;
  }

  public Weapon getClone(){
    return new Weapon(refireRate, projectileType, fireSound);
  }

  boolean checkFire(){
    if(currentRefire == 0){
      currentRefire = refireRate;
        canFire = true;
      return true;
    }

    if(!canFire) {
        currentRefire -= 1f * Gdx.graphics.getDeltaTime();

        if (currentRefire <= 0) {
            currentRefire = 0;
        }

        return false;
    } else {
        return true;
    }
  }

  void fire(){
      canFire = false;
  }
}
