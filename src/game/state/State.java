/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state;

import core.Size;
import display.Camera;
import entity.GameObject;
import gfx.SpriteLibrary;
import input.Input;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import map.GameMap;

/**
 *
 * @author kmne6
 */
public abstract class State {  
  
  protected GameMap gameMap;
  protected List<GameObject> gameObjects;
  protected SpriteLibrary spriteLibrary;
  protected Input input;
  protected Camera camera;
  
  
  public State(Size windowSize, Input input) {
    
    this.input = input;
  
    gameObjects = new ArrayList<GameObject>();
    spriteLibrary = new SpriteLibrary();
    camera = new Camera(windowSize);
    
  }
  
  
  public void update() {    
    
    sortObjectsByPosition();
    gameObjects.forEach(gameOjbect -> gameOjbect.update());
    camera.update(this);
  }
  
  
  public List<GameObject> getGameObjects() {
    
    return gameObjects;
  }
  
  
  public Camera getCamera() {
    
    return camera;
    
  }
  
  
  public GameMap getGameMap() {
    
    return gameMap;
    
  }

  
  /**
   * Method to sort game objects by their Y position so that the player object
   * is rendered on top of other objects.
   */
  private void sortObjectsByPosition() {
    
    gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
    
    // TODO: add attribute to objects to determine whether they are rendered
    // before or after the player object
  }
  
}
