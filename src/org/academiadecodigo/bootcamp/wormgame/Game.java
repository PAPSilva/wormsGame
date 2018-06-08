package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxRectangularBody2D;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.Body2DSystem;
import org.academiadecodigo.bootcamp.physics2D.collidable.Body2DCollider;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.SgfxCharacter;

import java.security.PrivateKey;

/**
 * Created by codecadet on 05/06/2018.
 */
public class Game {

    private Player player1;
    private Player player2;
    private SgfxViewport simWindow;
    private WormSystem system;

    private static final double DELTA_TIME = 0.001;
    private static final int FRAMERATE = 30; // TODO implement this

    public void init(int numOfChars) {

        // Start viewport
        simWindow = new SgfxViewport(640,400, 1.0);

        // Start system
        Body2DCollider collider = new Body2DCollider(1.0E-8);
        Vector2D gravity = new Vector2D(0.0,-98.0);
        system = new WormSystem(10, gravity, collider);

        // Initialize scenario
        SgfxRectangularBody2D floor = new SgfxRectangularBody2D(20.0, 640.0, 15.0, new Vector2D(320.0, 7.5), simWindow);
        floor.toggleMovable();
        system.add(floor);


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

        return new SgfxCharacter(30, 20, new Vector2D(Math.random()*400,50), 100, simWindow);

    }


    // We have to pass positions for the characters
    private Character[] createCharacters(int numOfChars) {

        Character[] characters = new Character[numOfChars];

        for(Character character : characters) {
            character = new Character(30, 20, new Vector2D(100,30), 100);
        }

        return characters;

    }

}
