/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

/**
 *
 * @author kmne6
 */
public class Display extends JFrame {
  
  private Canvas canvas;
  
  public Display(int width, int height, Input input) {
    
    setTitle("2D Game");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    
    canvas = new Canvas();
    canvas.setPreferredSize(new Dimension(width, height));
    canvas.setFocusable(false);
    add(canvas);
    addKeyListener(input);
    pack();
    
    canvas.createBufferStrategy(3); // the argument is the number of buffers
    
    setLocationRelativeTo(null); // will result in a display centered onscreen
    setVisible(true);
    
  }
  
  
  public void render(Game game) {
    BufferStrategy bufferStrategy = canvas.getBufferStrategy();
    Graphics graphics = bufferStrategy.getDrawGraphics();
    
    graphics.setColor(Color.BLACK);
    graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    
    game.getGameObjects().forEach(gameObject -> graphics.drawImage(
            gameObject.getSprite(),
            gameObject.getPosition().getX(),
            gameObject.getPosition().getY(),
            null
    ) );
    
//    Rectangle rectangle = game.getRectangle();
//    graphics.setColor(Color.BLUE);
//    graphics.fillRect(
//            (int) rectangle.getX(), 
//            (int) rectangle.getY(),
//            (int) rectangle.getWidth(),
//            (int) rectangle.getHeight()
//    );
    
    graphics.dispose();
    bufferStrategy.show();
    
  }
}
