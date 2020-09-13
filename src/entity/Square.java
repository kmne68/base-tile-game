/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import core.Position;
import entity.GameObject;
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
    
    position = new Position(position.getX() + 1, position.getY() );
    // setPosition(new Position(getPosition().getX() + 1, getPosition().getY()));
    
  }
  
  
  @Override
  public Image getSprite() {
    
    BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();
    
    graphics.setColor(Color.blue);
    graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
    
    graphics.dispose();
    
    return image;
  }
  
}
