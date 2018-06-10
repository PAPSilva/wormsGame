/**
 * @author Pedro Angelo Silva
 */
package org.academiadecodigo.bootcamp.physics2D.utils;

import org.academiadecodigo.bootcamp.utils.Vector;

/**
 * This class assumes 2D Euclidean coordinate system.
 */
public class Vector2D extends Vector {

    // Constructor

    public Vector2D(double x, double y) {
        super(2);
        set(x, 0);
        set(y, 1);
    }

    public Vector2D(Vector2D u) {
        this(u.x(), u.y());
    }

    // Behavior

    public void add(double dx, double dy) {
        set(x() + dx, 0);
        set(y() + dy, 1);
    }

    /**
     * Rotate this vector by angle.
     * @param angle radians
     */
    public void rotate(double angle) {
        double newX = x() * Math.cos(angle) - y() * Math.sin(angle);
        double newY = y() * Math.cos(angle) + x() * Math.sin(angle);
        set(newX, 0);
        set(newY, 1);
    }

    // Getters and setters

    public double x() {
        return this.get(0);
    }

    public double y() {
        return this.get(1);
    }

    /**
     * @return angle this vector makes with y=0
     */
    public double angle() {
        return Math.atan2(y(), x());
    }

    /**
     * @param u
     * @return angle this vector makes with #u
     */
    public double angle(Vector2D u) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "(" + x() + "," + y() + ")";
    }
}
