package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.Character;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 04/06/2018.
 */
public class SgfxCharacter extends Character implements Drawable{

    private Picture picture;
    private SgfxViewport viewport;
    private boolean active = false;
    private Picture aim = new Picture(0.0,0.0,"crosshair.png");
    private final double MUZZLE = 40.0;

    // Constructor

    public SgfxCharacter(double mass, double radius, double x, double y, int health, int minDamage, SgfxViewport viewport) {
        this(mass, radius, new Vector2D(x, y), health, minDamage, viewport);
    }

    public SgfxCharacter(double mass, double radius, Vector2D position, int health, int minDamage, SgfxViewport viewport) {
        super(mass, radius, position, health, minDamage);
        this.viewport = viewport;

        Vector2D topLeftCorner = new Vector2D(position);
        topLeftCorner.add(-radius, radius);
        Vector2D viewCoord = viewport.toViewportCoordinates(topLeftCorner);
        picture = new Picture(viewCoord.x(),viewCoord.y(),"soldado.png");
        double growdx = picture.getWidth()*0.5 - radius;
        double growdy = picture.getHeight()*0.5 - radius;
        System.out.println(picture.getWidth() + " " + growdx);
        picture.grow(-growdx, -growdy);
        picture.translate(-growdx, -growdy);
        picture.draw();
        updateAim();
    }

    // Behavior

    @Override
    public void updatePosition(double dt) {
        // TODO errors might accumulate from double to integer. Check if they do
        super.updatePosition(dt);
        updateShape();
        updateAim();
    }

    private void updateAim() {

        if(!active) {
            return;
        }

        Vector2D muzzle = new Vector2D(1.0, 0.0);
        muzzle.rotate(getAim());
        muzzle.multiply(MUZZLE);
        muzzle.add(getPosition());

        Vector2D viewCoord = viewport.toViewportCoordinates(muzzle);
        viewCoord.subtract(new Vector2D(aim.getX(), aim.getY()));
        aim.translate(viewCoord.x(), viewCoord.y());

    }

    private void updateShape() {

        Vector2D topLeftCorner = new Vector2D(getPosition());
        topLeftCorner.add(-getRadius(), getRadius());
        Vector2D viewCoord = viewport.toViewportCoordinates(topLeftCorner);
        viewCoord.subtract(new Vector2D(picture.getX(), picture.getY()));
        picture.translate(viewCoord.x(), viewCoord.y());

    }


    public boolean isActive() {
        return active;
    }

    public void toogleActive() {

        active = !active;

        if(active) {
            aim.draw();
        } else {
            aim.delete();
        }

    }

    @Override
    public void draw() {

    }

    @Override
    public void delete() {

    }
}
