/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import controller.Controller;
import entity.effect.Caffeinated;
import gfx.SpriteLibrary;

/**
 *
 * @author kmne6
 */
public class Player extends MovingEntity {
  
  public Player(Controller controller, SpriteLibrary spriteLibrary) {
    super(controller, spriteLibrary);
    
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
