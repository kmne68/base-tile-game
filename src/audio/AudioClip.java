/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import game.settings.AudioSettings;
import game.settings.GameSettings;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author kmne6
 */
public abstract class AudioClip {
  
  
  private final Clip clip;
  
  
  public AudioClip(Clip clip) {
    
    this.clip = clip;
    clip.start();
    
  }
  
  
  public void cleanUp() {
    
    clip.close();
  }
  
  
  public boolean hasFinishedPlaying() {
    
    return !clip.isRunning();
  }
  
  
  public void update(AudioSettings audioSettings) {
    
    setVolume(audioSettings);
  }
  

  /***
   * This method converts gain to volume (our wave file does not work with Type.VOLUME)
   * allowing more standard values for volume control to be used   * 
   */
  protected void setVolume(AudioSettings audioSettings) {
    
    final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    float range = control.getMaximum() - control.getMinimum();
    float gain = (range * getVolume(audioSettings)) + control.getMinimum();
    
    control.setValue(gain);
  }
  
  
  protected abstract float getVolume(AudioSettings audioSettings);
  
}
