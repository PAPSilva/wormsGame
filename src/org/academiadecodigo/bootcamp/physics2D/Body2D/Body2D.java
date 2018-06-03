package org.academiadecodigo.bootcamp.physics2D.Body2D;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public interface Body2D {

    void applyForces(Vector2D[] forces, double dt);

    /**
     * Sets the object velocity.
     * @param velocity
     * @return the impulse necessary fo put the object at #velocity
     */
    Vector2D setVelocity(Vector2D velocity);

    /**
     * Change the momentum by providing the impulse acting upon the body.
     * @param impulse
     */
    void changeMomentum(Vector2D impulse);

    void updatePosition(double dt);

    Vector2D getPosition();

    double getOrientation();

    double getMass();

    Vector2D getVelocity();

    boolean isMovable();

}
