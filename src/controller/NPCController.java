/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import core.Position;
import entity.NPC;

/**
 *
 * @author kmne6
 */
public class NPCController implements EntityController {
  
  private boolean up;
  private boolean right;
  private boolean down;
  private boolean left;

  @Override
  public boolean isRequestingUp() {
    return up;
  }

  @Override
  public boolean isRequestingRight() {
    return right;
  }
  
  @Override
  public boolean isRequestingDown() {
    return down;
  }

  @Override
  public boolean isRequestingLeft() {
    return left;
  }


  public void moveToTarget(Position target, Position current) {
    
    double deltaX = target.getX() - current.getX();
    double deltaY = target.getY() - current.getY();
    
    up = deltaY < 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
    right = deltaX > 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;;
    down = deltaY > 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;;
    left = deltaX < 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;;
    
  }

  public void stop() {
    
    up = false;
    right = false;
    down = false;
    left = false;
  }
  
}
