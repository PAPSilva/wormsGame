package org.academiadecodigo.bootcamp.wormgame.logic;

import org.academiadecodigo.bootcamp.wormgame.actors.Character;
import org.academiadecodigo.bootcamp.wormgame.actors.Fireable;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 04/06/2018.
 */
public class Player {

    private String name;
    private List<Fireable> fireables = new LinkedList<>();
    private List<Character> characters = new LinkedList<>();
    private Character selectedCharacter = null;

    public Player(String name) {

        fireables = new ArrayList();

        this.name = name;

    }

    public void addFireable(Fireable fireable) {
        fireables.add(fireable);
    }

    public Fireable nextWeapon(Fireable fireable) {

        int index;
        if( (index = fireables.indexOf(fireable)) < 0 ) {
             return null;
        }

        index = ++index % fireables.size();
        return fireables.get(index);

    }

    public void addCharacter(Character character) {
        characters.add(character);
        // Appoint selected character to first.
        if(characters.size() == 1) {
            selectedCharacter = characters.get(0);
        }
    }

    public Character getSelectedCharacter() {

        return selectedCharacter;
    }

    public Character nextCharacter() throws EmptyStackException {

        // Check if there is any character
        if(characters.size() == 0) {
            throw new EmptyStackException();
        }

        // Select the next character and return it. Recycle if necessary.
        int indexOfNext = characters.indexOf(selectedCharacter) ;
        for(int i=0; i < characters.size(); i++) {
            indexOfNext++;
            indexOfNext = (indexOfNext == characters.size()) ? 0 : indexOfNext;
            if(!characters.get(indexOfNext).isDead()) {
                break;
            }
        }
        selectedCharacter = characters.get(indexOfNext);

        return selectedCharacter;

    }

    public boolean hasCharactersAvailable() {
        for(Character character : characters) {
            if(!character.isDead()) {
                return true;
            }
        }
        return false;
    }

}
