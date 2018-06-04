package org.academiadecodigo.bootcamp.physics2D.Body2D;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public abstract class AbstractBody2D implements Body2D {

    private Vector2D position;
    private double orientation;
    private double mass;
    private boolean movable;
    private Vector2D velocity;
    private boolean gravitable;
    private double restitution;
    private double staticFrictionCoeff;
    private double kineticFrictionCoeff;

    // Constructor

    /**
     * A body is created at position (0,0) with orientation 0 rad and velocity (0,0).
     * Mass should be negative for unmovable objects.
     * @param mass
     */
    public AbstractBody2D(double mass, Vector2D position) {
        this.position = position;
        orientation = 0.0;
        this.mass = mass;
        velocity = new Vector2D(0.0, 0.0);
        gravitable = true;
        movable = true;
    }

    // Behavior

    public void applyForce(Vector2D force, double dt) {

        // Force effect
        Vector2D acceleration = new Vector2D(force.x(), force.y());
        acceleration.multiply(1.0 / mass * dt);
        velocity.add(acceleration.x(), acceleration.y());

        // TODO Torque effect

    }

    public void applyForces(Vector2D[] forces, double dt) {

        // Apply each force
        for(Vector2D force : forces) {
            applyForce(force, dt);
        }

    }

    @Override
    public Vector2D setVelocity(Vector2D newVelocity) {

        // Calculate impulse
        Vector2D impulse = getImpulse(newVelocity);
        velocity = newVelocity;
        return impulse;
    }

    @Override
    public Vector2D getImpulse(Vector2D newVelocity) {
        // Calculate impulse
        Vector2D impulse = new Vector2D(
                mass * (newVelocity.x() - velocity.x()),
                mass * (newVelocity.y() - velocity.y())
        );
        return impulse;
    }

    @Override
    public void changeMomentum(Vector2D impulse) {
        impulse.multiply( 1.0 / mass);
        velocity.add(impulse);
        // TODO ensure this is here
        applyForce(new Vector2D(0.0, 0.0), 1.0);
    }

    @Override
    public void updatePosition(double dt) {
        // TODO update orientation
        position.add(velocity.x() * dt, velocity.y() * dt);
    }

    // Getters and setters

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public double getOrientation() {
        return orientation;
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public Vector2D getVelocity() {
        return velocity;
    }

    @Override
    public boolean isMovable() {
        return movable;
    }

    @Override
    public void toggleMovable() {
        movable = !movable;
    }

    @Override
    public boolean isGravitable() {
        return gravitable;
    }

    public void toggleGravitable() {
        gravitable = !gravitable;
    }

    public Vector2D momentum() {
        Vector2D momentum = new Vector2D(velocity.x(), velocity.y());
        momentum.multiply(mass);
        return momentum;
    }

    @Override
    public String toString() {
        return "[" + " " + position + " " + orientation + " @ " + velocity + "]";
    }
}
