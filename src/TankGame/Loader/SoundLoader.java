package TankGame.Loader;

import TankGame.GameObject.ResourceField;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static TankGame.GameObject.ResourceField.*;

public class SoundLoader {
    private static final Logger LOGGER = Logger.getLogger(SoundLoader.class.getName());
    private static Map<ResourceField, Clip> soundMap = new HashMap<>();

    public SoundLoader() {
        try {
            AudioInputStream soundStream =
                    AudioSystem.getAudioInputStream(SoundLoader.class.getResource("/TankGame/resources/Music.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(soundStream);
            soundMap.put(MUSIC, clip);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load sound", e);
        }
    }

    public void playSound(ResourceField field, boolean isLoopMode) {
        Clip clip = soundMap.get(field);

        if (isLoopMode) {
            Runnable runnable = () -> {
                while (true) {
                    clip.start();
                    clip.loop(clip.LOOP_CONTINUOUSLY);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Logger.getLogger(SoundLoader.class.getName()).log(Level.SEVERE, "Failed to play sound for " + field, e);
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        } else {
            clip.start();
        }
    }
}
