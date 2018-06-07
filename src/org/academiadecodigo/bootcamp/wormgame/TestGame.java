package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.TestGameV2;

/**
 * Created by codecadet on 05/06/2018.
 */
public class TestGame {

    public static void main(String[] args) {

        TestGameV2 game = new TestGameV2();

        game.init(1);
        game.start();



        System.out.println("GAMEOVER");
    }

}
