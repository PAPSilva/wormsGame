package org.academiadecodigo.bootcamp.physics2D;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;

public interface System {

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
     * Runs the system for a time interval
     * @param dt time interval
     */
    void update(double dt);

}
