package org.academiadecodigo.bootcamp.physics2D.collidable;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public interface Collider {

    boolean checkCollision(Body2D body1, Body2D body2);

    Vector2D[] solveCollision(Body2D body1, Body2D body2, double dt);

}
