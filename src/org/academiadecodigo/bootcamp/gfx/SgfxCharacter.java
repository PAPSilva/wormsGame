package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.actors.Character;
import org.academiadecodigo.bootcamp.wormgame.sound.SoundFX;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SgfxCharacter extends Character implements Drawable {

    private Picture picture;
    private SgfxViewport viewport;
    private boolean active = false;
    private Picture aim = new Picture(0.0,0.0,"resources/crosshair.png");
    private final double MUZZLE = 40.0;

    // Constructor

    public SgfxCharacter(double mass, double radius, Vector2D position, int health, int minDamage, String imagePath, SgfxViewport viewport) {

        super(mass, radius, position, health, minDamage);
        this.viewport = viewport;

        // Set skin with proper size and at proper location.
        Vector2D topLeftCorner = new Vector2D(position);
        topLeftCorner.add(-radius, radius);
        Vector2D viewCoord = viewport.toViewportCoordinates(topLeftCorner);

        picture = new Picture(viewCoord.x(),viewCoord.y(), imagePath);
        double growdx = picture.getWidth()*0.5 - radius;
        double growdy = picture.getHeight()*0.5 - radius;
        picture.grow(-growdx, -growdy);
        picture.translate(-growdx, -growdy);

        picture.draw();
        updateAim();

    }

    // Behavior

    @Override
    public void updatePosition(double dt) {

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
        aim.translate( (int) viewCoord.x(), (int) viewCoord.y());

    }

    private void updateShape() {

        Vector2D topLeftCorner = new Vector2D(getPosition());
        topLeftCorner.add(-getRadius(), getRadius());
        Vector2D viewCoord = viewport.toViewportCoordinates(topLeftCorner);
        viewCoord.subtract(new Vector2D(picture.getX(), picture.getY()));
        picture.translate((int) viewCoord.x(), (int) viewCoord.y());

    }

    public boolean isActive() {
        return active;
    }

    public void toggleActive() {

        active = !active;

        if(active) {
            aim.draw();
        } else {
            aim.delete();
        }

    }

    @Override
    public void turnAim() {
        super.turnAim();
        picture.flipHorizontal();
        picture.draw();
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
    public boolean suffer(int sufferDamage){

        // Character is dead
        if(health() <= 0) {
            return false;
        }

        // Damage
        if(super.suffer(sufferDamage)){

            SoundFX.playOnce("sounds/cry.wav");

            // Check if it died after suffering damage
            if(health() <= 0) {
                setDeathPic();
            }

            return true;
        }

        return false;

    }

    @Override
    public void remove() {}


    private void setDeathPic() {

        picture.delete();
        picture.load("resources/skull.png");
        picture.draw();

    }


}
