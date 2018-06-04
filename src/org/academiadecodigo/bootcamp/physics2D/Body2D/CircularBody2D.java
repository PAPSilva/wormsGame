package org.academiadecodigo.bootcamp.physics2D.Body2D;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public class CircularBody2D extends AbstractBody2D {

    private double radius;

    // Constructor

    public CircularBody2D(double mass, double radius, Vector2D position) {
        super(mass, position);
        this.radius = radius;
    }

    // Behavior


    // Getters and setters

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "[ " + radius + super.toString() + "]";
    }
}
