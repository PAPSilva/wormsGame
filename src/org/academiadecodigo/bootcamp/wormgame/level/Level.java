package org.academiadecodigo.bootcamp.wormgame.level;

import org.academiadecodigo.bootcamp.physics2D.Body2D.RectangularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.List;

/**
 * Created by codecadet on 09/06/2018.
 */
public class Level {

    private Picture picture;
    private List<RectangularBody2D> obstacles;
    private List<Vector2D> spawns;


    public Level(Picture picture, List<RectangularBody2D> obstacles, List<Vector2D> spawns) {

        this.picture = picture;
        this.obstacles = obstacles;
        this.spawns = spawns;

    }


    public Picture getPicture() {
        return picture;
    }

    public List<RectangularBody2D> getObstacles() {
        return obstacles;
    }

    public List<Vector2D> getSpawns() {
        return spawns;
    }

}
