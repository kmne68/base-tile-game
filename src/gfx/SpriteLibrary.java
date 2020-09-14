/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

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
  
  public SpriteLibrary() {
    
    units = new HashMap<>();
    loadSpritesFromDisk();
  }

  private void loadSpritesFromDisk() {
    
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
    }
  }

  private String[] getFolderNames(String basePath) {
    
    URL resource = SpriteLibrary.class.getResource(basePath);
    File file = new File(resource.getFile());
    
    // Pass in a method used to filter the contents to determine what to return in
    // the array of Strings. Return any that are a directory
    return file.list( ( current, name) -> new File(current, name).isDirectory() );
  }

  private String[] getSheetsInFolder(String basePath) {
    
    URL resource = SpriteLibrary.class.getResource(basePath);
    File file = new File(resource.getFile());
    
    // Pass in a method used to filter the contents to determine what to return in
    // the array of Strings. Return any that are a directory
    return file.list( ( current, name) -> new File(current, name).isFile() );
    
  }
  
}
