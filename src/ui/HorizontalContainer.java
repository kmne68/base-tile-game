/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import core.Position;
import core.Size;

/**
 *
 * @author kmne6
 */
public class HorizontalContainer extends UIContainer {
  
  public HorizontalContainer(Size windowSize) {
    super(windowSize);
  }

  @Override
  protected Size calculateContentSize() {
    
    int combinedChildWidth = 0;
    int tallestChildHeight = 0;
    
    for(UIComponent uiComponent : children) {
      combinedChildWidth += uiComponent.getSize().getWidth() + uiComponent.getMargin().getHorizontal();
      
      if(uiComponent.getSize().getHeight() > tallestChildHeight) {
        tallestChildHeight = uiComponent.getSize().getHeight();
      }
    }
    
    return new Size(combinedChildWidth, tallestChildHeight);
    
  }

  @Override
  protected void calculateContentPosition() {
    
    int currentX = padding.getLeft();
    
    for(UIComponent uiComponent : children) {
      currentX += uiComponent.getMargin().getLeft();
      uiComponent.setRelativePosition(new Position(currentX, padding.getTop()));
      uiComponent.setAbsolutePosition(new Position(
              currentX + absolutePosition.intX(),
              padding.getTop() + absolutePosition.intY() ));
      currentX += uiComponent.getSize().getWidth();
      currentX += uiComponent.getMargin().getRight();
      
    }
  }
  
}
