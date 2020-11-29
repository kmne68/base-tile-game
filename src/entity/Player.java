/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.humanoid.effect.Caffeinated;
import gfx.SpriteLibrary;
import controller.EntityController;
import core.Position;
import game.Game;
import game.state.State;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author kmne6
 */
public class Player extends Humanoid {
  
  
  private NPC target;
  private double targetRange;
  private SelectionCircle selectionCircle;
  
  
  public Player(EntityController entityController, SpriteLibrary spriteLibrary, SelectionCircle selectionCircle) {
    super(entityController, spriteLibrary);
    
    this.selectionCircle = selectionCircle;
    this.targetRange = Game.SPRITE_SIZE;
    
    // effects.add(new Caffeinated() );
  }
  
  
  @Override
  public void update(State state) {
    super.update(state);
    
    handleTarget(state);    
  }
  

  @Override
  protected void handleCollision(GameObject other) {
    
    if(other instanceof NPC) {
      NPC npc = (NPC) other;
      npc.clearEffects();   // this clears whatever effect is on the NPC when they collide with out player
    }
  }

  private void handleTarget(State state) {

    Optional<NPC> closestNPC = findClosestNPC(state);
    
    if(closestNPC.isPresent()) {
      NPC npc = closestNPC.get();
      
      if(!npc.equals(target)) {
        selectionCircle.setParent(npc);
        target = npc;
      }
    } else {
      selectionCircle.clearParent();
      target = null;      
    }
  }
  

  private Optional<NPC> findClosestNPC(State state) {

    return state.getGameObjectsOfClass(NPC.class).stream()
            .filter(npc -> getPosition().distanceTo(npc.getPosition()) < targetRange)
            .filter(npc -> isFacing(npc.getPosition()))
            .min(Comparator.comparingDouble(npc -> position.distanceTo(npc.getPosition())));
    
  }
  
}
