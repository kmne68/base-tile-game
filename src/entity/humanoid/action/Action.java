/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.humanoid.action;

import entity.MovingEntity;
import entity.humanoid.Humanoid;
import state.State;

/**
 *
 * @author kmne6
 */
public abstract class Action {
  
  protected boolean interruptable;
  
  public Action() {
    interruptable = true;
  }
  
  
  public abstract void update(State state, Humanoid humanoid);
  public abstract boolean isDone();
  public abstract String getAnimationName();
  
  public boolean isInterruptable() {
    return interruptable;
  }
  
}
