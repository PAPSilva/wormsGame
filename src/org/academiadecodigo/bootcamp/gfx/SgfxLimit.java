package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.level.Level;

public class SgfxLimit extends SgfxRectangularBody2D implements DeathGiver {

    public SgfxLimit(double width, double height, Vector2D position, SgfxViewport viewport) {

        super(-1,width,height,position,viewport);

    }


}
