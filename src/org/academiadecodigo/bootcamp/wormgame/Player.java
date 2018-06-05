package org.academiadecodigo.bootcamp.wormgame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 04/06/2018.
 */
public class Player {

    private String name;
    private ArrayList<Fireable> fireables;
    private List<Character> characters = new LinkedList<>();
    private Character selectedCharacter;

    public Player(String name) {

        fireables = new ArrayList();

        this.name = name;
        addFireables();

    }

    private void addFireables() {

        for (WeaponType weaponType : WeaponType.values()) {
            fireables.add(new Weapon(weaponType));
        }

    }

    public List<Fireable> getFireables() {
        return fireables;
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

    // TODO This, and consider an iterator.
    public Character nextCharacter() {
        return selectedCharacter;
    }

    public boolean hasCharacters() {
        return characters.size() > 0;
    }

    private String getName() {
        return name;
    }

}
