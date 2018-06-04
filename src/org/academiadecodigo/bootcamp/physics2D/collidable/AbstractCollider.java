package org.academiadecodigo.bootcamp.physics2D.collidable;

public abstract class AbstractCollider implements Collider {

    private final double TINY;

    AbstractCollider(double tiny) {
        TINY = tiny;
    }

}
