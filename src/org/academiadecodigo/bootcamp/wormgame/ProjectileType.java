package org.academiadecodigo.bootcamp.wormgame;

/**
 * Created by codecadet on 03/06/2018.
 */
public enum ProjectileType {

    BULLET(200, 2, 40),
    ROCKET(20, 4, 80);

    private int ammoDamage;
    private double mass;
    private double radius;


    ProjectileType (double mass, double radius, int ammoDamage){
        this.ammoDamage = ammoDamage;
        this.mass = mass;
        this.radius = radius;
    }

    public int getAmmoDamage(){
        return ammoDamage;
    }
    public double getMass(){
        return mass;
    }

    public double getRadius(){
        return radius;
    }

}
