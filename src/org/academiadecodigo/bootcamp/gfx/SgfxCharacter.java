package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.Character;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;


/**
 * Created by codecadet on 04/06/2018.
 */
public class SgfxCharacter extends Character {

    private Ellipse circle;
    private SgfxViewport viewport;
    private boolean active = false;


    // Constructor

    public SgfxCharacter(double mass, double radius, double x, double y, int health, SgfxViewport viewport) {
        this(mass, radius, new Vector2D(x, y), health, viewport);
    }

    public SgfxCharacter(double mass, double radius, Vector2D position, int health, SgfxViewport viewport) {
        super(mass, radius, position, health);
        this.viewport = viewport;
        Vector2D topLeftCorner = new Vector2D(position);
        topLeftCorner.add(-radius, radius);
        Vector2D viewCoord = viewport.toViewportCoordinates(topLeftCorner);
        circle = new Ellipse( viewCoord.x(), viewCoord.y(), 2.0 * radius, 2.0 *  radius);
        circle.draw();
        System.out.println(viewCoord);
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


    public boolean isActive() {
        return active;
    }

    public void toogleActive() {
        active = !active;
    }

}
