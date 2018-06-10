package org.academiadecodigo.bootcamp.wormgame.actors;

public enum CharacterType {

    SOLDIER_GREEN("resources/soldier_skins/soldier_green.png"),
    SOLDIER_RED("resources/soldier_skins/soldier_red.png"),
    SOLDIER_CYAN("resources/soldier_skins/soldier_cyan.png"),
    SOLDIER_YELLOW("resources/soldier_skins/soldier_yellow.png");

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
