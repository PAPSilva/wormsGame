package org.academiadecodigo.bootcamp.wormgame.sound;

import java.io.*;
import javax.sound.sampled.*;

public class SoundFX {
    public static void play(String pathname) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("resources/" + pathname)));
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
