package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxCircularBody2D;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 04/06/2018.
 */
public class SgfxCharacter extends Character implements KeyboardHandler {

    private SgfxCircularBody2D circle;

    private int mass = 20;
    private int radius = 20;

    public SgfxCharacter(Vector2D position, SgfxViewport viewport) {
        super(position);
        circle = new SgfxCircularBody2D(mass, radius, position.x(), position.y(), viewport);
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

        KeyboardEvent jump = new KeyboardEvent();
        jump.setKey(KeyboardEvent.KEY_M);
        jump.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(jump);

        KeyboardEvent fire = new KeyboardEvent();
        fire.setKey(KeyboardEvent.KEY_SPACE);
        fire.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(fire);

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                circle.setVelocity(new Vector2D(-20, 0));
                circle.updatePosition(1.0);
                break;
            case KeyboardEvent.KEY_RIGHT:
                circle.setVelocity(new Vector2D(20, 0));
                circle.updatePosition(1.0);
                break;
            case KeyboardEvent.KEY_M:
                circle.changeMomentum(new Vector2D(10, 20));
                break;
        }

    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                circle.setVelocity(new Vector2D(0, 0));
                break;
            case KeyboardEvent.KEY_RIGHT:
                circle.setVelocity(new Vector2D(0, 0));
                break;
        }

    }

}
