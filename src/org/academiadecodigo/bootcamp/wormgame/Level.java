package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

/**
 * Created by codecadet on 09/06/2018.
 */
public class Level {

    private Picture picture;
    //private LevelType levelType;
    private LinkedList rectangles;
    private LinkedList spawns;


    public Level(Picture picture, LinkedList rectangles, LinkedList spawns) {

        this.picture = picture;
        this.rectangles = rectangles;
        this.spawns = spawns;

    }

}
