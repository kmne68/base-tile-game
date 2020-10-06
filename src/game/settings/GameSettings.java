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
  
  public GameSettings(boolean debugMode) {
    
    this.debugMode = debugMode;
  }
  
  
  public boolean isDebugMode() {    
    return debugMode;    
  }
  
}
