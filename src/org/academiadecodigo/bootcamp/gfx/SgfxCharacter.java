package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.Character;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 04/06/2018.
 */
public class SgfxCharacter extends Character {

    private Picture picture;
    private SgfxViewport viewport;
    private boolean active = false;


    // Constructor

    public SgfxCharacter(double mass, double radius, double x, double y, int health, int minDamage, SgfxViewport viewport) {
        this(mass, radius, new Vector2D(x, y), health, minDamage, viewport);
    }

    public SgfxCharacter(double mass, double radius, Vector2D position, int health, int minDamage, SgfxViewport viewport) {
        super(mass, radius, position, health, minDamage);
        this.viewport = viewport;
        Vector2D topLeftCorner = new Vector2D(position);
        topLeftCorner.add(-radius, radius);
        Vector2D viewCoord = viewport.toViewportCoordinates(topLeftCorner);
        picture = new Picture(viewCoord.x(),viewCoord.y(),"cowboys.png");
        double growdx = picture.getWidth()*0.5 - radius;
        double growdy = picture.getHeight()*0.5 - radius;
        System.out.println(picture.getWidth() + " " + growdx);
        picture.grow(-growdx, -growdy);
        picture.translate(-growdx, -growdy);
        picture.draw();
    }

    // Behavior

    @Override
    public void updatePosition(double dt) {
        // TODO errors might accumulate from double to integer. Check if they do
        Vector2D oldCoord = viewport.toViewportCoordinates(getPosition());
        super.updatePosition(dt);
        Vector2D newCoord = viewport.toViewportCoordinates(getPosition());
        //circle.translate(newCoord.x() - oldCoord.x(), newCoord.y() - oldCoord.y());
        picture.translate(newCoord.x() - oldCoord.x(), newCoord.y() - oldCoord.y());
    }


    public boolean isActive() {
        return active;
    }

    public void toogleActive() {
        active = !active;
    }

}
