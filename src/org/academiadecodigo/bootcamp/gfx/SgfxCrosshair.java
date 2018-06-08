package org.academiadecodigo.bootcamp.gfx;

import javafx.scene.shape.Ellipse;
import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 06/06/2018.
 */
public class SgfxCrosshair extends CircularBody2D {

    private Vector2D position;
    private Picture picture;


    public SgfxCrosshair(double aim, Vector2D playerPosition) {

        super(0, 1, playerPosition);
        this.position = new Vector2D(playerPosition);



    }



    public void changePosition(double aim, Vector2D playerPosition) {

    }

}
