package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Character extends CircularBody2D implements Hittable {

    //private Player player;
    private int health;
    //private aim;
    //private WeaponType weaponType;

    public Character(Vector2D position) {
        super(0,2, position);
    }


    public void move() {

    }

    public void fire() {

    }

    public void suffer(int sufferDamage){
        health -= sufferDamage;
    }



}
