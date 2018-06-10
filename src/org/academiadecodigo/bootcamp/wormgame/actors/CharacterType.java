package org.academiadecodigo.bootcamp.wormgame.actors;

public enum CharacterType {

    SOLDIER_GREEN("soldier_skins/soldier_green.png"),
    SOLDIER_RED("soldier_skins/soldier_red.png"),
    SOLDIER_CYAN("soldier_skins/soldier_cyan.png"),
    SOLDIER_YELLOW("soldier_skins/soldier_yellow.png");

    private String imagePath;

    CharacterType(String imagePath) {
        this.imagePath = imagePath;
    }

    public static CharacterType random() {
        return values()[ (int) (Math.random() * values().length) ];
    }

    public String getImagePath() {
        return imagePath;
    }

}
