package com.kjbre.verts.player;

import com.kjbre.verts.projectile.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics;
/*
 * Weapon.java
 * This class holds all relevant information to a weapon, such as fire rate and projectile type
 */
public class Weapon{

  float refireRate = 0.15f;
  Projectile projectileType;
  Sound fireSound;
  float currentRefire = 0f;
  public Weapon(float refireRate, Projectile projectile, Sound fireSound){
    this.refireRate = 0.15f * refireRate;
    this.projectileType = projectile;
    this.fireSound = fireSound;
  }

  public float getRefireRate(){
    return refireRate;
  }

  public Projectile getProjectileType(){
    return projectileType;
  }

  public Sound getFireSound(){
    return fireSound;
  }

  public Weapon getClone(){
    return new Weapon(refireRate, projectileType, fireSound);
  }

  public boolean checkFire(){
    if(currentRefire == 0){
      currentRefire = refireRate;
      return true;
    }

    currentRefire-= 1f*Gdx.graphics.getDeltaTime();

    if(currentRefire <= 0){
      currentRefire = 0;
    }

    return false;
  }
}
