package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxProjectileType;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Character extends CircularBody2D implements Hittable {

    //private Player player;
    private int health;
    private double aim;
    Weapon currentWeapon;

    public Character(Vector2D position) {

        super(0,30, position);
        this.currentWeapon = new Weapon(WeaponType.SNIPER);
        this.health = 100;
        this.aim = 40;

    }


    public void move(double x, double y) {

        this.getPosition().add(x, y);

    }


    // for now, receives a double that can be positive or negative. it can increase or decrease aim.
    public void changeAim(double a) {

        this.aim += a;

    }

    // the fire method needs the weapon type
    public void fire(SgfxViewport viewport) {


        currentWeapon.fire(this.getPosition(), this.aim, viewport); // this doesn't seem ok

    }

    @Override
    public void suffer(int sufferDamage){

        health -= sufferDamage;

    }

    public Vector2D getPosition() {

        return super.getPosition();

    }


}
