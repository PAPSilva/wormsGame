package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Projectile extends CircularBody2D implements PainGiver, Hittable {

    private ProjectileType projectileType;
    private int health = 1;

    public Projectile(ProjectileType projectileType, Vector2D position){

        super(projectileType.getMass(), projectileType.getRadius(), position);
        this.projectileType = projectileType;
    }

    @Override
    public boolean suffer(int damage) {

        if (damage > 0 && health > 0) {
            health -= (damage < health) ? damage : health;
            System.out.println("Boom!");
            return true;
        }
        return false;
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }

    public int getDamage() {
        return projectileType.getAmmoDamage();
    }

}
