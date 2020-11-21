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
    System.out.println("Begin SpriteSet constructor");
    this.animationSheets = new HashMap<>();
    addSheet("default", image);
    System.out.println("Leaving SpriteSet constructor");
  }
  
  
  
  public void addSheet(String name, Image animationSheet) {
    System.out.println("SpriteSet.addSheet name: " + name);
    animationSheets.put(name, animationSheet);
    
  }
  
  
  public Image getOrGetDefault(String name) {
    
    System.out.println("SpriteSheet.getOrGetDefault() name: " + name);
    
    if(animationSheets.containsKey(name)) {
      System.out.println("animationSheets.containsKey(name): " + animationSheets.containsKey(name));
      return animationSheets.get(name);
    }
    
    return animationSheets.get("default");
  }
}
