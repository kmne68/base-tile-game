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

  private Map<String, SpriteSet> units;
  private Map<String, Image> tiles;

  public SpriteLibrary() {

    units = new HashMap<>();
    tiles = new HashMap<>();
    loadSpritesFromDisk();
  }

  private void loadSpritesFromDisk() {

    loadUnits("/resources/sprites/units");
    loadTiles("/resources/sprites/tiles");

  }

  private void loadTiles(String path) {

    String[] imagesInFolder = getImagesInFolder(path);

    for (String fileName : imagesInFolder) {
      tiles.put(
              fileName.substring(0, fileName.length() - 4),
              ImageUtils.loadImage(path + "/" + fileName));
    }

  }

  private void loadUnits(String path) {

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

  public SpriteSet getUnit(String name) {

    System.out.println("name: " + units.get(name));
    return units.get(name);

  }

  public Image getTile(String name) {

    return tiles.get(name);

  }

}
