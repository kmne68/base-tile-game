/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import display.Display;
import entity.GameObject;
import entity.Player;
import controller.PlayerController;
import gfx.SpriteLibrary;
import input.Input;
//import java.awt.*;
//import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kmne6
 */
public class Game {
  
  public static int SPRITE_SIZE = 64;
  
  private Display display;
  private List<GameObject> gameObjects;
  private Input input;
  private SpriteLibrary spriteLibrary;
  // private Rectangle rectangle;
  
  
  public Game(int width, int height) {
    
    
    input = new Input();
    display = new Display(width, height, input);
    gameObjects = new ArrayList<GameObject>();
    spriteLibrary = new SpriteLibrary();
    gameObjects.add(new Player(new PlayerController(input), spriteLibrary ) );
    
    //gameObjects.add(new Square());
    // rectangle = new Rectangle(0, 0, 50, 50);
  }
  
  
  public void update() {
    
    gameObjects.forEach(gameOjbect -> gameOjbect.update());
    // rectangle.setLocation((int) rectangle.getX() + 1, (int) rectangle.getY() );
    
  }
  
  
  public void render() {
    
    display.render(this);
  }
  
  
  public List<GameObject> getGameObjects() {
    
    return gameObjects;
  }
  
  
//  public Rectangle getRectangle() {
//    
//    return rectangle;
//  }
  
  
}
