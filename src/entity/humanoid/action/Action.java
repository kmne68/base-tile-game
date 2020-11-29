/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.humanoid.action;

import entity.Humanoid;
import game.state.State;

/**
 *
 * @author kmne6
 */
public abstract class Action {
  
  public abstract void update(State state, Humanoid humanoid);
  public abstract boolean isDone();
  public abstract String getAnimationName();
  
}
