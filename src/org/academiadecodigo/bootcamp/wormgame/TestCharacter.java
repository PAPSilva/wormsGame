package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxCharacter;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class TestCharacter {

    public static void main(String[] args) throws InterruptedException {

        SgfxViewport simWindow = new SgfxViewport(640,400, 1.0);
        simWindow.show();


        SgfxCharacter sgfxCharacter = new SgfxCharacter(30,20, new Vector2D(100,30),100, simWindow);

        Character character = (Character) sgfxCharacter;

        sgfxCharacter.initKeyboard();


    }


}
