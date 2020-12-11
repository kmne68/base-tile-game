/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.state;

import ai.AITransition;
import entity.NPC;
import state.State;

/**
 *
 * @author kmne6
 */
public abstract class AIState {
  
  private AITransition transition;

  public AIState() {
    this.transition = initializeTransition();
  }

  protected abstract AITransition initializeTransition(); 
  public abstract void update(State state, NPC currentCharcter);
  
  
  public boolean shouldTransition(State state, NPC currentCharacter) {
    
    return transition.shouldTransition(state, currentCharacter);
    
  }
  
  
  public String getNextState() {
    
    return transition.getNextState();
  }
  
  
}
