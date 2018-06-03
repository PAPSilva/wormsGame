package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public class TestBody2D extends CircularBody2D {

    public TestBody2D(double mass, Vector2D position) {
        super(mass, 1.0, position);
    }

    @Override
    public String toString() {
        Vector2D pos = getPosition();
        Vector2D vel = getVelocity();
        return "[TestBody2D (" + pos.x() + "," + pos.y() + ") : " + getOrientation() + " @ (" + vel.x() + "," + vel.y() + ")]";
    }

}
