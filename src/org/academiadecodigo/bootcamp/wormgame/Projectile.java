package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Projectile extends CircularBody2D implements PainGiver, Hittable {

    private ProjectileType projectileType;
    private int active;


    public Projectile(ProjectileType projectileType, Vector2D position){

        super(projectileType.getMass(), projectileType.getRadius(), position);
        this.projectileType = projectileType;
        this.active = 1;
    }

    public void hit(Hittable hittable) {

        hittable.suffer(projectileType.getAmmoDamage());

    }

    public void suffer(int damage) {
        this.active -= damage;
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }

    public int getDamage() {
        return projectileType.getAmmoDamage();
    }

}
