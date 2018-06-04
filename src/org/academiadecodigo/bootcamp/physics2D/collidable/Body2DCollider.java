package org.academiadecodigo.bootcamp.physics2D.collidable;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;
import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public class Body2DCollider implements Collider {

    private final double TINY;
    private final double INFINIY = 1.0e20;

    public Body2DCollider(double tiny) {
        TINY = tiny;
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
        if(!(body1 instanceof CircularBody2D) || !(body2 instanceof CircularBody2D)) {
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
    public Vector2D[] solveCollision(Body2D body1, Body2D body2, double dt) {

        // Solve the case of two circles colliding
        if((body1 instanceof CircularBody2D) && (body2 instanceof CircularBody2D)) {
            CircularBody2D circBody1 = (CircularBody2D) body1;
            CircularBody2D circBody2 = (CircularBody2D) body2;
            Vector2D[] forces = solveCircleCircleCollision(circBody1, circBody2, dt);
            return forces;
        }

        // No collisions
        return null;

    }

    private Vector2D[] solveCircleCircleCollision(CircularBody2D body1, CircularBody2D body2, double dt) {

        // Solve estimated contact point and its normal
        // TODO apply restitution coefficient and friction
        double mass1 = body1.isMovable() ? body1.getMass() : INFINIY;
        double mass2 = body2.isMovable() ? body2.getMass() : INFINIY;
        System.out.println(mass1 + " :: " + mass2);
        Vector2D initialVelocity1 = new Vector2D(body1.getVelocity());
        Vector2D initialVelocity2 = new Vector2D(body2.getVelocity());
        System.out.println(initialVelocity1 + " :: " + initialVelocity2);

        // Calculate final velocities and corresponding impulses
        Vector2D finalVelocity1 = calculateCircularVelocity(
                mass1, mass2, initialVelocity1, initialVelocity2);
        Vector2D impulse1 = body1.getImpulse(finalVelocity1);

        Vector2D finalVelocity2 = calculateCircularVelocity(
                mass2, mass1, initialVelocity2, initialVelocity1);
        Vector2D impulse2 = body2.getImpulse(finalVelocity2);

        // Return the impulses
        impulse1.divide(dt);
        impulse2.divide(dt);
        Vector2D[] impulses = {impulse1, impulse2};
        System.out.println("solveCollision " + finalVelocity1 + finalVelocity2 + impulses[0]);

        return impulses;

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
