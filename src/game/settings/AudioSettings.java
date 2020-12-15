/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.settings;

/**
 *
 * @author kmne6
 */
public class AudioSettings {
  
  
  private float musicVolume;
  private float soundVolume;
  

  public AudioSettings() {
    
    musicVolume = 1;
    soundVolume = 1;
    
    
  }

  public float getMusicVolume() {
    return musicVolume;
  }

  public void setMusicVolume(float musicVolume) {
    this.musicVolume = musicVolume;
  }

  public float getSoundVolume() {
    return soundVolume;
  }

  public void setSoundVolume(float soundVolume) {
    this.soundVolume = soundVolume;
  }
  
  
}
