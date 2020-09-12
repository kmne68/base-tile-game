/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.sun.glass.events.KeyEvent;

/**
 *
 * @author kmne6
 */
public class PlayerController implements Controller {
  
  private Input input;
  
  
  public PlayerController(Input input) {
    
    this.input = input;
    
  }
  

  @Override
  public boolean isRequestingUp() {
    
    return input.isPressed(KeyEvent.VK_UP);
    
  }

  @Override
  public boolean isRequestingDown() {
    
    return input.isPressed(KeyEvent.VK_DOWN);
    
  }

  @Override
  public boolean isRequestingLeft() {
    
    return input.isPressed(KeyEvent.VK_LEFT);
    
  }

  @Override
  public boolean isRequestingRight() {
    
    return input.isPressed(KeyEvent.VK_RIGHT);
    
  }
  
}
