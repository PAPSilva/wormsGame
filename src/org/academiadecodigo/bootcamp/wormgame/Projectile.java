package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Projectile extends CircularBody2D implements PainGiver {


    private ProjectileType projectileType;


    public Projectile(ProjectileType projectileType, Vector2D position){

        super(projectileType.getMass(), projectileType.getRadius(), position);
        this.projectileType = projectileType;
    }

    public void hit(Hittable hittable) {

        hittable.suffer(projectileType.getAmmoDamage());

    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }

    public int getDamage() {
        return projectileType.getAmmoDamage();
    }

}
