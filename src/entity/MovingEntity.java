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

  protected Vector2D directionVector;
  
  protected Size collisionBoxSize;
  
  
  public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary) {
    super();
    
    this.entityController = entityController;
    this.motion = new Motion(2);
    this.direction = Direction.South;
    this.directionVector = new Vector2D(0, 0);
    this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));

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
    motion.update(entityController);
    handleMotion();
    animationManager.update(direction);
    
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
  

  protected abstract String selectAnimation();
  

  public EntityController getController() {
    return entityController;
  }

  // This is removed in video 35
//  public void multiplySpeed(double multiplier) {
//    motion.multiply(multiplier);
//  }

  
  public void addEffect(Effect effect) {
    
    effects.add(effect);
  }
  
  
  public void perform(Action action) {
    
    this.action = Optional.of(action);
  }

  private void handleCollisions(State state) {
    state.getCollidingGameObjects(this).forEach(this::handleCollision);
  }
  
  
  protected abstract void handleMotion();
  
  
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
    
    // If the dotProduct is > 0 the target is in front of us, if negative, target is behind us
    return dotProduct > 0;
  }
  
}
