package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxProjectileType;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Weapon implements Fireable {

    private int ammo;

    private WeaponType weapon;

    public Weapon(WeaponType weapon) {

        this.ammo = weapon.getAmmo();
        this.weapon = weapon;

    }

    public int getAmmo() {

        return ammo;

    }

    public void addAmmo(int munitions) {

        ammo += munitions;

    }

    @Override
    public void fire(Vector2D position, double aim) {

        if (ammo > 0) {

            Vector2D muzzle = new Vector2D(1.0, 0.0);
            muzzle.multiply(weapon.getBarrelLength());
            muzzle.rotate(aim);
            muzzle.add(position);
            Projectile projectile = new Projectile(weapon.getBullet(), muzzle);
            projectile.setVelocity(weapon.getShotSpeed());

            ammo--;
        }

        if (ammo <= 0) {
            System.out.println("No ammo left...");
        }

    }

    public WeaponType getWeapon(){

        return weapon;

    }
}
