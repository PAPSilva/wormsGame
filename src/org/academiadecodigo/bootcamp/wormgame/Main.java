package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.wormgame.logic.Game;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();

        game.openMenu();

        game.start();

        System.out.println("Game Over");

    }

}
