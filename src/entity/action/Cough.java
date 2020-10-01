/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.action;

import entity.MovingEntity;
import game.GameLoop;
import game.state.State;

/**
 *
 * @author kmne6
 */
public class Cough extends Action {
  
  private int lifespanInSeconds;
  
  public Cough() {
    lifespanInSeconds = GameLoop.UPDATES_PER_SECOND;
  }

  @Override
  public void update(State state, MovingEntity entity) {
    lifespanInSeconds--;
  }

  @Override
  public boolean isDone() {
    return lifespanInSeconds <= 0;
  }

  @Override
  public String getAnimationName() {
    return "cough";
  }
  
}
