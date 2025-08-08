package myGame.Utilities;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SoundManager {
    // Singleton design pattern 
    private static final SoundManager instance = new SoundManager();
    public static SoundManager getInstance() {return instance;}
    
    private Clip backgroundMusic;
    private final List<Clip> soundEffects = new ArrayList<>();
    
    private SoundManager() {
        loadSounds();
    }
    // Load all sounds during initialization
    private void loadSounds() {
        try {
            // Load background music
            URL musicURL = getClass().getResource("/resources/sounds/Chippytoon.wav");
            AudioInputStream musicIn = AudioSystem.getAudioInputStream(musicURL);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(musicIn);
            
            // Load sound effects
            //loadSoundEffect("/sounds/sound1.wav");
           
        } catch (UnsupportedAudioFileException | IOException 
                | LineUnavailableException e) {
            System.err.println("Error loading sounds: " + e.getMessage());
        }
    }
    
    // Helper method to load individual sound effects
    private void loadSoundEffect(String soundPath) {
        try {
            URL soundURL = getClass().getResource(soundPath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            soundEffects.add(clip);
        } catch (Exception e) {
            System.err.println("Error loading sound effect: " + soundPath + " - " + e.getMessage());
        }
    }
    
    //Plays the background music (looped)
    public void playMusic() {
        stopMusic(); // Stop if already playing
        if (backgroundMusic != null) {
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusic.start();
        }
    }
    
    /**
     * Plays a sound effect once by its ID
     * @param soundID the index of the sound effect to play
     */
    public void playSound(int soundID) {
        if (soundID >= 0 && soundID < soundEffects.size()) {
            Clip clip = soundEffects.get(soundID);
            clip.setFramePosition(0); // Rewind to start
            clip.start();
        }
    }
    
    //Stops the background music
    public void stopMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.setFramePosition(0); // Rewind to start
        }
    }
}
