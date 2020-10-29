/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import controller.EntityController;
import controller.NPCController;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import gfx.SpriteSet;

/**
 *
 * @author kmne6
 */
public class Bubble extends MovingEntity {
  
  private NPCController controller;
  

  public Bubble(NPCController npcController, SpriteLibrary spriteLibrary) {
    super(npcController, spriteLibrary);
    
    this.controller = npcController;
    
    this.animationManager = new AnimationManager(new SpriteSet(spriteLibrary.getImage("bubble")), false);
    
  }
  
  
  public void insert(GameObject gameObject) {
    this.position = gameObject.getPosition();
    this.renderOffset = gameObject.getRenderOffset();
    gameObject.setParent(this);
  }
  
  

  @Override
  protected void handleMotion() {
    
    motion.update(controller);
  }

  @Override
  protected String selectAnimation() {
    return "default";
  }

  @Override
  protected void handleCollision(GameObject other) {
  }
  
}
