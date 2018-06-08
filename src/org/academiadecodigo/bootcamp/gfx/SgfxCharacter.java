package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.Character;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 04/06/2018.
 */
public class SgfxCharacter extends Character implements KeyboardHandler {

    private Ellipse circle;
    private Picture picture;
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
        picture = new Picture(viewCoord.x(),viewCoord.y(),"redball.png");
        double growdx = picture.getWidth()*0.5 - radius;
        double growdy = picture.getHeight()*0.5 - radius;
        System.out.println(picture.getWidth() + " " + growdx);
        picture.grow(-growdx, -growdy);
        picture.translate(-growdx, -growdy);
        picture.draw();
        //System.out.println(picture.pixels());
        //System.out.println(picture.getX());
        //System.out.println(picture.getY());
        //System.out.println(viewCoord);
        initKeyboard();
    }

    // Behavior

    @Override
    public void updatePosition(double dt) {
        // TODO errors might accumulate from double to integer. Check if they do
        Vector2D oldCoord = viewport.toViewportCoordinates(getPosition());
        super.updatePosition(dt);
        Vector2D newCoord = viewport.toViewportCoordinates(getPosition());
        //circle.translate(newCoord.x() - oldCoord.x(), newCoord.y() - oldCoord.y());
        picture.translate(newCoord.x() - oldCoord.x(), newCoord.y() - oldCoord.y());
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

        // Move only when active
        if(!active) {
            return;
        }

        // Deal with event
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                changeMomentum(new Vector2D(-1000.0,0.0));
                break;
            case KeyboardEvent.KEY_RIGHT:
                changeMomentum(new Vector2D(1000.0,0.0));
                break;
            case KeyboardEvent.KEY_UP:
                changeAim(0.087);
                break;
            case KeyboardEvent.KEY_DOWN:
                changeAim(-0.087);
                break;
            case KeyboardEvent.KEY_M:
                changeMomentum(new Vector2D(0.0, 10000.0));
                break;
            case KeyboardEvent.KEY_SPACE:
                fire();
                break;
            case KeyboardEvent.KEY_N:
                //changeWeapon();
                break;
        }

    }


    // For now, the keyReleased method is doing nothing
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        // Move only when active
        if(!active) {
            return;
        }

//        switch (keyboardEvent.getKey()) {
//            case KeyboardEvent.KEY_LEFT:
//                this.setVelocity(new Vector2D(0, 0));
//                break;
//            case KeyboardEvent.KEY_RIGHT:
//                this.setVelocity(new Vector2D(0, 0));
//                break;
//        }

    }

    public boolean isActive() {
        return active;
    }

    public void toogleActive() {
        active = !active;
    }

}
