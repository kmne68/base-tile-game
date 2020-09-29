/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import core.Size;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author kmne6
 */
public class ImageUtils {
  
  public static final int ALPHA_OPAQUE = 1;
  public static final int ALPHA_BIT_MASKED = 2;
  public static final int ALPHA_BLEND = 3;
  
  public static Image loadImage(String filePath) {    
    try {      
      Image imageFromDisk = ImageIO.read(ImageUtils.class.getResource(filePath));
      BufferedImage compatibleImage = (BufferedImage)
              createCompatibleImage(
                      new Size(imageFromDisk.getWidth(null),
                              imageFromDisk.getHeight(null)),
                      ALPHA_BIT_MASKED
              );
      Graphics2D graphics = compatibleImage.createGraphics();
      graphics.drawImage(imageFromDisk, 0, 0, null);
      graphics.dispose();
      
      return compatibleImage;
      
    } catch(IOException e) {
      
      System.out.println("Could not load image from path: " + filePath);
    }
    
    return null;
  }
  
  public static Image createCompatibleImage(Size size, int transparency) {
    
    GraphicsConfiguration graphicsConfiguration = 
            GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .getDefaultConfiguration();
    
    return graphicsConfiguration.createCompatibleImage(size.getWidth(), size.getHeight(), transparency);
  }
}
