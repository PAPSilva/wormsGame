package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxWeapon;
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
    public Projectile fire(Vector2D position, double aim) {

        if (ammo > 0) {

            Vector2D muzzle = new Vector2D(1.0, 0.0);
            muzzle.rotate(aim);
            Vector2D outVelocity = new Vector2D(muzzle);
            muzzle.multiply(weapon.getBarrelLength());
            muzzle.add(position);
            outVelocity.multiply(weapon.getShotSpeed());
            System.out.println(muzzle + " " + outVelocity);
            Projectile projectile = new Projectile(weapon.getBullet(), muzzle);
            projectile.setVelocity(outVelocity);
            ammo--;

            return projectile;

        }
        System.out.println("Ammo out");
        //TODO needs to be doublechecked if the null is right
        return null;
    }

    public WeaponType getWeaponType(){

        return weapon;

    }

    @Override
    public boolean equals(Object o) {

        if(!(o instanceof Fireable)) {
            return false;
        }

        Fireable fireable = (Fireable) o;
        System.out.println("equals!");
        return weapon == fireable.getWeaponType();
    }
}
