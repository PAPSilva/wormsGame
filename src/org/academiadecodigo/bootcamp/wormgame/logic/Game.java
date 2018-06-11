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
import org.academiadecodigo.simplegraphics.graphics.Canvas;
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
    private boolean gameStarted = false;
    private Picture menuPic;
    private Picture gameOverPic;
    private Picture instructions;
    private boolean gameover = false;
    private boolean inInstructions = false;
    private SoundFX gamesound;
    private Collider collider;
    private boolean player1Wins;
    private Picture winnerPic;
    private Picture creditsBackground;
    private boolean inGame;

    private static final double DELTA_TIME = 0.01; // milliseconds
    private static final int FRAMERATE = 30;
    private static final double ARENA_MARGIN = 200;

    public void openMenu() {

        inGame = true;

        gameStarted = false;
        inInstructions = false;
        initKeyboard();
        menuPic = new Picture();

        while(inGame) {
            gameStarted = false;
            inInstructions = false;

            menuPic.load("resources/startmenu.png");
            menuPic.draw();

            while (!gameStarted) {
                waitAsecond();
            }

            init(2);

            start();


        }

    }


    public void init(int numOfChars) {

        gamesound = new SoundFX("sounds/game.wav");
        gamesound.play();

        // Load random level
        LevelType levelType = LevelType.random();
        Level level = new Level(levelType.image(), levelType.obstacles(), levelType.spawnSites());
        Picture background = level.getPicture();
        List<RectangularBody2D> obstacles = level.getObstacles();
        List<Vector2D> spawnSites = level.getSpawns();

        simWindow = new SgfxViewport(background.getWidth(),background.getHeight(), 1.0);

        // Start system
        collider = new WormCollider(1.0E-8);
        Vector2D gravity = new Vector2D(0.0, -9.80);
        system = new Body2DSystem(gravity, collider);

        // Initialize scenario
        Iterator<RectangularBody2D> obstacleIterator = obstacles.iterator();
        while (obstacleIterator.hasNext()) {

            RectangularBody2D body = obstacleIterator.next();
            Vector2D position = simWindow.fromViewportCoordinates(body.getPosition());

            SgfxRectangularBody2D sgfxBody = new SgfxRectangularBody2D(body.getMass(), body.getWidth(), body.getHeight(), position, simWindow);
            sgfxBody.rotate(body.getOrientation());
            sgfxBody.toggleMovable();

            system.add(sgfxBody);

        }
        background.draw();


        // Load Outbounds
        setArenaLimits(background.getWidth(), background.getHeight());

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
        activePlayer = player1;

        // Initialize characters, with random skin for each player.
        Character randomCharacter;
        String soldierSkin1 = CharacterType.random().getImagePath();
        String soldierSkin2;
        while ((soldierSkin2 = CharacterType.random().getImagePath()).equals(soldierSkin1)) {}
        Vector2D position;
        for (int i = 0; i < numOfChars; i++) {

            //Player 1
            position = spawnSites.get((int) (Math.random() * spawnSites.size()));
            randomCharacter = createCharacter(position, soldierSkin1);
            player1.addCharacter(randomCharacter);
            system.add(randomCharacter);
            spawnSites.remove(position);

            // Player 2

            position = spawnSites.get((int) (Math.random() * spawnSites.size()));
            randomCharacter = createCharacter(position, soldierSkin2);
            player2.addCharacter(randomCharacter);
            system.add(randomCharacter);
            spawnSites.remove(position);

        }

        selectedCharacter = activePlayer.nextCharacter();
        weaponUI = new SgfxWeapon(selectedCharacter.getWeapon().getWeaponType(), simWindow);

    }

    private void setArenaLimits(double width, double height) {

        double limitWidth = width + ARENA_MARGIN * 2.0;
        double limitHeigth = height + ARENA_MARGIN * 2.0;

        SgfxLimit upLimit = new SgfxLimit(limitWidth, 0.5,
                new Vector2D(width * 0.5,-ARENA_MARGIN), simWindow);
        SgfxLimit leftLimit = new SgfxLimit(0.5, limitHeigth,
                new Vector2D(-ARENA_MARGIN, height * 0.5), simWindow);
        SgfxLimit downLimit = new SgfxLimit(limitWidth, 0.5,
                new Vector2D(width * 0.5, height + ARENA_MARGIN), simWindow);
        SgfxLimit rightLimit = new SgfxLimit(0.5, limitHeigth,
                new Vector2D(width + ARENA_MARGIN, height * 0.5), simWindow);

        upLimit.toggleMovable();
        leftLimit.toggleMovable();
        downLimit.toggleMovable();
        rightLimit.toggleMovable();

        system.add(upLimit);
        system.add(leftLimit);
        system.add(downLimit);
        system.add(rightLimit);

    }

    public void start() {

        // Run game
        simWindow.show();

        gameover = false;
        boolean allMoved = false;
        

        if (!selectedCharacter.isActive()) {
            selectedCharacter.toggleActive();
        }

        long time = System.currentTimeMillis();
        long framePeriod = (long) (1.0 / (double) FRAMERATE);
        while (!gameover) {

            allMoved = update();

            try {
                Thread.sleep(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            time = System.nanoTime() * 1000;

            checkTurnEnd(allMoved);

            if (!player1.hasCharactersAvailable() || !player2.hasCharactersAvailable()) {
                gameover = true;
            }

        }

        checkWinner();
        end();


    }

    private boolean update() {

        // Run physic system
        system.update(DELTA_TIME, DELTA_TIME);

        // Check if Hittable PainGivers are dead, remove them if so.
        for (Body2D body : system) {

            if (!(body instanceof Hittable && body instanceof PainGiver)) {
                continue;
            }

            Hittable hittable = (Hittable) body;
            if (hittable.isDead()) {
                system.remove(body);
            }

        }


        // Check Collision with Boundaries
        for(Body2D body1 : system) {

            if(!(body1 instanceof DeathGiver)) {
                continue;
            }

            for(Body2D body2 : system) {

                if(body2 instanceof Hittable && collider.checkCollision(body2, body1)) {
                    Hittable hittable = (Hittable) body2;
                    hittable.suffer(hittable.health());
                    system.remove(body2);
                }

            }

        }

        // Check if all projectiles moved
        for (Body2D body : system) {

            if (body instanceof PainGiver) {
                return false;
            }

        }
        return true;

    }

    private void checkTurnEnd(boolean allMoved) {

        // Inactivate selected if it is dead
        if(selectedCharacter.isDead()) {
            selectedCharacter.toggleActive();
        }

        // Player still in his turn
        if ( selectedCharacter.isActive() ) {
            return;
        }

        // Player fired but there still is movement in the area
        if(!allMoved) {
            return;
        }

        // Deactivate current character
        if (selectedCharacter.isActive()) {
            selectedCharacter.toggleActive();
        }

        // Select next player and activate its next character
        activePlayer = (activePlayer == player1) ? player2 : player1;
        selectedCharacter = activePlayer.nextCharacter();
        selectedCharacter.toggleActive();
        changeWeaponUI();

    }

    private void checkWinner() {
        
        if(player2.hasCharactersAvailable()) {
            player1Wins = false;
        } else {
            player1Wins = true;
        }

    }

    private void end() {

        for(Body2D body : system) {
            system.remove(body);
        }

        waitAsecond();
        gameOverScreen();

    }

    public void gameOverScreen() { // missing a picture

        gameover = true;

        creditsBackground = new Picture();
        creditsBackground.load("resources/creditsbg.png");
        creditsBackground.draw();
        gameOverPic = new Picture();
        gameOverPic.load("resources/credits.png");
        gameOverPic.draw();

        winnerPic = new Picture();

        if(player1Wins) {
            winnerPic.load("resources/player1wins.png");
        } else {
            winnerPic.load("resources/player2wins.png");
        }

        winnerPic.draw();

        while (gameover) {

           waitAsecond();

        }

    }

    private Character createCharacter(Vector2D position, String imagePath) {

        return new SgfxCharacter(30, 20, position, 100, 1, imagePath, simWindow);

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
                if (Math.cos(selectedCharacter.getAim()) > 0 ) {
                    selectedCharacter.turnAim();
                }
                selectedCharacter.changeMomentum(new Vector2D(-100.0, 0.0));
                break;
            case KeyboardEvent.KEY_RIGHT:

                if (Math.cos(selectedCharacter.getAim()) < 0 ) {
                    selectedCharacter.turnAim();
                }
                selectedCharacter.changeMomentum(new Vector2D(100.0, 0.0));
                break;
            case KeyboardEvent.KEY_UP:
                selectedCharacter.changeAim(0.087);
                break;
            case KeyboardEvent.KEY_DOWN:
                selectedCharacter.changeAim(-0.087);
                break;
            case KeyboardEvent.KEY_M:
                selectedCharacter.changeMomentum(new Vector2D(0.0, 1000.0));
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
                    gameover = false;
                    gameOverPic.delete();
                    winnerPic.delete();
                    creditsBackground.delete();
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
                selectedCharacter.toggleActive(); // TODO Uncomment for production
                break;
            case KeyboardEvent.KEY_N:
                changeWeaponUI();
                break;
            case KeyboardEvent.KEY_Q: //TODO Add Q to instructions
                Canvas.getInstance().closeWindow();
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

    private void changeWeaponUI() {
        Fireable fireable = activePlayer.nextWeapon(selectedCharacter.getWeapon());
        selectedCharacter.changeWeapon(fireable);
        weaponUI.removeWeapon();
        weaponUI = new SgfxWeapon(selectedCharacter.getWeapon().getWeaponType(), simWindow);
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void waitAsecond() {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

    }


}
