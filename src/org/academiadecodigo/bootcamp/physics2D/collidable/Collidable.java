package org.academiadecodigo.bootcamp.physics2D.collidable;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;

public interface Collidable {

    /**
     * Checks if two bodies collide with each other.
     * @param body
     * @return true if they collide
     */
    boolean checkCollision(Body2D body);

    /**
     * Solves the collisions and forces acting between two objects.
     * @param body
     */
    void solveCollision(Body2D body);

}
