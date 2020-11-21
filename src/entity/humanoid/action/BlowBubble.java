/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.humanoid.action;

import controller.NPCController;
import entity.Bubble;
import entity.Untargetable;
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
    
    System.out.println("BlowBubble constructor");
    
    lifeSpanInUpdates = GameLoop.UPDATES_PER_SECOND;
    this.target = target;
    interruptable = false;
  }
  
  
  private void bubbleTarget(State state) {
    
    target.perform(new Levitate());
    target.addEffect(new Untargetable());
    
    System.out.println("BlowBubble bubbleTarget()");
    
    bubble = new Bubble(new NPCController(), state.getSpriteLibrary());
    bubble.insert(target);
    
    state.spawn(bubble);
  }
 

  @Override
  public void update(State state, Humanoid humanoid) {    
    
    System.out.println("BlowBubble update()");
    
    lifeSpanInUpdates--;
    
    if(bubble == null) {
      bubbleTarget(state);
    }
    
    if(isDone()) {
      target.setRenderOrder(6);
      bubble.setRenderOrder(6);
    }
  }
  
  
  @Override
  public boolean isDone() {
    
    
    System.out.println("BlowBubble isDone()");
    
    return lifeSpanInUpdates == 0;
  }

  @Override
  public String getAnimationName() {
    
    
    System.out.println("BlowBubble getAnimationName()");
    
    return "blow";
  }

  
}
