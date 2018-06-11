package org.academiadecodigo.bootcamp.wormgame.actors;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 05/06/2018.
 */
public interface Fireable {

    Projectile fire(Vector2D position, double aim);

    WeaponType getWeaponType();

    boolean equals(Object o);

}
