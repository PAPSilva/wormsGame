package org.academiadecodigo.bootcamp.wormgame;

/**
 * Created by codecadet on 03/06/2018.
 */
public enum ProjectileType {
    BULLET(40),
    ROCKET(80);



    private int ammoDamage;


    ProjectileType (int ammoDamage){
        this.ammoDamage = ammoDamage;
    }

    public int getAmmoDamage(){
        return ammoDamage;
    }


}
