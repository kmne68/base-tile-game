/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.humanoid.action;

import controller.NPCController;
import entity.Bubble;
import entity.humanoid.Humanoid;
import game.GameLoop;
import game.state.State;

/**
 *
 * @author kmne6
 */
public class BlowBubble extends Action {
  
  
  private int lifeSpanInUpdates;
  private Humanoid target;
  private Bubble bubble;

  
  public BlowBubble(Humanoid target) {
    lifeSpanInUpdates = GameLoop.UPDATES_PER_SECOND;
    this.target = target;
  }
  
  
  private void bubbleTarget(State state) {
    
    bubble = new Bubble(new NPCController(), state.getSpriteLibrary());
    bubble.insert(target);
    
    state.spawn(bubble);
  }
 

  @Override
  public void update(State state, Humanoid humanoid) {
    
    lifeSpanInUpdates--;
    
    if(bubble == null) {
      bubbleTarget(state);
    }
  }

  
  
  @Override
  public boolean isDone() {
    
    return lifeSpanInUpdates == 0;
  }

  @Override
  public String getAnimationName() {
    return "blow";
  }

  
}
