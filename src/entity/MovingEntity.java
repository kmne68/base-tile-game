/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import core.CollisionBox;
import core.Direction;
import core.Motion;
import core.Position;
import core.Size;
import core.Vector2D;
import entity.action.Action;
import entity.effect.Effect;
import entity.effect.Sick;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import controller.EntityController;

/**
 *
 * @author kmne6
 */
public abstract class MovingEntity extends GameObject {
  
  private EntityController entityController;
  protected Motion motion;
  protected AnimationManager animationManager;
  protected Direction direction;
  protected List<Effect> effects;
  protected Optional<Action> action;
  
  protected Vector2D directionVector;
  protected Size collisionBoxSize;
  
  
  public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary) {
    super();
    
    this.entityController = entityController;
    this.motion = new Motion(2);
    this.direction = Direction.South;
    this.directionVector = new Vector2D(0, 0);
    this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));
    effects = new ArrayList<>();
    action = Optional.empty();
    this.collisionBoxSize = new Size(16, 28);
    this.renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
    this.collisionBoxOffset = new Position(collisionBoxSize.getWidth() / 2, collisionBoxSize.getHeight());
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
    
    handleCollisions(state);
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
        this.directionVector = motion.getDirection();
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
  

  public EntityController getController() {
    return entityController;
  }

  
  // in video, this method was removed in episode 35
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
      motion.update(entityController);
    } else {
      motion.stop(true, true);
    }
  }
  
  
  public void addEffect(Effect effect) {
    
    effects.add(effect);
  }
  
  
  public void perform(Action action) {
    
    this.action = Optional.of(action);
  }

  private void handleCollisions(State state) {
    state.getCollidingGameObjects(this).forEach(this::handleCollision);
  }
  
  
  protected abstract void handleCollision(GameObject other);
  
  
  @Override
  public CollisionBox getCollisionBox() {
    
    Position positionWithMotion = Position.copyOf(getPosition());
    positionWithMotion.apply(motion);
    positionWithMotion.subtract(collisionBoxOffset);
    
    return new CollisionBox(
            new Rectangle(
                    positionWithMotion.intX(),
                    positionWithMotion.intY(),
                    collisionBoxSize.getWidth(),
                    collisionBoxSize.getHeight()
            )
    );    
  }
  
 
  public boolean willCollideX(GameObject other) {
    CollisionBox otherBox = other.getCollisionBox();
    Position positionWithXApplied = Position.copyOf(position);
    positionWithXApplied.applyX(motion);
    positionWithXApplied.subtract(collisionBoxOffset);
    
    return CollisionBox.of(positionWithXApplied, collisionBoxSize).collidesWith(otherBox);
  }
  
  
  public boolean willCollideY(GameObject other) {
    CollisionBox otherBox = other.getCollisionBox();
    Position positionWithYApplied = Position.copyOf(position);
    positionWithYApplied.applyY(motion);
    positionWithYApplied.subtract(collisionBoxOffset);
    
    return CollisionBox.of(positionWithYApplied, collisionBoxSize).collidesWith(otherBox);
  }

  public boolean isAffectedBy(Class<?> aClass) {
    
    return effects.stream()
            .anyMatch(effect -> aClass.isInstance(effect));
  }
  
  
  public boolean isFacing(Position other) {
    
    Vector2D direction = Vector2D.directionBetweenPositions(other, getPosition());
    double dotProduct = Vector2D.dotProduct(direction, directionVector);
    
    // If the dot product is larger than zero, the tagret is ahead of us
    return dotProduct > 0;
  }
  
}
