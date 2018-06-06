package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxProjectileType;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Weapon {

    private int ammo;
    private WeaponType weapon1;
    private SgfxViewport viewport;

    public Weapon(WeaponType weapon) {

        this.ammo = weapon.getAmmo();
        weapon1 = weapon;

    }

    public int getAmmo() {

        return ammo;

    }

    public void addAmmo(int munitions) {

        ammo = ammo + munitions;

    }

    public Projectile fire(Vector2D position, double aim, SgfxViewport viewport) {

        SgfxProjectileType tiro = new SgfxProjectileType(weapon1.getBullet(), position, viewport);
        tiro.setVelocity(new Vector2D(100.0, 0.0));

        if (ammo > 0) {

            ammo--;
        }

        if (ammo <= 0) {

            System.out.println("No ammo left...");
        }

        return tiro;

    }

    public WeaponType getWeapon(){

        return weapon1;

    }
}
