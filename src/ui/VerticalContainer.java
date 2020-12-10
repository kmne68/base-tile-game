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
public class VerticalContainer extends UIContainer {

  public VerticalContainer(Size windowSize) {
    super(windowSize);
  }

  @Override
  protected Size calculateContentSize() {
    
    int combinedChildHeight = 0;
    int widestChildWidth = 0;
    
    for(UIComponent uiComponent : children) {
      combinedChildHeight += uiComponent.getSize().getHeight() + uiComponent.getMargin().getVertical();
      
      if(uiComponent.getSize().getWidth() > widestChildWidth) {
        widestChildWidth = uiComponent.getSize().getWidth();
      }
    }
    
    return new Size(widestChildWidth, combinedChildHeight);
    
  }

  @Override
  protected void calculateContentPosition() {
    
    int currentY = padding.getTop();
    
    for(UIComponent uiComponent : children) {
      currentY += uiComponent.getMargin().getTop();
      uiComponent.setRelativePosition(new Position(padding.getLeft(), currentY));
      uiComponent.setAbsolutePosition(new Position(
              padding.getLeft() + absolutePosition.intX(), 
              currentY + absolutePosition.intY()));
      currentY += uiComponent.getSize().getHeight();
      currentY += uiComponent.getMargin().getBottom();
      
    }
  }
  
}
