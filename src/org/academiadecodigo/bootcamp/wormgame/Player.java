package org.academiadecodigo.bootcamp.wormgame;

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
    private boolean fired = false;

    public Player(String name) {

        fireables = new ArrayList();

        this.name = name;

    }

//    private void addFireables() {
//
//        for (WeaponType weaponType : WeaponType.values()) {
//            fireables.add(new Weapon(weaponType));
//        }
//
//    }

    public void addFireable(Fireable fireable) {
        fireables.add(fireable);
    }

    public List<Fireable> getFireables() {
        return fireables;
    }

    public Fireable nextWeapon(Fireable fireable) {

        System.out.println("Weaponory = " + fireables.size());
        int index;
        if( (index = fireables.indexOf(fireable)) < 0 ) {
             return null;
        }

        System.out.println(index + " ; " + fireables.size());
        index = ++index % fireables.size();
        System.out.println("Ammo " + fireables.get(index).getAmmo());
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
        int indexOfNext = characters.indexOf(selectedCharacter) + 1;
        indexOfNext = (indexOfNext == characters.size()) ? 0 : indexOfNext;
        selectedCharacter = characters.get(indexOfNext);

        return selectedCharacter;

    }

    public boolean hasCharacters() {
        return characters.size() > 0;
    }

    public void toggleFired() {
        fired = !fired;
    }

    public boolean fired() {
        return fired;
    }

    private String getName() {
        return name;
    }

}
