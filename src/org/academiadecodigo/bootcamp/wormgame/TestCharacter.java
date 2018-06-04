package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxCircularBody2D;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class TestCharacter {

    public static void main(String[] args) throws InterruptedException {

        SgfxViewport simWindow = new SgfxViewport(640,400, 1.0);
        simWindow.show();

        Character character = new Character(new Vector2D(200,30));
        SgfxCircularBody2D circle = new SgfxCircularBody2D(character.getMass(), character.getRadius(), character.getPosition().x(), character.getPosition().y(), simWindow);

        System.out.println("x: " + character.getPosition().x() + "; y: " + character.getPosition().y());

        Thread.sleep(500);

        character.move(30, 60);

        System.out.println("x: " + character.getPosition().x() + "; y: " + character.getPosition().y());



    }


}
