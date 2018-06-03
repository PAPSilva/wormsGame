package org.academiadecodigo.bootcamp.wormgame;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Weapon {

    //Projectile pewpew = new Projectile(ProjectileType);
    private int ammo;
    private WeaponType pewpew;

    public Weapon(WeaponType weapon) {

        this.ammo = weapon.getAmmo();
        pewpew = weapon;

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

        return pewpew;

    }

}
