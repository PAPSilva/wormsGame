package org.academiadecodigo.bootcamp.wormgame.actors;

import org.academiadecodigo.bootcamp.wormgame.actors.PainGiver;

/**
 * Created by codecadet on 06/06/2018.
 */
public interface Shooter {

    void changeWeapon(Fireable fireable);

    Fireable getWeapon();

    PainGiver fire();

    void changeAim(double angle);

    double getAim();

    void  turnAim();

}
