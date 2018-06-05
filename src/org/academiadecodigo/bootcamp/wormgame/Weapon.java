package org.academiadecodigo.bootcamp.wormgame;

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

        ammo = ammo + munitions;

    }

    public void fire() {

        if (ammo > 0) {

            Projectile projectile = new Projectile(weapon.getBullet(), weapon.getShotSpeed());
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
