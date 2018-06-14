package org.academiadecodigo.bootcamp.wormgame.tests;

import org.academiadecodigo.bootcamp.gfx.SgfxCharacter;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.actors.Character;

/**
 * Created by codecadet on 03/06/2018.
 */
public class TestCharacter {

    public static void main(String[] args) throws InterruptedException {

        SgfxViewport simWindow = new SgfxViewport(640,400, 1.0);
        simWindow.show();


        SgfxCharacter sgfxCharacter = new SgfxCharacter(30,20, new Vector2D(100,30),100, 10, "soldier_skins/soldier_green.png", simWindow);

        Character character = (Character) sgfxCharacter;



    }


}
