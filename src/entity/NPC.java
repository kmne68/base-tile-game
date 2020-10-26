/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import ai.AIManager;
import entity.humanoid.action.Action;
import entity.humanoid.action.Cough;
import entity.humanoid.effect.Sick;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import java.util.Optional;
import controller.EntityController;
import entity.humanoid.Humanoid;

/**
 *
 * @author kmne6
 */
public class NPC extends Humanoid {
  
  private AIManager aiManager;

  public NPC(EntityController entityController, SpriteLibrary spriteLibrary) {
    super(entityController, spriteLibrary);
    
    animationManager = new AnimationManager(spriteLibrary.getSpriteSet("dave"));
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
      motion.stop(willCollideX(other), willCollideY(other) );
    }
  }

}
