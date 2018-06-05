package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 04/06/2018.
 */
public class SgfxCharacter extends Character implements KeyboardHandler {

    private Ellipse circle;
    private SgfxViewport viewport;

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




    public void initKeyboard() {

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(left);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(right);

        KeyboardEvent leftReleased = new KeyboardEvent();
        leftReleased.setKey(KeyboardEvent.KEY_LEFT);
        leftReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(leftReleased);

        KeyboardEvent rightReleased = new KeyboardEvent();
        rightReleased.setKey(KeyboardEvent.KEY_RIGHT);
        rightReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(rightReleased);

        KeyboardEvent aimUp = new KeyboardEvent();
        aimUp.setKey(KeyboardEvent.KEY_UP);
        aimUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(aimUp);

        KeyboardEvent aimDown = new KeyboardEvent();
        aimDown.setKey(KeyboardEvent.KEY_DOWN);
        aimDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(aimDown);


        KeyboardEvent jump = new KeyboardEvent();
        jump.setKey(KeyboardEvent.KEY_M);
        jump.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(jump);

        KeyboardEvent fire = new KeyboardEvent();
        fire.setKey(KeyboardEvent.KEY_SPACE);
        fire.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(fire);

        KeyboardEvent changeWeapon = new KeyboardEvent();
        changeWeapon.setKey(KeyboardEvent.KEY_N);
        changeWeapon.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(changeWeapon);

    }


    // Should I add the move method, from the Character?!
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                this.setVelocity(new Vector2D(-20, 0));
                this.updatePosition(1.0);
                break;
            case KeyboardEvent.KEY_RIGHT:
                this.setVelocity(new Vector2D(20, 0));
                this.updatePosition(1.0);
                break;
            case KeyboardEvent.KEY_UP:
                this.changeAim(5);
                break;
            case KeyboardEvent.KEY_DOWN:
                this.changeAim(-5);
                break;
            case KeyboardEvent.KEY_M:
                this.changeMomentum(new Vector2D(10, 20));
                break;
            case KeyboardEvent.KEY_SPACE:
                this.fire();
                break;
            case KeyboardEvent.KEY_N:
                //this.changeWeapon();
                break;
        }

    }


    // For now, the keyReleased method is doing nothing
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                this.setVelocity(new Vector2D(0, 0));
                break;
            case KeyboardEvent.KEY_RIGHT:
                this.setVelocity(new Vector2D(0, 0));
                break;
        }

    }

}
