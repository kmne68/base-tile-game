/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import entity.NPC;
import state.State;

/**
 *
 * @author kmne6
 */
public class AITransition {
  
  private String nextState;
  private AICondition condition;

  public AITransition(String nextState, AICondition condition) {
    this.nextState = nextState;
    this.condition = condition;
  }
  
  /**
   * Wrapper function
   * @return 
   */
  public boolean shouldTransition(State state, NPC currentCharacter) {
    
    return condition.isMet(state, currentCharacter);
  }

  public String getNextState() {
    return nextState;
  }
  
  
  
}
