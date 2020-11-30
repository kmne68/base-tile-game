/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.humanoid.effect;

import entity.MovingEntity;
import entity.humanoid.Humanoid;
import game.GameLoop;
import game.state.State;

/**
 *
 * @author kmne6
 */
public class Caffeinated extends Effect {
  
  private double speedMultiplier;
  
  public Caffeinated() {
    super(GameLoop.UPDATES_PER_SECOND * 5);
    
    speedMultiplier = 2.5;
  }
  
  
  @Override
  public void update(State state, Humanoid humanoid) {
    super.update(state, humanoid);
    
  //  entity.multiplySpeed(speedMultiplier);
  }
}
