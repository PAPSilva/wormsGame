package org.academiadecodigo.bootcamp.wormgame;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Weapon {

    //Projectile pewpew = new Projectile(ProjectileType);
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
