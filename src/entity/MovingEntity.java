/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import controller.Controller;
import core.Direction;
import core.Motion;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import java.awt.Image;

/**
 *
 * @author kmne6
 */
public abstract class MovingEntity extends GameObject {
  
  protected Controller controller;
  protected Motion motion;
  protected AnimationManager animationManager;
  protected Direction direction;
  
  
  public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
    super();
    
    this.controller = controller;
    this.motion = new Motion(2);
    this.direction = Direction.South;
    this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));
    
  }
  
  
  @Override
  public void update() {
  
    motion.update(controller);
    position.apply(motion);
    manageDirection();
    selectAnimation();
    animationManager.update(direction);
  }
  
  
    
  @Override
  public Image getSprite() {
    
    return animationManager.getSprite();

  }

  private void manageDirection() {
    
    if(motion.isMoving() ) {
        this.direction = Direction.fromMotion(motion);
    }
    
  }

  private void selectAnimation() {
    
    if( motion.isMoving() ) {
      animationManager.playAnimation("walk");
    }
    else {
      animationManager.playAnimation("stand");
    }
    
  }
  
}
