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
public class GameSettings {
  
  private boolean debugMode;
  private double gameSpeedMultiplier;
  private AudioSettings audioSettings;
  
  
  public GameSettings(boolean debugMode) {
    
    this.debugMode = debugMode;
    gameSpeedMultiplier = 1;
    audioSettings = new AudioSettings();
    
  }
  
  
  public boolean isDebugMode() {    
    return debugMode;    
  }

  
  public void toggleDebugMode() {
    
    debugMode = !debugMode;
    
  }
  
  
  public void increaseGameSpeed() {
    
    gameSpeedMultiplier += 0.25;
    
  }
  
  
  public void decreaseGameSpeed() {
    
    gameSpeedMultiplier -= 0.25;
    
  }
  
  
  public double getGameSpeedMultiplier() {
    
    return gameSpeedMultiplier;
  }

  public AudioSettings getAudioSettings() {
    return audioSettings;
  }

  public void setAudioSettings(AudioSettings audioSettings) {
    this.audioSettings = audioSettings;
  }
  
  
}
