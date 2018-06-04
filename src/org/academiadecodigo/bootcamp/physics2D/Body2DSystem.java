package org.academiadecodigo.bootcamp.physics2D;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.physics2D.collidable.Collider;

public class Body2DSystem implements PhysicSystem {

    private Body2D [] bodies;
    private int length = 0;
    private Vector2D gravityForce;
    private Collider collider;

    // Constructor

    public Body2DSystem(int n, Vector2D gravityForce, Collider collider) {
        n = n > 10 ? n : 10;
        bodies = new Body2D[n];
        this.gravityForce = gravityForce;
        this.collider = collider;
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
        length++;

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
    public void update(double t, double dt) {

        for(int i=0; i < (int) Math.ceil( t / dt); i++) {
            update(dt);
        }

    }

    private void update(double dt) {

         // Check forces interacting with each body
        Vector2D[] force = new Vector2D[length];
        // TODO Check for torque here
        for(int i=0; i < length; i++) {
            force[i] = interactingForces(bodies[i], dt);
            if(force[i] == null) {
                System.out.println("Force is null");
            } else {
                System.out.println("Force " + force[i]);
            }
        }

        // Apply force to the objects
        // TODO Apply torque to the objects
        for(int i=0; i < length; i++) {
            bodies[i].applyForce(force[i], dt);
            bodies[i].updatePosition(dt);
            System.out.println("updating position");
        }

    }

    private Vector2D interactingForces(Body2D body, double dt) {

        Vector2D force = new Vector2D(0.0, 0.0);

        // Unmovable objects are dealt with within the collider

        // Collisions
        // TODO this is calculating some objects two times... improve it
        for(Body2D otherBody : bodies) {

            // Discard self
            if(body.equals(otherBody)) {
                continue;
            }

            // Check if bodies are colliding
            if(!collider.checkCollision(body, otherBody)) {
                continue;
            }
            System.out.println("Colliding");

            // Get force for object
            Vector2D[] impulses = collider.solveCollision(body, otherBody, dt);
            System.out.println("Impulse " + impulses[0]);

            force.add(impulses[0]);

            break;

        }

        // Apply gravity
        if(body.isGravitable()) {
            force.add(gravityForce);
        }

        return force;

    }

    // Getters and setters

    public int getLength() {
        return length;
    }
}
