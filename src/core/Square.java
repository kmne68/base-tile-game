/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author kmne6
 */
public class Square extends GameObject {
  
  
  
  @Override
  public void update() {
    
    setPosition(new Position(getPosition().getX() + 1, getPosition().getY()));
    
  }
  
  
  @Override
  public Image getSprite() {
    
    BufferedImage image = new BufferedImage(getSize().getWidth(), getSize().getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();
    
    graphics.setColor(Color.blue);
    graphics.fillRect(0, 0, getSize().getWidth(), getSize().getHeight());
    
    graphics.dispose();
    
    return image;
  }
  
}
