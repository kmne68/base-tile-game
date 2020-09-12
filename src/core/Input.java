/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author kmne6
 */
public class Input implements KeyListener {
  
  private boolean[] pressed;
  
  
  public Input() {
    
    pressed = new boolean[255];
  }

  
  public boolean isPressed(int keyCode) {
    
  //  for(boolean key : pressed) {
  //    System.out.println("key in pressed is: " + key);
  //  }  
    
    return pressed[keyCode];
  }
  
  
  /*****************************************************************************
   * Interface Methods
   */  
  
  @Override
  public void keyTyped(KeyEvent e) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void keyPressed(KeyEvent e) {
    
    pressed[e.getKeyCode()] = true;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    
    pressed[e.getKeyCode()] = false;
  }
  
}
