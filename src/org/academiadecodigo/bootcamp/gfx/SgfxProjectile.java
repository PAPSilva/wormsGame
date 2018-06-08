package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.Projectile;
import org.academiadecodigo.bootcamp.wormgame.ProjectileType;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

/**
 * Created by codecadet on 04/06/2018.
 */
public class SgfxProjectile extends Projectile implements Drawable {

    private Ellipse circle;
    private SgfxViewport viewport;
    private ProjectileType projectileType;


    public SgfxProjectile(Projectile projectile, SgfxViewport viewport) {
        this(projectile.getProjectileType(), projectile.getPosition(), viewport);
    }

    public SgfxProjectile(ProjectileType projectileType, Vector2D position, SgfxViewport viewport) {
        super(projectileType, position);
        this.viewport = viewport;
        Vector2D topLeftCorner = new Vector2D(position);
        double radius = projectileType.getRadius();
        topLeftCorner.add(-radius, radius);
        Vector2D viewCoord = viewport.toViewportCoordinates(topLeftCorner);
        circle = new Ellipse( viewCoord.x(), viewCoord.y(), 2.0 * radius, 2.0 *  radius);
        circle.draw();
    }

    // Behavior

    @Override
    public void updatePosition(double dt) {
        // TODO errors might accumulate from double to integer. Check if they do
        Vector2D oldCoord = viewport.toViewportCoordinates(getPosition());
        super.updatePosition(dt);
        Vector2D newCoord = viewport.toViewportCoordinates(getPosition());
        circle.translate(newCoord.x() - oldCoord.x(), newCoord.y() - oldCoord.y());
    }

    @Override
    public void draw() {
        circle.draw();
    }

    @Override
    public void delete() {
        circle.delete();
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Projectile (" + getPosition() + ") " + "[" + circle.getX() + "," + circle.getY()+"]";
    }
}
