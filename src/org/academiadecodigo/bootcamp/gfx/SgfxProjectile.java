package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.Projectile;
import org.academiadecodigo.bootcamp.wormgame.ProjectileType;
import org.academiadecodigo.bootcamp.wormgame.sound.SoundFX;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SgfxProjectile extends Projectile implements Drawable {

    private SgfxViewport viewport;
    private ProjectileType projectileType;
    private Picture picture;



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
        picture = new Picture(viewCoord.x(),viewCoord.y(),"bullet.png");
        double growdx = picture.getWidth()*0.5 - radius;
        double growdy = picture.getHeight()*0.5 - radius;
        picture.grow(-growdx, -growdy);
        picture.translate(-growdx, -growdy);
        picture.draw();
    }

    // Behavior

    @Override
    public void updatePosition(double dt) {
        // TODO errors might accumulate from double to integer. Check if they do
        Vector2D oldCoord = viewport.toViewportCoordinates(getPosition());
        super.updatePosition(dt);
        Vector2D newCoord = viewport.toViewportCoordinates(getPosition());
        picture.translate(newCoord.x() - oldCoord.x(), newCoord.y() - oldCoord.y());
    }

    @Override
    public void draw() {
        picture.draw();
    }

    @Override
    public void delete() {
        picture.delete();
    }

    @Override
    public void remove() {
        delete();
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Projectile (" + getPosition() + ") " + "[" + picture.getX() + "," + picture.getY()+"]";
    }

    @Override
    public boolean isDead() {
        if (super.isDead()) {
            SoundFX.play("sounds/bazooka.wav");
            return true;
        }
        return false;
    }
}
