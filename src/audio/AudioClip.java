/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

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
  
  
  public void update(GameSettings gameSettings) {
    
    final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    control.setValue(getVolume(gameSettings));
  }
  
  
  protected abstract float getVolume(GameSettings gameSettings);
  
}
