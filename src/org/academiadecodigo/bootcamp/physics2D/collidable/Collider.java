package org.academiadecodigo.bootcamp.physics2D.collidable;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;

public interface Collider {

    boolean checkCollision(Body2D body1, Body2D body2);

    void solveCollision(Body2D body1, Body2D body2);

}
