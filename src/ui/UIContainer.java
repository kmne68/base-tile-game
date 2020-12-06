/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kmne6
 */
public abstract class UIContainer extends UIComponent {
  
  protected Color backgroundColor;
  protected Alignment alignment;
  protected Size windowSize;
  protected List<UIComponent> children;

  
  public UIContainer(Size windowSize) {
    super();
    this.windowSize = windowSize;
    alignment = new Alignment(Alignment.Position.START, Alignment.Position.START);
    backgroundColor = new Color(0, 0, 0, 0);
    margin = new Spacing(5);
    padding = new Spacing(5);
    children = new ArrayList<>();
    calculateSize();
    calculatePosition();
  }
  
  
  protected abstract Size calculateContentSize();
  protected abstract void calculateContentPosition();
  
  
  private void calculateSize() {
    Size calculatedContentSize = calculateContentSize();
    size =  new Size(
            padding.getHorizontal() + calculatedContentSize.getWidth(),
            padding.getVertical() + calculatedContentSize.getHeight()
    );
  }
  
  
  private void calculatePosition() {
    
    int x = padding.getLeft();
    
    if(alignment.getHorizontal().equals(Alignment.Position.CENTER)) {
      x = windowSize.getWidth() / 2 - size.getWidth() / 2;    // adjust location for size of component
    }
    if(alignment.getHorizontal().equals(Alignment.Position.END)) {
      x = windowSize.getWidth() - size.getWidth() - margin.getRight();
    }
    
    int y = padding.getTop();
    
    if(alignment.getVertical().equals(Alignment.Position.CENTER)) {
      y = windowSize.getHeight() / 2 - size.getHeight() / 2;    // adjust location for size of component
    }
    if(alignment.getVertical().equals(Alignment.Position.END)) {
      y = windowSize.getHeight() - size.getHeight() - margin.getBottom();
    }
    
    this.position = new Position(x, y);
            
    calculateContentPosition();
  }
  

  @Override
  public Image getSprite() {
    BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
    Graphics2D graphics = image.createGraphics();
    
    graphics.setColor(backgroundColor);
    graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
    
    for(UIComponent uiComponent : children) {
      graphics.drawImage(
              uiComponent.getSprite(),
              uiComponent.getPosition().intX(),
              uiComponent.getPosition().intY(),
              null
      );
    }
    
    graphics.dispose();
    return image;
  }

  @Override
  public void update(State state) {
    
    children.forEach(component -> component.update(state));
    calculateSize();
    calculatePosition();    
  }
  
  
  public void addUIComponent(UIComponent uiComponent) {
    children.add(uiComponent);    
  }

  public void setBackgroundColor(Color color) {
    
    backgroundColor = color;
  }

  public void setAlignment(Alignment alignment) {
    this.alignment = alignment;
  }
  
  
  
}
