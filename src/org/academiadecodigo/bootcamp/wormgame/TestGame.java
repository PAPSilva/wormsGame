package org.academiadecodigo.bootcamp.wormgame;


/**
 * Created by codecadet on 05/06/2018.
 */
public class TestGame {

    public static void main(String[] args) {



        Game game = new Game();
        game.openMenu();

        //game.init(1);
        game.start();
        //game.openMenu();


        System.out.println("Game Over");

    }

}
