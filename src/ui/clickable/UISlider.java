/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.clickable;

import core.Size;
import gfx.ImageUtils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import state.State;

/**
 *
 * @author kmne6
 */
public class UISlider extends UIClickable {
  
  
  private double value;
  private double min;
  private double max;
  
  
  public UISlider(double min, double max) {
    this.min = min;
    this.max = max;
    this.value = max;
    this.size = new Size(360, 10);
    
  }
  
  
  @Override
  protected void onClick(State state) {
    
    this.value = getValueAt(state.getInput().getCursorPosition().getX());
  }
  

  @Override
  public Image getSprite() {
    
    BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
    Graphics2D graphics = sprite.createGraphics();
    
    graphics.setColor(Color.GRAY);
    graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
    
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, getPixelsOfCurrentValue(), size.getHeight());
    
    graphics.dispose();
    return sprite;
  }
  

  private double getValueAt(double xPosition) {
    
    double positionOnSlider = xPosition - absolutePosition.getX();
    double percentage = positionOnSlider / size.getWidth();
    double range = max - min;
    
    return min + range * percentage;
    
  }

  private int getPixelsOfCurrentValue() {
    
    double range = max - min;
    double percentage = value - min;
    
    return (int) ( (percentage / range) * size.getWidth() );
    
  }

  public double getValue() {
    
    return value;
  }
  
  
  public void setValue(double value) {
    
    this.value = value;
  }
  
}
