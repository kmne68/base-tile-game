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
public class Player extends GameObject {
  
  private Controller controller;
  
  
  public Player(Controller controller) {
    super();
    
    this.controller = controller;
    
  }
  
  
  @Override
  public void update() {
    
    int deltaX = 0;
    int deltaY = 0;
    
    if(controller.isRequestingUp() ) {
      deltaY--;
    }    
    if(controller.isRequestingDown() ) {
      deltaY++;
    }    
    if(controller.isRequestingLeft() ) {
      deltaX--;
    }    
    if(controller.isRequestingRight() ) {
      deltaX++;
    }    
    
    position = new Position(position.getX() + deltaX, position.getY() + deltaY);
    
    
  }
  
  
  @Override
  public Image getSprite() {
    
    BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();
    
    graphics.setColor(Color.blue);
    graphics.fillRect(0, 0, getSize().getWidth(), getSize().getHeight());
    
    graphics.dispose();
    
    return image;
  }
  
}
