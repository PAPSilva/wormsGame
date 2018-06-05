package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.Body2D.RectangularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.simplegraphics.graphics.Line;

public class SgfxRectangularBody2D extends RectangularBody2D implements Drawable {

    private Line[] rectangle;
    private Vector2D[] corners;
    private SgfxViewport viewport;

    // Constructor

    public SgfxRectangularBody2D(double mass, double width, double height, Vector2D position, SgfxViewport viewport) {
        super(mass, width, height, position);
        this.viewport = viewport;
        rectangle = new Line[4];
        corners = new Vector2D[4];
        draw();
    }

    // Behavior

    private void updateRectangle() {

        Vector2D[] corners = getCorners();

        // Delete line
        // TODO check it it moved or rotated before deleting.
        //delete();

        // Calculate new points
        for (int i = 0; i < corners.length; i++) {
            this.corners[i] = viewport.toViewportCoordinates(corners[i]);
        }
        rectangle[0] = new Line(
                this.corners[0].x(), this.corners[0].y(),
                this.corners[1].x(), this.corners[1].y()
        );
        rectangle[1] = new Line(
                this.corners[1].x(), this.corners[1].y(),
                this.corners[2].x(), this.corners[2].y()
        );
        rectangle[2] = new Line(
                this.corners[2].x(), this.corners[2].y(),
                this.corners[3].x(), this.corners[3].y()
        );
        rectangle[3] = new Line(
                this.corners[3].x(), this.corners[3].y(),
                this.corners[0].x(), this.corners[0].y()
        );

    }

    @Override
    public void updatePosition(double dt) {
        Vector2D oldCoord = viewport.toViewportCoordinates(getPosition());
        super.updatePosition(dt);
        Vector2D newCoord = viewport.toViewportCoordinates(getPosition());
        for (int i = 0; i < corners.length; i++) {
            rectangle[i].translate(newCoord.x() - oldCoord.x(), newCoord.y() - oldCoord.y());
        }
    }

    @Override
    public void rotate(double angle) {
        // TODO finish
        super.rotate(angle);
    }

    @Override
    public void draw() {
        updateRectangle();
        for(int i=0; i < rectangle.length; i++) {
            rectangle[i].draw();
        }
    }

    @Override
    public void delete() {
        // TODO handle null pointers exceptions
        for(int i=0; i < rectangle.length; i++) {
            if(rectangle[i] != null) {
                rectangle[i].delete();
            }
        }
    }
}
