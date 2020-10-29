/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author kmne6
 */
public class Input implements KeyListener {
  
  private boolean[] currentlyPressed;
  private boolean[] pressed;
  
  
  public Input() {
    pressed = new boolean[255];
    currentlyPressed = new boolean[255];
  }
  
  
  public boolean isPressed(int keyCode) {
    
    if( !pressed[keyCode] && currentlyPressed[keyCode] ) {
      pressed[keyCode] = true;
      return true;
    }
    
    return false;
  }

  
  public boolean isCurrentlyPressed(int keyCode) {
    
  //  for(boolean key : pressed) {
  //    System.out.println("key in pressed is: " + key);
  //  }  
    
    return currentlyPressed[keyCode];
  }
  
  
  /*****************************************************************************
   * Interface Methods
   */  
  
  @Override
  public void keyTyped(KeyEvent e) {
    
  }

  
  @Override
  public void keyPressed(KeyEvent e) {
    
    currentlyPressed[e.getKeyCode()] = true;
  }

  
  @Override
  public void keyReleased(KeyEvent e) {
    
    currentlyPressed[ e.getKeyCode() ] = false;
    pressed[ e.getKeyCode() ] = false;
    
  }
  
  
}
