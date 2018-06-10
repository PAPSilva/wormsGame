package org.academiadecodigo.bootcamp.wormgame.logic;


import org.academiadecodigo.bootcamp.gfx.*;
import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;
import org.academiadecodigo.bootcamp.physics2D.Body2D.RectangularBody2D;
import org.academiadecodigo.bootcamp.physics2D.Body2DSystem;
import org.academiadecodigo.bootcamp.physics2D.PhysicSystem;
import org.academiadecodigo.bootcamp.physics2D.collidable.Collider;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

import org.academiadecodigo.bootcamp.wormgame.actors.*;
import org.academiadecodigo.bootcamp.wormgame.actors.Character;
import org.academiadecodigo.bootcamp.wormgame.level.Level;
import org.academiadecodigo.bootcamp.wormgame.level.LevelType;
import org.academiadecodigo.bootcamp.wormgame.sound.SoundFX;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Iterator;
import java.util.List;

/**
 * Created by codecadet on 05/06/2018.
 */
public class Game implements KeyboardHandler {

    private Player player1;
    private Player player2;
    private Player activePlayer;
    private SgfxViewport simWindow;
    private PhysicSystem system;
    private Character selectedCharacter;
    private SgfxWeapon weaponUI;
    private int aimSide = KeyboardEvent.KEY_RIGHT;
    private boolean gameStarted = false;
    private Picture menuPic;
    private Picture gameOverPic;
    private Picture instructions;
    private boolean gameover = false;
    private boolean inInstructions = false;

    private static final double DELTA_TIME = 0.001;
    private static final int FRAMERATE = 30; // TODO implement this
    private static final double MOVE_THRESHOLD = 10.0;

    public Game() {

        simWindow = new SgfxViewport(1200, 800, 1.0);

    }


    public void openMenu() {
        gameStarted = false;
        inInstructions = false;

        initKeyboard();

        menuPic = new Picture();
        menuPic.load("startmenu.png");
        menuPic.draw();

        while (!inInstructions) {

            waitAsecond();

        }

        while (!gameStarted) {

            waitAsecond();

        }

        init(1);

    }


    public void init(int numOfChars) {

        SoundFX.play("sounds/game.wav");

        // Load random level
        LevelType levelType = LevelType.random();
        Level level = new Level(levelType.image(), levelType.obstacles(), levelType.spawnSites());
        Picture background = level.getPicture();
        List<RectangularBody2D> obstacles = level.getObstacles();
        List<Vector2D> spawnSites = level.getSpawns();
        //simWindow = new SgfxViewport(background.getWidth(),background.getHeight(), 1.0);

        // Start system
        Collider collider = new WormCollider(1.0E-8);
        Vector2D gravity = new Vector2D(0.0, -980.0);
        system = new Body2DSystem(gravity, collider);

        // Initialize scenario
        background.draw();
        Iterator<RectangularBody2D> obstacleIterator = obstacles.iterator();
        while (obstacleIterator.hasNext()) {

            RectangularBody2D body = obstacleIterator.next();
            Vector2D position = simWindow.fromViewportCoordinates(body.getPosition());

            SgfxRectangularBody2D sgfxBody = new SgfxRectangularBody2D(body.getMass(), body.getWidth(), body.getHeight(), position, simWindow);
            sgfxBody.rotate(body.getOrientation());
            sgfxBody.toggleMovable();

            system.add(sgfxBody); // TODO this is to see. Add it.next() directly.

            //RectangularBody2D rectBody = obstacleIterator.next();
            //body.toggleMovable();
            //system.add(body);
        }

        // Initialize players
        player1 = new Player("Player 1");
        for (WeaponType weaponType : WeaponType.values()) {

            player1.addFireable(new Weapon(weaponType));
        }

        player2 = new Player("Player 2");
        for (WeaponType weaponType : WeaponType.values()) {

            player2.addFireable(new Weapon(weaponType));

        }

        // Select initial player at random
        activePlayer = Math.random() > 0.5 ? player1 : player2;

        // Initialize characters
        Character randomCharacter;
        String soldierSkin1, soldierSkin2;
        Vector2D position;
        for (int i = 0; i < numOfChars; i++) {

            //Player 1
            soldierSkin1 = CharacterType.random().getImagePath();
            position = spawnSites.get((int) (Math.random() * spawnSites.size()));
            randomCharacter = createCharacter(position, soldierSkin1);
            player1.addCharacter(randomCharacter);
            system.add(randomCharacter);
            spawnSites.remove(position);

            // Player 2
            while ((soldierSkin2 = CharacterType.random().getImagePath()).equals(soldierSkin1)) {
                System.out.println(soldierSkin1 + " and " + soldierSkin2 );
            }
            position = spawnSites.get((int) (Math.random() * spawnSites.size()));
            randomCharacter = createCharacter(position, soldierSkin2);
            player2.addCharacter(randomCharacter);
            system.add(randomCharacter);
            spawnSites.remove(position);

        }

        selectedCharacter = activePlayer.nextCharacter();
        weaponUI = new SgfxWeapon(selectedCharacter.getWeapon().getWeaponType(), simWindow);
    }

