package org.academiadecodigo.bootcamp.wormgame;

import java.util.ArrayList;

/**
 * Created by codecadet on 04/06/2018.
 */
public class Player {

    private String name;
    private ArrayList<Fireable> weapons;
    private Character[] characters;

    public Player(String name) {

        weapons = new ArrayList();

        this.name = name;
        addWeapons();

    }


    private void addWeapons() {

        for (WeaponType weaponType : WeaponType.values()) {
            weapons.add(new Weapon(weaponType));
        }

    }

    public void changeWeapon() {

    }


    public void addCharacters(Character[] characters) {

        this.characters = characters;

    }


    private String getName() {

        return name;

    }

}
