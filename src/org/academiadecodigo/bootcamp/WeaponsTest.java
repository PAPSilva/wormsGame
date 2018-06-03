package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.wormgame.Weapon;

import org.academiadecodigo.bootcamp.wormgame.WeaponType;

/**
 * Created by codecadet on 03/06/2018.
 */
public class WeaponsTest {

    public static void main(String[] args) {

        Weapon weapon = new Weapon(WeaponType.SNIPER);
        System.out.println("You got a " + weapon.getWeapon());
        System.out.println("You start with " + weapon.getAmmo() + " bullets.");

        weapon.fire(weapon);
        System.out.println("The weapon is firing! BOOOM!");
        System.out.println("Ammo left: " + weapon.getAmmo());
        weapon.addAmmo(4);
        System.out.println("Ammo updated! Now you have " + weapon.getAmmo() + " bullets.");
        weapon.fire(weapon);
        System.out.println("The weapon is firing! BOOOM!");
        System.out.println("Ammo left: " + weapon.getAmmo());
        weapon.fire(weapon);
        System.out.println("The weapon is firing! BOOOM!");
        System.out.println("Ammo left: " + weapon.getAmmo());

    }

}
