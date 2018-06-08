package org.academiadecodigo.bootcamp.wormgame;

/**
 * Created by codecadet on 03/06/2018.
 */
public enum ProjectileType {

    BULLET(2, 2, 5),
    ROCKET(20, 10, 5);

    private double mass;
    private double radius;
    private int ammoDamage;

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
