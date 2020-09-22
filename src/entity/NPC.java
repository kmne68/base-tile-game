/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import controller.Controller;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

/**
 *
 * @author kmne6
 */
public class NPC extends MovingEntity {

  public NPC(Controller controller, SpriteLibrary spriteLibrary) {
    super(controller, spriteLibrary);
    
    animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
  }
  
  
  
}
