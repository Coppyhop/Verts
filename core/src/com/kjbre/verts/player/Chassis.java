package com.kjbre.verts.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 *  Chassis
 *  This class holds the info needed for a Chassis
 */
public class Chassis {

    private final Texture texture;
    private final Sprite sprite;
    private final float moveSpeed, armorLevel, shipClass, extraWeapon, regenMulti;


    public Chassis(Texture texture, float moveSpeed, float armorLevel, float shipClass, float extraWeapon, float regenMulti){
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.moveSpeed = moveSpeed;
        this.armorLevel = armorLevel;
        this.shipClass = shipClass;
        this.extraWeapon = extraWeapon;
        this.regenMulti = regenMulti;

    }

    public void draw(SpriteBatch batch, float x, float y){
        sprite.setBounds(0,0,32,32);
        sprite.setPosition(x - 16, y - 16);
        sprite.draw(batch);
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getArmorLevel() {
        return armorLevel;
    }

    public float getShipClass() {
        return shipClass;
    }

    public float getExtraWeapon() {
        return extraWeapon;
    }

    public float getRegenMulti() {
        return regenMulti;
    }

    public Chassis getClone(){
        return new Chassis(texture, moveSpeed, armorLevel, shipClass, extraWeapon, regenMulti);
    }
}
