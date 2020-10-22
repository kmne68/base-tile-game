/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.effect.Caffeinated;
import gfx.SpriteLibrary;
import controller.EntityController;

/**
 *
 * @author kmne6
 */
public class Player extends MovingEntity {
  
  public Player(EntityController entityController, SpriteLibrary spriteLibrary) {
    super(entityController, spriteLibrary);
    
    effects.add(new Caffeinated() );
  }

  @Override
  protected void handleCollision(GameObject other) {
    
    if(other instanceof NPC) {
      NPC npc = (NPC) other;
      npc.clearEffects();   // this clears whatever effect is on the NPC when they collide with out player
    }
  }
  
}
