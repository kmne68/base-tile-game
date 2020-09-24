/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.state;

import ai.AITransition;
import entity.NPC;
import game.state.State;

/**
 *
 * @author kmne6
 */
public class Stand extends AIState {
  
  private int updatesAlive;

  @Override
  protected AITransition initializeTransition() {
    return new AITransition("wander", ((state, currentCharacter) -> updatesAlive >= state.getTime().getUpdatesFromSeconds(3) ) );
  }

  @Override
  public void update(State state, NPC currentCharcter) {
    updatesAlive++;
  }
  
}
