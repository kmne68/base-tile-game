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
  private float musicVolume;
  private float soundVolume;
  
  
  public GameSettings(boolean debugMode) {
    
    this.debugMode = debugMode;
    gameSpeedMultiplier = 1;
    musicVolume = 0;
    soundVolume = 0;
    
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
