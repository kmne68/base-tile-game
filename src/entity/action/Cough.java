/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.action;

import core.CollisionBox;
import core.Position;
import core.Size;
import entity.MovingEntity;
import entity.effect.Sick;
import game.Game;
import game.GameLoop;
import game.state.State;

/**
 *
 * @author kmne6
 */
public class Cough extends Action {
  
  private int lifespanInSeconds;
  private Size spreadAreaSize;
  private double risk;
  
  
  public Cough() {
    lifespanInSeconds = GameLoop.UPDATES_PER_SECOND;
    spreadAreaSize = new Size(2 * Game.SPRITE_SIZE, Game.SPRITE_SIZE);
    risk = 0.1;
  }
  

  @Override
  public void update(State state, MovingEntity entity) {
    if(--lifespanInSeconds <= 0) {
      Position spreadAreaPosition = new Position(
             entity.getPosition().getX() - spreadAreaSize.getWidth() / 2,
             entity.getPosition().getY() - spreadAreaSize.getHeight() / 2
      );
     
      CollisionBox spreadArea = CollisionBox.of(spreadAreaPosition, spreadAreaSize);
      
      state.getGameObjectsOfClass(MovingEntity.class).stream()
              .filter(movingEntity -> movingEntity.getCollisionBox().collidesWith(spreadArea))
              .filter(movingEntity -> !movingEntity.isAffectedBy(Sick.class))
              .forEach(movingEntity -> {
                
                if(Math.random() < risk) {
                  movingEntity.addEffect(new Sick());
                }
              });
    }
  }

  
  @Override
  public boolean isDone() {
    return lifespanInSeconds <= 0;
  }

  
  @Override
  public String getAnimationName() {
    return "cough";
  }
  
}
