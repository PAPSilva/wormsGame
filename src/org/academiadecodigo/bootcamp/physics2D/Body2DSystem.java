package org.academiadecodigo.bootcamp.physics2D;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.physics2D.collidable.Collider;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Body2DSystem implements PhysicSystem {

    private List<Body2D> bodies = new CopyOnWriteArrayList<>();
    private Vector2D gravityAcceleration;
    private Collider collider;

    // Constructor

    public Body2DSystem(Vector2D gravityAcceleration, Collider collider) {

        this.gravityAcceleration = gravityAcceleration;
        this.collider = collider;

    }

    // Behavior

    public void add(Body2D body) {
       bodies.add(body);
    }

    public void remove(Body2D body) {
        body.remove();
        bodies.remove(body);
    }

    @Override
    public void update(double t, double dt) {

        for(int i=0; i < (int) Math.ceil( t / dt); i++) {
            update(dt);
        }

    }

    private void update(double dt) {

        // Check forces interacting with each body
        int length = bodies.size();
        Vector2D[] force = new Vector2D[length];
        for(int i=0; i < length; i++) {
            force[i] = new Vector2D(0.0, 0.0);
        }
        // TODO Check for torque here
        for(int i=0; i < length; i++) {
            force[i].add(interactingForces(bodies.get(i), dt));
        }

        // Apply force to the objects
        // TODO Apply torque to the objects
        for(int i=0; i < length; i++) {
            System.out.println("Vi = " + bodies.get(i).getVelocity());
            System.out.println("F  = " + force[i]);
            bodies.get(i).applyForce(force[i], dt);
            System.out.println("Vf = " + bodies.get(i).getVelocity());
            bodies.get(i).updatePosition(dt);
            System.out.println("P  = " + bodies.get(i).getPosition());
        }

    }

    private Vector2D interactingForces(Body2D body, double dt) {

        Vector2D force = new Vector2D(0.0, 0.0);

        // Unmovable objects are dealt with within the collider

        // Collisions
        // TODO this is calculating some object pairs two times... improve it
        int length = bodies.size();
        for(int i=0; i < length; i++) {

            // Discard self
            if(body.equals(bodies.get(i))) {
                continue;
            }

            // Check if bodies are colliding
            if(!collider.checkCollision(body, bodies.get(i))) {
                continue;
            }

            // Get force for object
            Vector2D impulses = collider.solveCollision(body, bodies.get(i), dt);
            System.out.println("I = " + impulses);
            force.add(impulses);

        }

        // Apply gravity
        if(body.isGravitable()) {
            Vector2D graviticForce = new Vector2D(gravityAcceleration);
            graviticForce.multiply(body.getMass());
            force.add(graviticForce);
        }

        return force;

    }

    // Getters and setters

    @Override
    public Iterator<Body2D> iterator() {
        return bodies.iterator();
    }

}
