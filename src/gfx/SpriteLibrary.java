/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import game.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kmne6
 */
public class SpriteLibrary {
  
  private final static String PATH_TO_UNITS = "/resources/sprites/units";
  
  private Map<String, SpriteSet> units;
  private Map<String, Image> tiles;
  
  public SpriteLibrary() {
    
    units = new HashMap<>();
    tiles = new HashMap<>();
    loadSpritesFromDisk();
  }

  private void loadSpritesFromDisk() {
    
    loadUnits();
    loadTiles();

  }  
  

  private void loadTiles() {
    
    BufferedImage image = new BufferedImage(Game.SPRITE_SIZE, Game.SPRITE_SIZE, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();
    
    graphics.setColor(Color.red);
    graphics.drawRect(0, 0, Game.SPRITE_SIZE, Game.SPRITE_SIZE);
    
    graphics.dispose();
    tiles.put("default", image);
    
  }  
  
  
  
  private void loadUnits() {
    
    String[] folderNames = getFolderNames(PATH_TO_UNITS);
    
    for(String folderName: folderNames) {      
      SpriteSet spriteSet = new SpriteSet();
      String pathToFolder = PATH_TO_UNITS + "/" + folderName;
      String[] sheetsInFolder = getSheetsInFolder(pathToFolder);
      
      for(String sheetName : sheetsInFolder ) {        
        spriteSet.addSheet(
                sheetName.substring(0, sheetName.length() - 4),
                ImageUtils.loadImage(pathToFolder + "/" + sheetName ) );
      }
      
      units.put(folderName, spriteSet);
      System.out.println("folder name: " + folderName + ", sprite set: " + spriteSet);
    }
    
  }
  

  private String[] getFolderNames(String basePath) {
    
    URL resource = SpriteLibrary.class.getResource(basePath);
    File file = new File(resource.getFile());
    
    // Pass in a method used to filter the contents to determine what to return in
    // the array of Strings. Return any that are a directory
    
    System.out.println("base path: " + basePath);
    return file.list( ( current, name) -> new File(current, name).isDirectory() );
  }

  private String[] getSheetsInFolder(String basePath) {
    
    URL resource = SpriteLibrary.class.getResource(basePath);
    File file = new File(resource.getFile());
    
    // Pass in a method used to filter the contents to determine what to return in
    // the array of Strings. Return any that are a directory    
    
    System.out.println("base path: " + basePath);
    return file.list( ( current, name) -> new File(current, name).isFile() );
    
  }

  public SpriteSet getUnit(String name) {
    
    System.out.println("name: " + units.get(name));
    return units.get(name);
    
  }
  
  
  public Image getTile(String name) {
    
    return tiles.get(name);
    
  }

  
}
