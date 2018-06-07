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
        Vector2D topLeftCorner = topLeftCorner();
        circle = new Ellipse( topLeftCorner.x(), topLeftCorner.y(), 2.0 * radius, 2.0 *  radius);
        draw();
    }

    // Behavior

    @Override
    public void updatePosition(double dt) {
        super.updatePosition(dt);
        updateShape();
    }

    @Override
    public void translate(Vector2D displacement) {
        super.translate(displacement);
        updateShape();
    }

    private void updateShape() {
        Vector2D oldCoord = new Vector2D(circle.getX(), circle.getY());
        Vector2D newCoord = topLeftCorner();
        circle.translate(newCoord.x() - oldCoord.x(), newCoord.y() - oldCoord.y());
    }

    private Vector2D topLeftCorner() {

        Vector2D topLeftCorner = getPosition();
        topLeftCorner.add(-getRadius(), getRadius());
        Vector2D viewCoord = viewport.toViewportCoordinates(topLeftCorner);
        return viewCoord;
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
