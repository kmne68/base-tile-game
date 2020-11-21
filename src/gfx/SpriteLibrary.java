/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kmne6
 */
public class SpriteLibrary {

  private Map<String, SpriteSet> spriteSets;
  private Map<String, Image> images;

  public SpriteLibrary() {

    spriteSets = new HashMap<>();
    images = new HashMap<>();
    loadSpritesFromDisk();
  }

  private void loadSpritesFromDisk() {

    loadSpriteSets("/resources/sprites/units");
    loadImages("/resources/sprites/tiles");
    loadImages("/resources/sprites/effects");

  }

  private void loadImages(String path) {

    String[] imagesInFolder = getImagesInFolder(path);

    for (String fileName : imagesInFolder) {
      images.put(
              fileName.substring(0, fileName.length() - 4),
              ImageUtils.loadImage(path + "/" + fileName));
    }
  }

  
  private void loadSpriteSets(String path) {

    String[] folderNames = getFolderNames(path);

    for (String folderName : folderNames) {
      SpriteSet spriteSet = new SpriteSet();
      String pathToFolder = path + "/" + folderName;
      String[] sheetsInFolder = getImagesInFolder(pathToFolder);

      for (String sheetName : sheetsInFolder) {
        spriteSet.addSheet(
                sheetName.substring(0, sheetName.length() - 4),
                ImageUtils.loadImage(pathToFolder + "/" + sheetName));
      }

      spriteSets.put(folderName, spriteSet);
      System.out.println("folder name: " + folderName + ", sprite set: " + spriteSet);
    }

  }

  private String[] getFolderNames(String basePath) {

    URL resource = SpriteLibrary.class.getResource(basePath);
    File file = new File(resource.getFile());

    // Pass in a method used to filter the contents to determine what to return in
    // the array of Strings. Return any that are a directory
    System.out.println("base path: " + basePath);
    return file.list((current, name) -> new File(current, name).isDirectory());
  }

  private String[] getImagesInFolder(String basePath) {

    URL resource = SpriteLibrary.class.getResource(basePath);
    File file = new File(resource.getFile());

    // Pass in a method used to filter the contents to determine what to return in
    // the array of Strings. Return any that are a directory    
    System.out.println("base path: " + basePath);
    return file.list((current, name) -> new File(current, name).isFile());

  }

  public SpriteSet getSpriteSet(String name) {

    System.out.println("name: " + spriteSets.get(name));
    return spriteSets.get(name);

  }

  public Image getImage(String name) {

    return images.get(name);

  }

}
