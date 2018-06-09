package org.academiadecodigo.bootcamp.wormgame.level;

import org.academiadecodigo.bootcamp.physics2D.Body2D.RectangularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.utils.DoubleReader;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public enum LevelType {
    LEVEL_RUIN("resources/levels/ruinsField.png",
               "resources/levels/ruinsField.obs",
               "resources/levels/ruinsField.spw",
               700),
    LEVEL_MOUNTAIN("resources/levels/mountainsField.png",
                   "resources/levels/mountainsField.obs",
                   "resources/levels/mountainsField.spw",
                   700);

    private String imagePath;
    private String obstaclesPath;
    private String spawnSitesPath;
    private int imageHeight;

    LevelType(String imagePath, String obstaclesPath, String spawnSitesPath, int imageHeight) {
        this.imagePath = imagePath;
        this.obstaclesPath = obstaclesPath;
        this.spawnSitesPath = spawnSitesPath;
        this.imageHeight = imageHeight;
    }

    public Picture image() {

        return new Picture(0, 0, imagePath);

    }

    public static LevelType random() {
        return values()[ (int) (Math.random() * values().length) ];
    }

    public List<RectangularBody2D> obstacles() {

        List<List<Vector2D>> vectors = DoubleReader.load(obstaclesPath);

        List<RectangularBody2D> obstacles = new ArrayList<>();

        Iterator<List<Vector2D>> it = vectors.iterator();
        Iterator<Vector2D> it2;
        while( it.hasNext() ) {

            it2 = it.next().iterator();
            if(!it2.hasNext()) {
                continue;
            }

            Vector2D current = it2.next();
            Vector2D next;
            while( it2.hasNext() ) {
                next = it2.next();
                obstacles.add( constructRectangle(current, next) );
                current = next;
            }

        }

        return obstacles;

    }

    private RectangularBody2D constructRectangle(Vector2D point1, Vector2D point2) {

        Vector2D center = new Vector2D(point2);
        center.subtract(point1);
        double width = center.norm();
        double angle = center.angle();
        center.divide(2.0);
        center.add(point1);

        RectangularBody2D rectangle = new RectangularBody2D(
                -1.0, width, 0.5, center);
        rectangle.rotate(-angle);
        rectangle.toggleMovable();

        return rectangle;

    }

    public List<Vector2D> spawnSites () {

        List<List<Vector2D>> vectors = DoubleReader.load(spawnSitesPath);

        List<Vector2D> sites = new ArrayList<>();

        Iterator<List<Vector2D>> it = vectors.iterator();
        Iterator<Vector2D> it2;
        Vector2D yInverter = new Vector2D(1.0, -1.0);
        Vector2D current;
        while( it.hasNext() ) {

            it2 = it.next().iterator();
            while( it2.hasNext() ) {
                current = it2.next();
                current.multiply(yInverter);
                current.add(0, imageHeight);
                sites.add(current);
            }

        }

        return sites;

    }

}
