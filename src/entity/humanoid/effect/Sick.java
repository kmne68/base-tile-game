/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.humanoid.effect;

import entity.MovingEntity;
import entity.humanoid.Humanoid;
import entity.humanoid.action.Cough;
import game.GameLoop;
import game.state.State;

/**
 *
 * @author kmne6
 */
public class Sick extends Effect {
  
  
  private static final double COUGH_RATE = 1d / GameLoop.UPDATES_PER_SECOND / 10;
  
  
  public Sick() {
    super(Integer.MAX_VALUE);
    
  }
  
  
  @Override
  public void update(State state, Humanoid humanoid) {
    super.update(state, humanoid);
    
    if(Math.random() < COUGH_RATE) {
      humanoid.perform(new Cough());
    }
  }
  
}
