package org.academiadecodigo.bootcamp.physics2D;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public abstract class AbstractSystem implements System {

    private Body2D [] bodies;
    private int length = 0;
    private Vector2D gravityForce;

    // Constructor

    public AbstractSystem(int n, Vector2D gravityForce) {
        n = n > 10 ? n : 10;
        bodies = new Body2D[n];
        this.gravityForce = gravityForce;
    }

    // Behavior

    @Override
    public void add(Body2D body) {

        // Check if array is full and increase it if so
        if(length == bodies.length) {
            Body2D[] newBodies = new Body2D[2*length];
            for(int i=0; i < length; i++) {
                newBodies[i] = bodies[i];
            }
        }

        // Add the new body to the system
        bodies[length] = body;

    }

    @Override
    public void remove(Body2D body) {

        for(int i=0; i < length; i++) {

            if(bodies[i].equals(body)) {
                bodies[i] = bodies[length];
                bodies[length] = null;
                length--;
                return;
            }

        }

    }

    @Override
    public void update(double dt) {

        // Check forces interacting with each body
        // TODO this can break for a huge amount of objects
        Vector2D[][] forces = new Vector2D[1][length];
        for(int i=0; i < length; i++) {
            forces[i] = interactingForces(bodies[i]);
        }

        // Apply forces to the objects
        for(int i=0; i < length; i++) {
            bodies[i].applyForces(forces[i], dt);
        }

    }

    private Vector2D[] interactingForces(Body2D body) {

        Vector2D[] forces = new Vector2D[1];
        forces[0] = new Vector2D(0.0, 0.0);

        // Check if body is movable
        if(!body.isMovable()) {
            return forces;
        }

        // TODO Check and resolve collisions

        // Apply gravity
        forces[0].add(gravityForce);

        return forces;

    }

    // Getters and setters

    public int getLength() {
        return length;
    }
}
