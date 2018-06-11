package org.academiadecodigo.bootcamp.wormgame.sound;

import java.io.*;
import javax.sound.sampled.*;

public class SoundFX {
    private Clip clip;

    public static void playOnce(String pathname) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("resources/" + pathname)));
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public SoundFX(String pathname) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("resources/" + pathname)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void play(){
        //clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public boolean isPlaying(){
        return clip.isRunning();
    }
}
