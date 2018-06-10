package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class SgfxViewport {

    private final int PADDING = 0;
    private Vector2D viewportOrigin;
    private Vector2D dimension;
    private int width;
    private int height;
    private double scale;

    // Constructor

    public SgfxViewport(int width, int height, double scale) {
        viewportOrigin = new Vector2D(0.0, (double) height);
        this.width = width;
        this.height = height;
        this.scale = scale;
        dimension = new Vector2D(((double) width) / scale, ((double) height) / scale);
    }

    // Behavior

    public void show() {
        Rectangle view = new Rectangle(PADDING, PADDING, width, height);
        view.draw();
    }

    /**
     * Rescales the viewport.
     * @param scale desired scale factor. Increased values is equivalent to zooming in, and vice-versa.
     */
    public void rescale(double scale) {
        updateDimension(scale / this.scale);
        this.scale = scale;
    }

    private void updateDimension(double scaleRatio) {
        double width = dimension.x() * scaleRatio;
        double height = dimension.y() * scaleRatio;
        dimension = new Vector2D(width, height);
    }

    public void translate(Vector2D displacement) {
        viewportOrigin.add(displacement);
    }

    // Getters and setters

    /**
     * @param coord system coordinates
     * @return the corresponding vector in viewport (pixel) coordinates
     */
    public Vector2D toViewportCoordinates(Vector2D coord) {
        Vector2D viewCoord = new Vector2D(0.0, 0.0);
        viewCoord.add(
                (coord.x() - viewportOrigin.x()) * scale,
                (viewportOrigin.y() - coord.y()) * scale
        );
        return viewCoord;
    }

    /**
     * @param viewCoord viewport coordinates
     * @return the corresponding vector in the system coordinates
     */
    public Vector2D fromViewportCoordinates(Vector2D viewCoord) {
        Vector2D coord = new Vector2D(
                viewCoord.x() / scale + viewportOrigin.x(),
                viewportOrigin.y() - (viewCoord.y() / scale)
        );
        return coord;
    }

}
