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
 @Override
    public boolean checkCollision(Body2D body) {

        if(checkCircleCircleCollision(body)) {
            return true;
        }

        return false;
    }

    private boolean checkCircleCircleCollision(Body2D body) {

        // Check if both bodies are circles
        if(!(body instanceof CircularBody2D)) {
            return false;
        }

        CircularBody2D circBody = (CircularBody2D) body;

        // Distance between centers should be lower than their radius, if colliding
        Vector2D center1 = new Vector2D(getPosition());
        Vector2D center2 = new Vector2D(getPosition());
        center2.subtract(center1); // Difference vector is store in center2
        return center2.norm() < getRadius() + circBody.getRadius();

    }

    @Override
    public void solveCollision(Body2D body) {

        // Solve the case of two circles colliding
        if(solveCircleCircleCollision(body)) {
            return;
        }

    }

    private boolean solveCircleCircleCollision(Body2D body) {

        // TODO it is probable we'll do two shape checks between this and the collision check.
        // Check if both bodies are circles
        if(!(body instanceof CircularBody2D)) {
            return false;
        }

        // Solve estimated contact point and its normal
        // TODO apply restitution coefficient here
        double mass1 = isMovable() ? getMass() : 0.0;
        double mass2 = body.isMovable() ? body.getMass() : 0.0;
        Vector2D initialVelocity1 = new Vector2D(getVelocity());
        Vector2D initialVelocity2 = new Vector2D(body.getVelocity());

        // Calculate final velocities
        Vector2D finalVelocity1 = calculateCircularVelocity(mass1, mass2, initialVelocity1, initialVelocity2);
        changeMomentum(finalVelocity1);
        Vector2D finalVelocity2 = calculateCircularVelocity(mass2, mass1, initialVelocity2, initialVelocity1);
        body.changeMomentum(finalVelocity2);

        return true;
    }

    private Vector2D calculateCircularVelocity(double mass1, double mass2, Vector2D velocity1, Vector2D velocity2) {

        // New velocity according to conservation of momentum
        // TODO do inelastic collision
        Vector2D finalVelocity = new Vector2D(velocity1);
        finalVelocity.multiply(mass1 - mass2);
        Vector2D temp = new Vector2D(velocity2);
        temp.multiply(2.0 * mass2);
        finalVelocity.add(temp);
        finalVelocity.multiply( 1.0 / (mass1 + mass2));

        return finalVelocity;


    }

    // Getters and setters

    public double getRadius() {
        return radius;
    }
}
