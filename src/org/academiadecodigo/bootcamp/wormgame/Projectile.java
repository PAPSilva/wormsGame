package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Projectile extends CircularBody2D{


    private ProjectileType projectileType;
    private int ammoDamage;

    public Projectile(ProjectileType projectileType, Vector2D position){
        super(0,2,position);
        this.ammoDamage = projectileType.getAmmoDamage();

    }


    public void hit(Hitable){
    }
    

}
