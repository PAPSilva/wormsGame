package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxViewport;

import java.util.ArrayList;

/**
 * Created by codecadet on 04/06/2018.
 */
public class Player {

    private String name;
    private Weapon weapons;
    private Character characters;

    public Player(String name, Character characters) {

        ArrayList weapons = new ArrayList();

        this.name = name;
        this.characters = characters;

    }

    private String getName() {

        return name;

    }

}
