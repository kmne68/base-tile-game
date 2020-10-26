/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.humanoid.action;

import core.CollisionBox;
import core.Position;
import core.Size;
import entity.MovingEntity;
import entity.humanoid.Humanoid;
import entity.humanoid.effect.Sick;
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
  public void update(State state, entity.humanoid.Humanoid performer) {
    if(--lifespanInSeconds <= 0) {
      Position spreadAreaPosition = new Position(
             performer.getPosition().getX() - spreadAreaSize.getWidth() / 2,
             performer.getPosition().getY() - spreadAreaSize.getHeight() / 2
      );
     
      CollisionBox spreadArea = CollisionBox.of(spreadAreaPosition, spreadAreaSize);
      
      state.getGameObjectsOfClass(Humanoid.class).stream()
              .filter(humanoid -> humanoid.getCollisionBox().collidesWith(spreadArea))
              .filter(humanoid -> !humanoid.isAffectedBy(Sick.class))
              .forEach(humanoid -> {
                
                if(Math.random() < risk) {
                  humanoid.addEffect(new Sick());
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
