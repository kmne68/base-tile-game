/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import gfx.SpriteLibrary;
import java.awt.Image;

/**
 *
 * @author kmne6
 */
public class Tile {
  
  private Image sprite;
  
  public Tile(SpriteLibrary spriteLibrary) {
    
    this.sprite = spriteLibrary.getTile("woodfloor");
  }
  
  
  public Image getSprite() {
    
    return sprite;
  }
          
  
}
