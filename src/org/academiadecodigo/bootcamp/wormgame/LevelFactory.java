package org.academiadecodigo.bootcamp.wormgame;

/**
 * Created by codecadet on 09/06/2018.
 */
public class LevelFactory {

    private Level level;



    public  static Level getLevel(LevelType levelType) {

        return new Level();

    }

}