    public void start() {

        // Run game
        simWindow.show();

        gameover = false;
        boolean allMoved = true;
        

        if (!selectedCharacter.isActive()) {
            selectedCharacter.toggleActive();
        }

        while (!gameover) {

            allMoved = update();

            try {
                Thread.sleep(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // Change player if turn ended
            checkTurnEnd(allMoved);

            // TODO check other conditions for end of game
            if (!activePlayer.hasCharacters() || !activePlayer.hasCharacters()) {
                gameover = true;
                continue;
            }

        }

        end();
        waitAsecond();
        gameOverScreen();

    }

    private boolean update() {

        // Run physic system
        system.update(DELTA_TIME, DELTA_TIME);

        // Check if Hittables are dead, remove them if so.
        for (Body2D body : system) {

            if (!(body instanceof Hittable)) {
                continue;
            }

            Hittable hittable = (Hittable) body;
            if (hittable.isDead()) {
                system.remove(body);
            }

        }

        // Check if all projectiles moved
        boolean allMoved = true;
        for (Body2D body : system) {

            if (!(body instanceof PainGiver)) {
                continue;
            }

            PainGiver painGiver = (PainGiver) body;
            if (painGiver.getPosition().norm() > MOVE_THRESHOLD) {
                allMoved = false;
                break;
            }

            // TODO check end of game


        }
        return allMoved;

    }

    private void checkTurnEnd(boolean allMoved) {

        // End turn conditions
        if (!activePlayer.fired()) {
            return;
        }

        // Deactivate current character
        // TODO deactivate instead of this?
        if (selectedCharacter.isActive()) {
            selectedCharacter.toggleActive();
        }

        // Select next player and its character
        activePlayer = (activePlayer == player1) ? player2 : player1;
        selectedCharacter = activePlayer.getSelectedCharacter();

        // Ensure this character is inactivated and select the next one
        // TODO repeated code
        if (selectedCharacter.isActive()) {
            selectedCharacter.toggleActive();
        }
        activePlayer.nextCharacter().toggleActive();


    }

    private void end() {

        //for(Body2D body : system) {
        //    system.remove(body);
        //}
        // TODO Canvas.getInstance().DELETE!

    }

    public void gameOverScreen() { // missing a picture

        gameover = true;

        gameOverPic = new Picture();
        gameOverPic.load("startmenu.png");
        gameOverPic.draw();

        while (gameover) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }

        }

    }


    // To substitute the createCharacters.
    private Character createCharacter(Vector2D position, String imagePath) {


        return new SgfxCharacter(30, 20, position, 100, 1, imagePath, simWindow);

    }


    // We have to pass positions for the characters
    private Character[] createCharacters(int numOfChars) {

        Character[] characters = new Character[numOfChars];

        for (Character character : characters) {
            character = new Character(30, 20, new Vector2D(100, 30), 100, 1);
        }

        return characters;

    }

    private void showInstructions() {

        if(inInstructions) {
            return;
        }
        instructions = new Picture();
        instructions.load("resources/instructions.png");
        instructions.draw();
        inInstructions = true;

    }

    private void hideInstructions() {

        instructions.delete();
        inInstructions = false;

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

        KeyboardEvent toMenu = new KeyboardEvent();
        toMenu.setKey(KeyboardEvent.KEY_Q);
        toMenu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(toMenu);

        KeyboardEvent instructionsIn = new KeyboardEvent();
        instructionsIn.setKey(KeyboardEvent.KEY_I);
        instructionsIn.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(instructionsIn);

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {


        // Move only when active
        if (gameStarted && !gameover && !inInstructions) {
            if (!selectedCharacter.isActive()) {
                return;
            }
        }

        // Deal with event
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                if (aimSide == KeyboardEvent.KEY_RIGHT) {
                    selectedCharacter.turnAim();
                    aimSide = KeyboardEvent.KEY_LEFT;
                }
                selectedCharacter.changeMomentum(new Vector2D(-1000.0, 0.0));
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (aimSide == KeyboardEvent.KEY_LEFT) {
                    selectedCharacter.turnAim();
                    aimSide = KeyboardEvent.KEY_RIGHT;
                }
                selectedCharacter.changeMomentum(new Vector2D(1000.0, 0.0));
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
                if(!gameStarted && !inInstructions) {
                    menuPic.delete();
                    showInstructions();
                    break;
                }
                if (!gameStarted) {
                    hideInstructions();
                    gameStarted = true;
                    break;
                }
                if (gameover) {
                    gameOverPic.delete();
                    openMenu();
                }
                if (activePlayer.fired()) {
                    break;
                }
                Projectile projectile = selectedCharacter.fire();
                if (projectile == null) {
                    break;
                }
                SgfxProjectile sgfxProjectile = new SgfxProjectile(projectile, simWindow);
                sgfxProjectile.setVelocity(projectile.getVelocity());
                sgfxProjectile.flip();
                system.add(sgfxProjectile);
                //activePlayer.toggleFired(); // TODO Uncomment for production
                break;
            case KeyboardEvent.KEY_N:
                Fireable fireable = activePlayer.nextWeapon(selectedCharacter.getWeapon());
                System.out.println(fireable.getWeaponType());
                selectedCharacter.changeWeapon(fireable);
                weaponUI.removeWeapon();
                weaponUI = new SgfxWeapon(selectedCharacter.getWeapon().getWeaponType(), simWindow);
                break;
            case KeyboardEvent.KEY_Q: //TODO Q not working yet
                //gameover = true;
                //openMenu();
                break;
            case KeyboardEvent.KEY_I:
                if(!inInstructions) {
                    showInstructions();
                    return;
                }
                hideInstructions();
                break;

        }

    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void waitAsecond() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

    }


}