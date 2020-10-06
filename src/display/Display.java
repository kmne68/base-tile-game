/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import game.state.State;
import input.Input;
import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

/**
 *
 * @author kmne6
 */
public class Display extends JFrame {
  
  private Canvas canvas;
  private Renderer renderer;
  private DebugRenderer debugRenderer;
  
  
  public Display(int width, int height, Input input) {
    
    setTitle("2D Game");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    
    this.renderer = new Renderer();
    this.debugRenderer = new DebugRenderer();
    
    canvas = new Canvas();
    canvas.setPreferredSize(new Dimension(width, height));
    canvas.setFocusable(false);
    add(canvas);
    addKeyListener(input);
    pack();
    
    canvas.createBufferStrategy(2); // the argument is the number of buffers
    
    setLocationRelativeTo(null); // will result in a display centered onscreen
    setVisible(true);
    
  }
  
  
  public void render(State state, boolean debugMode) {
    BufferStrategy bufferStrategy = canvas.getBufferStrategy();
    Graphics graphics = bufferStrategy.getDrawGraphics();
    
    graphics.setColor(Color.BLACK);
    graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    
    renderer.render(state, graphics);
    
    if(debugMode) {
      debugRenderer.render(state, graphics);
    }
    
    graphics.dispose();
    bufferStrategy.show();
    
  }
}
