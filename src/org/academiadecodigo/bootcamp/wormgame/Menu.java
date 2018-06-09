package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 09/06/2018.
 */
public class Menu {

    private Picture picture;
    private Picture title;
    private String picturePath = "";
    private String titlePath = "";


    public Menu() {

        picture = new Picture();
        title = new Picture();

    }


    public void init() {

        picture.load(picturePath);
        title.load(titlePath);

    }

}
