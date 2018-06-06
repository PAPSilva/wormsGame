package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxProjectile;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Character extends CircularBody2D implements Hittable, Shooter {

    private int health;
    private double aim;
    Fireable currentWeapon;

    public Character(double mass, double radius, Vector2D position, int health) {


        super(mass,radius, position);
        this.currentWeapon = new Weapon(WeaponType.BAZOOKA);
        this.health = health;
        this.aim = 0;

    }


    public void move(double x, double y) {

        this.getPosition().add(x, y);

    }


    // for now, receives a double that can be positive or negative. it can increase or decrease aim.
    public void changeAim(double angle) {

        this.aim += angle;

    }



    public void fire() {

        currentWeapon.fire(getPosition(), aim);

    }


    public void changeWeapon(Fireable weapon) {

        this.currentWeapon = weapon;


        //currentWeapon.fire(); // this doesn't seem ok

    }

    @Override
    public void suffer(int sufferDamage){

        health -= sufferDamage;

    }

    public Vector2D getPosition() {

        return super.getPosition();

    }




}
