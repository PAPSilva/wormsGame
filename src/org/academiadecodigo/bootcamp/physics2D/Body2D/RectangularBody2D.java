package org.academiadecodigo.bootcamp.physics2D.Body2D;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public class RectangularBody2D extends AbstractBody2D {

    private double halfWidth;
    private double halfHeight;
    private Vector2D tangent;
    private Vector2D normal;
    private Vector2D[] corners;

    // Constructor

    public RectangularBody2D(double mass, double width, double height, Vector2D position) {
        super(mass, position);
        halfWidth = width * 0.5;
        halfHeight = height * 0.5;
        tangent = new Vector2D(halfWidth, 0.0);
        normal = new Vector2D(0.0, halfHeight);
        corners = new Vector2D[4];
        updateCorners();
    }

    // Behavior


    @Override
    public void updatePosition(double dt) {
        super.updatePosition(dt);
        updateCorners();
    }

    @Override
    public void rotate(double angle) {
        super.rotate(angle);
        Vector2D newTangent = new Vector2D(halfWidth, 0.0);
        newTangent.rotate(getOrientation());
        tangent = newTangent;
        Vector2D newNormal = new Vector2D(0.0, halfHeight);
        newNormal.rotate(getOrientation());
        normal = newNormal;
        updateCorners();
    }

    private void updateCorners() {

        double x;
        double y;

        // Corner 1
        x = getPosition().x() + normal.x() - tangent.x();
        y = getPosition().y() + normal.y() - tangent.y();
        corners[0] = new Vector2D(x, y);

        // Corner 2
        x = getPosition().x() + normal.x() + tangent.x();
        y = getPosition().y() + normal.y() + tangent.y();
        corners[1] = new Vector2D(x, y);

        // Corner 3
        x = getPosition().x() + tangent.x() - normal.x();
        y = getPosition().y() + tangent.y() - normal.y();
        corners[2] = new Vector2D(x, y);

        // Corner 4
        x = getPosition().x() - normal.x() - tangent.x();
        y = getPosition().y() - normal.y() - tangent.y();
        corners[3] = new Vector2D(x, y);

    }

    // Getters ans setters

    public double getWidth() {
        return 2.0 * halfWidth;
    }

    public double getHeight() {
        return 2.0 * halfHeight;
    }

    /**
     * @return a copy of the corners.
     */
    public Vector2D[] getCorners() {
        Vector2D[] c = new Vector2D[4];
        for(int i=0; i < corners.length; i++) {
            c[i] = new Vector2D(corners[i]);
        }
        return c;
    }

    @Override
    public String toString() {
        return "[ " + getPosition() + " : " + getWidth() + "x" + getHeight() + " : " + getOrientation() + super.toString() + "]";
    }
}
