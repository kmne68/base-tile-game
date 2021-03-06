/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import input.Input;
import java.awt.event.KeyEvent;

/**
 *
 * @author kmne6
 */
public class PlayerController implements EntityController {
  
  private Input input;
  
  
  public PlayerController(Input input) {
    
    this.input = input;
    
  }
  

  @Override
  public boolean isRequestingUp() {
    
    return input.isKeyCurrentlyPressed(KeyEvent.VK_UP);
    
  }

  @Override
  public boolean isRequestingDown() {
    
    return input.isKeyCurrentlyPressed(KeyEvent.VK_DOWN);
    
  }

  @Override
  public boolean isRequestingLeft() {
    
    return input.isKeyCurrentlyPressed(KeyEvent.VK_LEFT);
    
  }

  @Override
  public boolean isRequestingRight() {
    
    return input.isKeyCurrentlyPressed(KeyEvent.VK_RIGHT);
    
  }

  @Override
  public boolean isRequestingAction() {
    
    return input.isKeyPressed(KeyEvent.VK_SPACE);
    
  }
  
}
