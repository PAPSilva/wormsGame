package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxProjectile;
import org.academiadecodigo.bootcamp.gfx.SgfxRectangularBody2D;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.Body2DSystem;
import org.academiadecodigo.bootcamp.physics2D.PhysicSystem;
import org.academiadecodigo.bootcamp.physics2D.collidable.Collider;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.gfx.SgfxCharacter;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 05/06/2018.
 */
public class Game implements KeyboardHandler {

    private Player player1;
    private Player player2;
    private SgfxViewport simWindow;
    private PhysicSystem system;
    private SgfxCharacter selectedCharacter;

    private static final double DELTA_TIME = 0.001;
    private static final int FRAMERATE = 30; // TODO implement this

    public void init(int numOfChars) {

        // Start viewport
        simWindow = new SgfxViewport(640,480, 1.0);

        // Start system
        Collider collider = new WormCollider(1.0E-8);
        Vector2D gravity = new Vector2D(0.0,-980.0);
        system = new Body2DSystem(10, gravity, collider);

        // Initialize scenario
        // Floor
        SgfxRectangularBody2D floor = new SgfxRectangularBody2D(20.0, 640.0, 15.0, new Vector2D(320.0, 7.5), simWindow);
        floor.toggleMovable();
        system.add(floor);
        // Walls
        SgfxRectangularBody2D wall1 = new SgfxRectangularBody2D(20.0, 1.0, 200.0, new Vector2D(642.5, 100.0), simWindow);
        wall1.toggleMovable();
        system.add(wall1);
        SgfxRectangularBody2D wall2 = new SgfxRectangularBody2D(20.0, 1.0, 105.0, new Vector2D(5.0, 25.0), simWindow);
        wall2.toggleMovable();
        system.add(wall2);


        // Initialize players TODO (and fireables)
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

        // Initialize characters
        Character randomCharacter;
        for (int i = 0; i < numOfChars; i++) {

            //Player 1
            randomCharacter = createCharacter();
            player1.addCharacter(randomCharacter);
            system.add(randomCharacter);

            // PLayer 2
            randomCharacter = createCharacter();
            player2.addCharacter(randomCharacter);
            system.add(randomCharacter);

        }

    }

    public void start() {

        // Select initial player at random
        Player activePlayer = Math.random() > 0.5 ? player1 : player2;

        // Run game
        simWindow.show();
        boolean gameover = false;
        boolean turnEnded = true;
        initKeyboard();

        while (!gameover) {

            system.update(DELTA_TIME, DELTA_TIME);

            try {
                Thread.sleep( 1 );
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // Change player if turn ended
            if(turnEnded) {

                if(!activePlayer.hasCharacters()) {
                    gameover = true;
                    continue;
                }

                activePlayer = (activePlayer == player1) ? player2 : player1;

                if(!activePlayer.hasCharacters()) {
                    gameover = true;
                    continue;
                }

                // Select next character
                SgfxCharacter selectedCharacter = (SgfxCharacter) activePlayer.getSelectedCharacter();
                this.selectedCharacter = selectedCharacter;

                if(!selectedCharacter.isActive()) {
                    selectedCharacter.toogleActive();
                    turnEnded = false;
                    continue;
                }

                selectedCharacter.toogleActive();
                ((SgfxCharacter) activePlayer.nextCharacter()).toogleActive();
                turnEnded = false;
            }

            // TODO check end of game
        }

    }

    // To substitute the createCharacters.
    private Character createCharacter(){

        return new SgfxCharacter(30, 20, new Vector2D(Math.random()*400,50), 100, 1, simWindow);

    }


    // We have to pass positions for the characters
    private Character[] createCharacters(int numOfChars) {

        Character[] characters = new Character[numOfChars];

        for(Character character : characters) {
            character = new Character(30, 20, new Vector2D(100,30), 100, 1);
        }

        return characters;

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


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        // Move only when active
        if(!selectedCharacter.isActive()) {
            return;
        }

        // Deal with event
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                selectedCharacter.changeMomentum(new Vector2D(-1000.0,0.0));
                break;
            case KeyboardEvent.KEY_RIGHT:
                selectedCharacter.changeMomentum(new Vector2D(1000.0,0.0));
                break;
            case KeyboardEvent.KEY_UP:
                selectedCharacter.changeAim(0.087);
                break;
            case KeyboardEvent.KEY_DOWN:
                selectedCharacter.changeAim(-0.087);
                break;
            case KeyboardEvent.KEY_M:
                selectedCharacter.changeMomentum(new Vector2D(0.0, 10000.0));
                break;
            case KeyboardEvent.KEY_SPACE:
                Projectile projectile = selectedCharacter.fire();
                if(projectile==null) {
                    break;
                }
                SgfxProjectile sgfxProjectile = new SgfxProjectile(projectile, simWindow);
                sgfxProjectile.setVelocity(projectile.getVelocity());
                system.add(sgfxProjectile);
                break;
            case KeyboardEvent.KEY_N:
                //selectedCharacter.changeWeapon();
                break;
        }

    }


    // For now, the keyReleased method is doing nothing
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        // Move only when active
        if(!selectedCharacter.isActive()) {
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



}
