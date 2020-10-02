/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import controller.Controller;
import core.Direction;
import core.Motion;
import entity.action.Action;
import entity.effect.Effect;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kmne6
 */
public abstract class MovingEntity extends GameObject {
  
  private Controller controller;
  protected Motion motion;
  protected AnimationManager animationManager;
  protected Direction direction;
  protected List<Effect> effects;
  protected Optional<Action> action;
  
  
  public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
    super();
    
    this.controller = controller;
    this.motion = new Motion(2);
    this.direction = Direction.South;
    this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));
    effects = new ArrayList<>();
    action = Optional.empty();
  }
  
  
  private void cleanup() {
    List.copyOf(effects).stream()
            .filter(Effect::shouldDelete)
            .forEach(effects::remove);
    
    if(action.isPresent() && action.get().isDone()) {
      action = Optional.empty();
    }
  }
  
  
  @Override
  public void update(State state) {
  
    handleAction(state);
    handleMotion();
    animationManager.update(direction);
    effects.forEach(effect -> effect.update(state, this));
    manageDirection();
    selectAnimation();
    
    position.apply(motion);
    cleanup();
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
    
    if(action.isPresent()) {
      animationManager.playAnimation(action.get().getAnimationName());
    } else if( motion.isMoving() ) {
      animationManager.playAnimation("walk");
    }
    else {
      animationManager.playAnimation("stand");
    }
    
  }

  public Controller getController() {
    return controller;
  }

  public void multiplySpeed(double multiplier) {
    motion.multiply(multiplier);
  }

  private void handleAction(State state) {
    
    if(action.isPresent()) {
      action.get().update(state, this);
    }
      
  }

  private void handleMotion() {
    
    if(!action.isPresent()) {
      motion.update(controller);
    } else {
      motion.stop();
    }
  }
  
  
  public void addEffect(Effect effect) {
    
    effects.add(effect);
  }
  
  
  public void perform(Action action) {
    
    this.action = Optional.of(action);
  }
  
}
