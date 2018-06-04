package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public enum WeaponType {

    BAZOOKA(ProjectileType.ROCKET, new Vector2D(0.0,0.0), 3),
    SNIPER(ProjectileType.BULLET, new Vector2D(0.0,0.0), 1);


    private int ammo;
    private Vector2D shotSpeed;
    private ProjectileType bullet;

    WeaponType(ProjectileType bullet, Vector2D shotSpeed, int ammo) {

        this.ammo = ammo;
        this.shotSpeed = shotSpeed;
        this.bullet = bullet;

    }

    public int getAmmo() {

        return ammo;

    }

    public ProjectileType getBullet() {

        return bullet;

    }

    public Vector2D getShotSpeed() {

        return shotSpeed;

    }

}
