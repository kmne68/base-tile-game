/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import core.Position;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author kmne6
 */
public class Input implements KeyListener, MouseListener, MouseMotionListener {
  
  private Position cursorPosition;
  private boolean mouseClicked;
  private boolean mousePressed;
  
  private boolean[] keyCurrentlyPressed;
  private boolean[] keyPressed;
  
  
  public Input() {
    keyPressed = new boolean[1000];
    keyCurrentlyPressed = new boolean[1000];
    cursorPosition = new Position(0, 0);
  }
  
  
  public boolean isKeyPressed(int keyCode) {
    
    if( !keyPressed[keyCode] && keyCurrentlyPressed[keyCode] ) {
      keyPressed[keyCode] = true;
      return true;
    }
    
    return false;
  }

  
  public boolean isKeyCurrentlyPressed(int keyCode) {
    
  //  for(boolean key : pressed) {
  //    System.out.println("key in pressed is: " + key);
  //  }  
    
    return keyCurrentlyPressed[keyCode];
  }
  
  
  public void clearMouseClick() {
    
    mouseClicked = false;
  }
  
  
  /*****************************************************************************
   * Interface Methods
   */  
  
  @Override
  public void keyTyped(KeyEvent e) {
    
  }

  
  @Override
  public void keyPressed(KeyEvent e) {
    
    keyCurrentlyPressed[e.getKeyCode()] = true;
  }

  
  @Override
  public void keyReleased(KeyEvent e) {
    
    keyCurrentlyPressed[ e.getKeyCode() ] = false;
    keyPressed[ e.getKeyCode() ] = false;
    
  }
  
  
  public Position getCursorPosition() {
    return cursorPosition;
  }

  public void setCursorPosition(Position cursorPosition) {
    this.cursorPosition = cursorPosition;
  }

  public boolean isMouseClicked() {
    return mouseClicked;
  }

  public void setMouseClicked(boolean mouseClicked) {
    this.mouseClicked = mouseClicked;
  }

  public boolean isMousePressed() {
    return mousePressed;
  }

  public void setMousePressed(boolean mousePressed) {
    this.mousePressed = mousePressed;
  }
  
  
  /* ABSTRACT CLASS METHODS */

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {

    mousePressed = true;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    
    mouseClicked = true;
    mousePressed = false;
  }

  @Override
  public void mouseEntered(MouseEvent e) {   }

  @Override
  public void mouseExited(MouseEvent e) {   }

  @Override
  public void mouseDragged(MouseEvent e) {
  
    cursorPosition = new Position(e.getPoint().getX(), e.getPoint().getY());
  }

  @Override  public void mouseMoved(MouseEvent e) {
    
    cursorPosition = new Position(e.getPoint().getX(), e.getPoint().getY());  }  
}
