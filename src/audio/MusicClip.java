/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import game.settings.AudioSettings;
import javax.sound.sampled.Clip;

/**
 *
 * @author kmne6
 */
public class MusicClip extends AudioClip {

  public MusicClip(Clip clip) {
    super(clip);
  }

  @Override
  protected float getVolume(AudioSettings audioSettings) {
    
    return audioSettings.getMusicVolume();
  }
  
}
