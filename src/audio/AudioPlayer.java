/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import game.settings.GameSettings;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author kmne6
 */
public class AudioPlayer {
  
  
  List<AudioClip> audioClips;
  
  
  public AudioPlayer() {
    
    audioClips = new ArrayList<>();

  }
  
  
  public void playMusic(String fileName) {
    
    final Clip clip = getClip(fileName);
    audioClips.add(new MusicClip(clip));
  }
  
  
  public void playSound(String fileName) {
    
    final Clip clip = getClip(fileName);
    audioClips.add(new SoundClip(clip));
  }
  
  
  private Clip getClip(String fileName) {
    
    final URL soundFile = AudioPlayer.class.getResource("/resources/sounds/" + fileName);
    
    try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
      
      final Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.setMicrosecondPosition(0);
      return clip;
      
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex ) {
      System.out.println("ex");;
    }
    
    return null;
    
  }
  
  
  public void update(GameSettings gameSettings) {
    
    audioClips.forEach(audioClip -> audioClip.update(gameSettings));
    
    List.copyOf(audioClips).forEach(audioClip -> {
      if(audioClip.hasFinishedPlaying()) {
        audioClip.cleanUp();
        audioClips.remove(audioClip);
      }
    });
  }
  
}
