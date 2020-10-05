/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import ai.AIManager;
import controller.Controller;
import entity.action.Action;
import entity.action.Cough;
import entity.effect.Sick;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import java.util.Optional;

/**
 *
 * @author kmne6
 */
public class NPC extends MovingEntity {
  
  private AIManager aiManager;

  public NPC(Controller controller, SpriteLibrary spriteLibrary) {
    super(controller, spriteLibrary);
    
    animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
    aiManager = new AIManager();
  }
  
  
  @Override
  public void update(State state) {
    super.update(state);
    
    aiManager.update(state, this);
  }  

  @Override
  protected void handleCollision(GameObject other) {
    if(other instanceof Player) {
      motion.stop();
    }
  }

  void clearEffects() {
    effects.clear();
  }
  
}
