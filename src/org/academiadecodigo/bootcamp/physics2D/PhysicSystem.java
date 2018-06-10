package org.academiadecodigo.bootcamp.physics2D;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;

public interface PhysicSystem extends Iterable<Body2D> {

    /**
     * Adds a body to the system.
     * @param body
     */
    void add(Body2D body);

    /**
     * Removes a body from the system.
     * @param body
     */
    void remove(Body2D body);

    /**
     * Advance the system for a simulation time t in time intervals dt.
     * @param t time of simulation
     * @param dt time steps of simulation
     */
    void update(double t, double dt);

}
