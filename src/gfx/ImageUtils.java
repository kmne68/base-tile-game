/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author kmne6
 */
public class ImageUtils {
  
  public static Image loadImage(String filePath) {
    
    try {
      
      return ImageIO.read(ImageUtils.class.getResource(filePath));
      
    } catch(IOException e) {
      
      System.out.println("Could not load image from path: " + filePath);
    }
    
    return null;
  }
}
