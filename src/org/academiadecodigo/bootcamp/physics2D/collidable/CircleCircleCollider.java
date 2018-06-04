package org.academiadecodigo.bootcamp.physics2D.collidable;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;
import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public class CircleCircleCollider extends AbstractCollider {

    public CircleCircleCollider(double tiny) {
        super(tiny);
    }

    @Override
    public boolean checkCollision(Body2D body1, Body2D body2) {

        if(checkCircleCircleCollision(body1, body2)) {
            return true;
        }

        return false;
    }

    private boolean checkCircleCircleCollision(Body2D body1, Body2D body2) {

        // Check if both bodies are circles
        if(!(body1 instanceof CircularBody2D) || !(body2 instanceof CircularBody2D))) {
            return false;
        }

        CircularBody2D circBody1 = (CircularBody2D) body1;
        CircularBody2D circBody2 = (CircularBody2D) body2;

        // Distance between centers should be lower than their radius, if colliding
        Vector2D center1 = new Vector2D(circBody1.getPosition());
        Vector2D center2 = new Vector2D(circBody2.getPosition());
        center2.subtract(center1); // Difference vector is store in center2
        return center2.norm() <= circBody1.getRadius() + circBody2.getRadius();

    }

    @Override
    public void solveCollision(Body2D body1, Body2D body2) {

        // Solve the case of two circles colliding
        if((body1 instanceof CircularBody2D) && (body2 instanceof CircularBody2D)) {
            CircularBody2D circBody1 = (CircularBody2D) body1;
            CircularBody2D circBody2 = (CircularBody2D) body2;
            solveCircleCircleCollision(circBody1, circBody2);
            return;
        }

    }

    private boolean solveCircleCircleCollision(CircularBody2D body1, CircularBody2D body2) {

        // Solve estimated contact point and its normal
        // TODO apply restitution coefficient and friction
        double mass1 = body1.isMovable() ? body1.getMass() : 0.0;
        double mass2 = body2.isMovable() ? body2.getMass() : 0.0;
        Vector2D initialVelocity1 = new Vector2D(body1.getVelocity());
        Vector2D initialVelocity2 = new Vector2D(body2.getVelocity());

        // Calculate final velocities
        Vector2D finalVelocity1 = calculateCircularVelocity(mass1, mass2, initialVelocity1, initialVelocity2);
        body1.setVelocity(finalVelocity1);
        Vector2D finalVelocity2 = calculateCircularVelocity(mass2, mass1, initialVelocity2, initialVelocity1);
        body2.setVelocity(finalVelocity2);

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
}
