package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.Projectile;
import org.academiadecodigo.bootcamp.wormgame.ProjectileType;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

/**
 * Created by codecadet on 04/06/2018.
 */
public class SgfxProjectile extends Projectile {

    private Ellipse circle;
    private SgfxViewport viewport;
    private ProjectileType projectileType;



    public SgfxProjectile(ProjectileType projectileType, double x, double y, SgfxViewport viewport) {
        this(projectileType, new Vector2D(x, y), viewport);
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
        System.out.println(viewCoord);
    }


}
