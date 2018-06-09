
package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.wormgame.Character;
import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SgfxPicture extends Character implements Drawable {

    private Picture picture;
    private SgfxViewport viewport;
    private boolean active = false;
    // Constructor

    public SgfxPicture(double mass, double radius, double x, double y,int health, SgfxViewport viewport) {
        this(mass, radius, new Vector2D(x, y), health, viewport);
    }

    public SgfxPicture(double mass, double radius, Vector2D position, int health, SgfxViewport viewport) {
        super(mass, radius, position, health, 1);

        this.viewport = viewport;
        Vector2D topLeftCorner = new Vector2D(position);
        topLeftCorner.add(-radius, radius);
        Vector2D viewCoord = viewport.toViewportCoordinates(topLeftCorner);
        picture = new Picture(viewCoord.x(), viewCoord.y(), "soldier.png");
        //picture.grow(-90,-90);
        picture.draw();
    }

    // Behavior

    @Override
    public void updatePosition(double dt) {
        // TODO errors might accumulate from double to integer. Check if they do
        Vector2D oldCoord = viewport.toViewportCoordinates(getPosition());
        super.updatePosition(dt);
        Vector2D newCoord = viewport.toViewportCoordinates(getPosition());
        picture.translate(newCoord.x() - oldCoord.x(), newCoord.y() - oldCoord.y());

    }

    @Override
    public void draw() {
        picture.draw();
    }

    @Override
    public void delete() {
        picture.delete();
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Circle (" + getPosition() + ") " + "[" + picture.getX() + "," + picture.getY() + "]";
    }



        public boolean isActive() {
        return active;
    }

    public void toogleActive() {
        active = !active;
    }

}

