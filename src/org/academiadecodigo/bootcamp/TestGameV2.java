package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gfx.SgfxPicture;
import org.academiadecodigo.bootcamp.gfx.SgfxRectangularBody2D;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.Body2DSystem;
import org.academiadecodigo.bootcamp.physics2D.collidable.Body2DCollider;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.Character;
import org.academiadecodigo.bootcamp.wormgame.Player;

/**
 * Created by codecadet on 06/06/2018.
 */
public class TestGameV2 {

    private Player player1;
    private Player player2;
    private SgfxViewport simWindow;
    private Body2DSystem system;

    private static final double DELTA_TIME = 0.001;
    private static final int FRAMERATE = 30; // TODO implement this

    public void init(int numOfChars) {

        // Start viewport
        simWindow = new SgfxViewport(800, 600, 1.0);

        // Start system
        Body2DCollider collider = new Body2DCollider(1.0E-8);
        Vector2D gravity = new Vector2D(0.0, -980.0);
        system = new Body2DSystem(10, gravity, collider);

        // Initialize scenario
        // Floor
        SgfxRectangularBody2D floor = new SgfxRectangularBody2D(20.0, 640.0, 15.0, new Vector2D(320.0, 7.5), simWindow);
        floor.toggleMovable();
        system.add(floor);
        // Walls
        SgfxRectangularBody2D wall1 = new SgfxRectangularBody2D(20.0, 1.0, 480.0, new Vector2D(642.5, 240.0), simWindow);
        wall1.toggleMovable();
        system.add(wall1);
        SgfxRectangularBody2D wall2 = new SgfxRectangularBody2D(20.0, 1.0, 480.0, new Vector2D(5.0, 240.0), simWindow);
        wall2.toggleMovable();
        system.add(wall2);
        SgfxRectangularBody2D ceiling = new SgfxRectangularBody2D(20.0, 640.0, 15.0, new Vector2D(320.0, 400.0), simWindow);
        ceiling.toggleMovable();
        system.add(ceiling);




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


    /*

    ===>>>> player.addMovable <<<<=====
    implementar collider

    end of game

     */

    public void start() {

        // Select initial player at random
        Player activePlayer = Math.random() > 0.5 ? player1 : player2;

        // Run game
        simWindow.show();
        boolean gameover = false;
        boolean turnEnded = true;
        while (!gameover) {

            system.update(DELTA_TIME, DELTA_TIME);

            try {
                Thread.sleep(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // Change player if turn ended
            if (turnEnded) {

                if (!activePlayer.hasCharacters()) {
                    gameover = true;
                    continue;
                }

                activePlayer = (activePlayer == player1) ? player2 : player1;

                if (!activePlayer.hasCharacters()) {
                    gameover = true;
                    continue;
                }

                // Select next character
                SgfxPicture selectedCharacter = (SgfxPicture) activePlayer.getSelectedCharacter();
                if (!selectedCharacter.isActive()) {
                    selectedCharacter.toogleActive();
                    turnEnded = false;
                    continue;
                }
                selectedCharacter.toogleActive();
                ((SgfxPicture) activePlayer.nextCharacter()).toogleActive();
                turnEnded = false;
            }

            // TODO check end of game
        }

    }

    // To substitute the createCharacters.
    private Character createCharacter() {

        return new SgfxPicture(10, 10, new Vector2D(Math.random() * 400, 50), 100, simWindow);

    }


    // We have to pass positions for the characters
    private Character[] createCharacters(int numOfChars) {

        Character[] characters = new Character[numOfChars];

        for (Character character : characters) {
            character = new Character(30, 40, new Vector2D(100, 30), 100, 1);
        }

        return characters;

    }

}





