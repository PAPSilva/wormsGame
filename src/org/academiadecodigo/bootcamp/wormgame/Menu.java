package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 09/06/2018.
 */
public class Menu {

    private String backgroundPath = "resources/menupic.png";

    private Picture background;

    private MenuKeyboard menuKeyboard;
    private boolean gameStart = false;

    public Menu() {

        background = new Picture();
        menuKeyboard = new MenuKeyboard(this);

    }


    public void initMenu() {

        background.load(backgroundPath);
        background.draw();
        menuKeyboard.initKeyboard();

        while (!gameStart) {}

    }


    public void startGame() {

        gameStart = true;
        background.delete();
        //init(1);
        //start();


    }


}
