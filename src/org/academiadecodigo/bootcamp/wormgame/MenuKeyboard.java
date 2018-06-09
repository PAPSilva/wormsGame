package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 09/06/2018.
 */
public class MenuKeyboard implements KeyboardHandler {

    private Keyboard keyboard;
    private Menu menu;

    private KeyboardEvent start;



    public MenuKeyboard(Menu menu) {

        this.menu = menu;

    }




    public void initKeyboard() {

        keyboard = new Keyboard(this);

        start = new KeyboardEvent();
        start.setKey(KeyboardEvent.KEY_S);
        start.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(start);


    }

    public void closeEvents() {

        keyboard.removeEventListener(start);

    }



    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_S:
                closeEvents();
                menu.startGame();
                break;

        }

    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }



}
