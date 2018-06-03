package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public enum WeaponType {

    BAZOOKA(3, new Vector2D(10,20)),
    SNIPER(1, new Vector2D(200,0));


    private int ammo;
    private Vector2D iVelocity;

    WeaponType(int ammo, Vector2D iVelocity) {

        this.ammo = ammo;
        this.iVelocity = iVelocity;

    }

    public int getAmmo() {

        return ammo;

    }

}
