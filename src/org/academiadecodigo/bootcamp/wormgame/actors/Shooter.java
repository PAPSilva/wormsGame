package org.academiadecodigo.bootcamp.wormgame.actors;

import org.academiadecodigo.bootcamp.wormgame.actors.PainGiver;

/**
 * Created by codecadet on 06/06/2018.
 */
public interface Shooter {

    PainGiver fire();

    double getAim();

    void  turnAim();

}
