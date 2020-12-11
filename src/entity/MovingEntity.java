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
import entity.humanoid.effect.Sick;
import state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.stream.Stream;
import controller.EntityController;

/**
 *
 * @author kmne6
 */
public abstract class MovingEntity extends GameObject {
  
  protected EntityController entityController;
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
    this.animationManager = new AnimationManager(spriteLibrary.getSpriteSet("matt"));
    this.collisionBoxSize = new Size(size.getWidth(), size.getHeight());

  }
  
  
  protected abstract void handleMotion();
  
  
  @Override
  public void update(State state) {
  
    motion.update(entityController);
    handleMotion();
    animationManager.update(direction);
    
    handleCollisions(state);
    manageDirection();
    animationManager.playAnimation(selectAnimation());
    
    position.apply(motion);
  }

  
  @Override
  public Image getSprite() {
    
    Image sprite = animationManager.getSprite();
//    sprite.toString();
//    System.out.println("sprite is null? " + sprite.equals(null) );
    
    return sprite;
  //  return animationManager.getSprite();
  }

  
  private void manageDirection() {
    
    if(motion.isMoving() ) {
        this.direction = Direction.fromMotion(motion);
        this.directionVector = motion.getDirection();
    }    
  }
  

  protected abstract String selectAnimation();
  

  public EntityController getEntityController() {
    return entityController;
  }

  
  // in video, this method was removed in episode 35
//  public void multiplySpeed(double multiplier) {
//    motion.multiply(multiplier);
//  }

  


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

  
  
  public boolean isFacing(Position other) {
    
    Vector2D direction = Vector2D.directionBetweenPositions(other, getPosition());
    double dotProduct = Vector2D.dotProduct(direction, directionVector);
    
    // If the dot product is larger than zero, the tagret is ahead of us
    return dotProduct > 0;
  }
  
}
