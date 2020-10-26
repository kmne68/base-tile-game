/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.humanoid.effect;

import entity.MovingEntity;
import entity.humanoid.action.Cough;
import game.GameLoop;
import game.state.State;

/**
 *
 * @author kmne6
 */
public class Sick extends Effect {
  
  
  private static final double COUGH_RATE = 1d / GameLoop.UPDATES_PER_SECOND / 1;
  
  
  public Sick() {
    super(Integer.MAX_VALUE);
    
  }
  
  
  @Override
  public void update(State state, entity.humanoid.Humanoid entity) {
    super.update(state, entity);
    
    if(Math.random() < COUGH_RATE) {
      entity.perform(new Cough());
    }
  }
  
}
