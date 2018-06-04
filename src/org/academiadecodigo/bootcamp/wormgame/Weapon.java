package org.academiadecodigo.bootcamp.wormgame;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Weapon {

    private int ammo;
    private WeaponType weapon1;

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

    public void fire(Weapon weapon) {

        Projectile projectile = new Projectile(weapon1.getBullet(), weapon1.getShotSpeed());

        projectile.setVelocity(weapon1.getShotSpeed());

        if (ammo > 0) {

            weapon.ammo--;

        }

        if (ammo < 0) {

            System.out.println("No ammo left...");

        }

    }

    public WeaponType getWeapon(){

        return weapon1;

    }

}
