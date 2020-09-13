/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import controller.Controller;
import core.Movement;

/**
 *
 * @author kmne6
 */
public abstract class MovingEntity extends GameObject {
  
  private Controller controller;
  private Movement movement;
  
  
  public MovingEntity(Controller controller) {
    super();
    
    this.controller = controller;
    this.movement = new Movement(2);
    
  }
  
  
  @Override
  public void update() {
  
    movement.update(controller);
    position.apply(movement);
  }
  
}
