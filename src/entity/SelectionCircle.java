/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import state.State;
import gfx.ImageUtils;
import java.awt.BasicStroke;
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
    size = new Size(20, 16);
    renderOffset = new Position(size.getWidth() / 2, size.getHeight());
    collisionBoxOffset = renderOffset;
    renderOrder = 4;
    initializeSprite();
  }

  @Override
  public Image getSprite() {
    
    return parent != null ? sprite : null;
  }
  

  @Override
  public CollisionBox getCollisionBox() {
    
    Position position = getPosition();
    position.subtract(collisionBoxOffset);
    return CollisionBox.of(position, getSize());
  }
  

  private void initializeSprite() {  
    
    sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
    Graphics2D graphics = sprite.createGraphics();
    
    graphics.setColor(color);
    graphics.setStroke(new BasicStroke(2));
    graphics.drawOval(0, 0, size.getWidth(), size.getHeight());
    
    graphics.dispose();
    
  }

  
}
