/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import core.CollisionBox;
import core.Size;
import game.state.State;
import gfx.ImageUtils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author kmne6
 */
public class SelectionCircle extends GameObject {
  
  private Color color;
  private BufferedImage sprite;
  

  @Override
  public void update(State state) {
    
    color = Color.ORANGE;
    size = new Size(32, 16);
    initializeSprite();
  }

  @Override
  public Image getSprite() {
    return sprite;
  }

  @Override
  public CollisionBox getCollisionBox() {
    return CollisionBox.of(position, size);
  }

  private void initializeSprite() {  
    
    sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
    Graphics2D graphics = sprite.createGraphics();
    
    graphics.setColor(color);
    graphics.fillOval(0, 0, size.getWidth(), size.getHeight());
    
    graphics.dispose();
    
  }

  
}
