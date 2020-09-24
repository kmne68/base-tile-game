/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import ai.state.AIState;
import ai.state.Stand;
import ai.state.Wander;
import entity.NPC;
import game.state.State;

/**
 *
 * @author kmne6
 * 
 * Acts as AI state holder "the brain"
 */
public class AIManager {
  
 private AIState currentAIState;
 
 
 public AIManager() {
   
   transitionTo("stand");
 }
 
 
 public void update(State state, NPC currentCharacter) {
   
   currentAIState.update(state, currentCharacter);
   
   if(currentAIState.shouldTransition(state, currentCharacter) ) {
     transitionTo(currentAIState.getNextState() );
     
   }
 }
 

  private void transitionTo(String nextState) {
    
    System.out.println("Transitioning to " + nextState);
    
    switch(nextState) {
      case "wander":
        currentAIState = new Wander();
        return;
      case "stand":
      default:
        currentAIState = new Stand();
    }
  }
  
}
