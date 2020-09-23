/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author kmne6
 */
public class Time {
  
  private int updateSinceStart;
  
  
  public Time() {
    
    this.updateSinceStart = 0;
    
  }
  
  
  public int getUpdatesFromSeconds(int seconds) {
    
    return seconds * GameLoop.UPDATES_PER_SECOND;
  }
}
