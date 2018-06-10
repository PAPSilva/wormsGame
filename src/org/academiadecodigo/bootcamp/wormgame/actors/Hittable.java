package org.academiadecodigo.bootcamp.wormgame.actors;

/**
 * Created by codecadet on 03/06/2018.
 */
public interface Hittable {

    boolean suffer(int sufferDamage);

    boolean isDead();

}
