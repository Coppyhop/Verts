package com.kjbre.verts.player;

import com.kjbre.verts.projectile.*;
import com.badlogic.gdx.audio.*;
/*
 * Weapon.java
 * This class holds all relevant information to a weapon, such as fire rate and projectile type
 */
public class Weapon{

  float refireRate = 1.0f;
  Projectile projectileType;
  Sound fireSound;

  public Weapon(float refireRate, Projectile projectile, Sound fireSound){
    this.refireRate = refireRate;
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

}
