package org.academiadecodigo.bootcamp.wormgame;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Character implements Hittable {

    //private Player player;
    private int health;
    //private aim;
    //private WeaponType weaponType;

    
    public void move() {

    }

    public void fire() {

    }

    public void suffer(int sufferDamage){
        health -= sufferDamage;
    }



}
