/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import core.Size;
import game.state.State;
import gfx.ImageUtils;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author kmne6
 */
public class UIText extends UIComponent {
  
  private String text;
  private int fontSize;
  private int fontStyle;
  private String fontFamily;
  private Color color;
  
  private boolean dropShadow;
  private int dropShadowOffset;
  private Color shadowColor;
  
  private Font font;

  public UIText(String text) {
    this.text = text;
    this.fontSize = 24;
    this.fontStyle = Font.PLAIN;
    this.fontFamily = "joystix monospace";
    this.color = Color.WHITE;
    
    this.dropShadow = true;
    this.dropShadowOffset = 2;
    this.shadowColor = new Color(140, 148, 140);

  }
  
  
  

  @Override
  public Image getSprite() {
    BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
    Graphics2D graphics = image.createGraphics();
    graphics.setFont(font);
    
    if(dropShadow) {
      graphics.setColor(shadowColor);
      graphics.drawString(text, padding.getLeft() + dropShadowOffset, fontSize + padding.getBottom() + dropShadowOffset);
    }
    
    graphics.setColor(color);
    graphics.drawString(text, padding.getLeft(), fontSize + padding.getTop());
    
    graphics.dispose();
    return image;
    
  }
  

  @Override
  public void update(State state) {
    createFont();
    calculateSize();
  }

  private void createFont() {
    font = new Font(fontFamily, fontStyle, fontSize);
  }

  private void calculateSize() {
    FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
    size = new Size(fontMetrics.stringWidth(text) + padding.getHorizontal(),
                    fontMetrics.getHeight() + padding.getVertical()
    );
  }
  
}
