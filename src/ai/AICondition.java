/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import entity.NPC;
import game.state.State;

/**
 *
 * @author kmne6
 */
public interface AICondition {
  
  boolean isMet(State state, NPC currentCharacter);
  
}
