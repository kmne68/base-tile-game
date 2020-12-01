/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kmne6
 */
public class SpriteSet {
  
  private Map<String, Image> animationSheets;
  
  
  public SpriteSet() {
    
    this.animationSheets = new HashMap<>();
  }
  
  
  public SpriteSet(Image image) {
    
    this.animationSheets = new HashMap<>();
    addSheet("default", image);
  }
  
  
  public void addSheet(String name, Image animationSheet) {
    
    animationSheets.put(name, animationSheet);
    
  }
  
  
  public Image getOrGetDefault(String name) {
    
    if(animationSheets.containsKey(name) ) {
      return animationSheets.get(name);
    }
    
    return animationSheets.get("default");
    
  }
}
