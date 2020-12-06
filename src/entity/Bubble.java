/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import controller.EntityController;
import controller.NPCController;
import core.Direction;
import core.Vector2D;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import gfx.SpriteSet;

/**
 *
 * @author kmne6
 */
public class Bubble extends MovingEntity {
  
  private boolean halted;

  public Bubble(NPCController npcController, SpriteLibrary spriteLibrary) {
    super(npcController, spriteLibrary);
    
    this.animationManager = new AnimationManager(new SpriteSet(spriteLibrary.getImage("bubble")), false);
    
  }
  
  
  public void insert(GameObject gameObject) {
    this.position = gameObject.getPosition();
    this.renderOffset = gameObject.getRenderOffset();
    this.collisionBoxOffset = renderOffset;
    gameObject.parent(this);
  }
  
  
  public void halt() {
    
    halted = true;
  }
  

  @Override
  protected void handleMotion() {
    
    if(!halted) {
      motion.add(new Vector2D(0, -0.5));
    }
    
    halted = false;
    direction = Direction.South;
  }

  @Override
  protected String selectAnimation() {
    return "default";
  }

  @Override
  protected void handleCollision(GameObject other) {
  }
  
}
