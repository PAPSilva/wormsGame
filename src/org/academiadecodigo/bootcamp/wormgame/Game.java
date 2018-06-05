package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.Body2DSystem;
import org.academiadecodigo.bootcamp.physics2D.collidable.Body2DCollider;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

import java.security.PrivateKey;

/**
 * Created by codecadet on 05/06/2018.
 */
public class Game {

    private Player player1;
    private Player player2;


    public void init(int numOfChars) {

        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

        player1.addCharacters(createCharacters(numOfChars));
        player2.addCharacters(createCharacters(numOfChars));

        // Start viewport
        SgfxViewport simWindow = new SgfxViewport(640,400, 1.0);
        simWindow.show();

        // Start system
        Body2DCollider collider = new Body2DCollider(1.0E-8);
        Vector2D gravity = new Vector2D(0.0,0);
        Body2DSystem system = new Body2DSystem(10, gravity, collider);


    }


    /*

    ===>>>> player.addMovable <<<<=====
    implementar collider

    end of turn
    end of game

     */

    public void start() {



    }

    // To substitute the createCharacters.
    private Character createCharacter(){

        return new Character(30, 20, new Vector2D(100,30), 100);

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
