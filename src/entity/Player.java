/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import controller.Controller;
import core.Position;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author kmne6
 */
public class Player extends MovingEntity {
  
  public Player(Controller controller) {
    super(controller);
    
  }
  
  
  @Override
  public void update() {   
    
    super.update();
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
