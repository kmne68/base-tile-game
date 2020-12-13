/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import game.settings.GameSettings;
import javax.sound.sampled.Clip;

/**
 *
 * @author kmne6
 */
public class SoundClip extends AudioClip {

  public SoundClip(Clip clip) {
    super(clip);
  }

  @Override
  protected float getVolume(GameSettings gameSettings) {
    
    return gameSettings.getSoundVolume();
  }
  
  
  
  
}
