/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.clickable;

import core.Position;
import state.State;
import java.awt.Rectangle;
import ui.UIComponent;

/**
 *
 * @author kmne6
 */
public abstract class UIClickable extends UIComponent {
  
  
  protected boolean hasFocus;
  protected boolean isPressed;
  
  
  @Override
  public void update(State state) {
    
    Position mousePosition = state.getInput().getCursorPosition();
    
    hasFocus = getBounds().contains(mousePosition.intX(), mousePosition.intY());
    isPressed = hasFocus && state.getInput().isMousePressed();
    
    if(hasFocus && state.getInput().isMouseClicked() ) {
      onClick(state);
    }
  }
  
  
  protected abstract void onClick(State state);
  
  
  private Rectangle getBounds() {
    
    return new Rectangle(
            absolutePosition.intX(),
            absolutePosition.intY(),
            size.getWidth(),
            size.getHeight()
    );
  }
  
}
