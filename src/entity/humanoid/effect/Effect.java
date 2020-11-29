/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.humanoid.effect;

import entity.Humanoid;
import game.state.State;

/**
 *
 * @author kmne6
 */
public abstract class Effect {
  
  private int lifespanInUpdates;
  
  public Effect(int lifespanInUpdates) {
    
    this.lifespanInUpdates = lifespanInUpdates;
  }
  
  
  public void update(State state, Humanoid entity) {
    lifespanInUpdates--;
  }
  
  
  public boolean shouldDelete() {
    return lifespanInUpdates <= 0;
  }
  
}
