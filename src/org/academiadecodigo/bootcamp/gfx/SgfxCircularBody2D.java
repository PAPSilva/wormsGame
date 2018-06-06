package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

public class SgfxCircularBody2D extends CircularBody2D implements Drawable {

    private Ellipse circle;
    private SgfxViewport viewport;

    // Constructor

    public SgfxCircularBody2D(double mass, double radius, double x, double y, SgfxViewport viewport) {
        this(mass, radius, new Vector2D(x, y), viewport);
    }

    public SgfxCircularBody2D(double mass, double radius, Vector2D position, SgfxViewport viewport) {
        super(mass, radius, position);
        this.viewport = viewport;
        Vector2D topLeftCorner = new Vector2D(position);
        topLeftCorner.add(-radius, radius);
        Vector2D viewCoord = viewport.toViewportCoordinates(topLeftCorner);
        circle = new Ellipse( viewCoord.x(), viewCoord.y(), 2.0 * radius, 2.0 *  radius);
        draw();
    }

    // Behavior

    @Override
    public void updatePosition(double dt) {
        // TODO errors might accumulate from double to integer. Check if they do
        Vector2D oldCoord = viewport.toViewportCoordinates(getPosition());
        super.updatePosition(dt);
        Vector2D newCoord = viewport.toViewportCoordinates(getPosition());
        circle.translate(newCoord.x() - oldCoord.x(), newCoord.y() - oldCoord.y());
    }

    @Override
    public void draw() {
        circle.draw();
    }

    @Override
    public void delete() {
        circle.delete();
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Circle (" + getPosition() + ") " + "[" + circle.getX() + "," + circle.getY()+"]";
    }

}
