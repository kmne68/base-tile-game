/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import audio.AudioPlayer;
import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import game.Game;
import game.Time;
import gfx.SpriteLibrary;
import input.Input;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import map.GameMap;
import ui.UIContainer;

/**
 *
 * @author kmne6
 */
public abstract class State {  
  
  protected AudioPlayer audioPlayer;
  protected GameMap gameMap;
  protected List<GameObject> gameObjects;
  protected List<UIContainer> uiContainers;
  protected SpriteLibrary spriteLibrary;
  protected Input input;
  protected Camera camera;
  private Time time;
  private State nextState;
  protected Size windowSize;
  
  
  public State(Size windowSize, Input input) {
    
    this.windowSize = windowSize;
    this.input = input;
  
    audioPlayer = new AudioPlayer();
    gameObjects = new ArrayList<>();
    uiContainers = new ArrayList<>();
    spriteLibrary = new SpriteLibrary();
    camera = new Camera(windowSize);
    time = new Time();
    
  }
  
  
  public void update(Game game) { 
    time.update();
    sortObjectsByPosition();
    updateGameObjects();
    List.copyOf(uiContainers).forEach(uiContainer -> uiContainer.update(this));
    camera.update(this);
    handleMouseInput();
    
    if(nextState != null) {
      game.enterState(nextState);
    }
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
    
    gameObjects.sort(Comparator.comparing(GameObject::getRenderOrder).thenComparing(gameObject -> gameObject.getPosition().getY()));
    
    // TODO: add attribute to objects to determine whether they are rendered
    // before or after the player object
  }

  public Time getTime() {
    return time;
  }

  public Position getRandomPosition() {
    
    return gameMap.getRandomPosition();
  }
  

  public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
    
    return gameObjects.stream()
            .filter(other -> other.collidesWith(gameObject))
            .collect(Collectors.toList());
  }
  

  public List<UIContainer> getUiContainers() {
    return uiContainers;
  }
  
  
  public <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> aClass) {
    
    return gameObjects.stream()
            .filter(aClass::isInstance)
            .map(gameObject -> (T) gameObject)
            .collect(Collectors.toList());
  }
  

  public SpriteLibrary getSpriteLibrary() {
    return spriteLibrary;
  }

  
  public void spawn(GameObject gameObject) {
    
    gameObjects.add(gameObject);
  }
  

  private void updateGameObjects() {
    
    for(int i = 0; i < gameObjects.size(); i++) {
      gameObjects.get(i).update(this);      
    }
  }

  private void handleMouseInput() {
    
    if(input.isMouseClicked()) {
      System.out.println(String.format("Mouse clicked at x: %d, y: %d", input.getCursorPosition().intX(), input.getCursorPosition().intY() ) );
    }
    input.clearMouseClick();
  }

  public Input getInput() {
    
    return input;
  }

  public void setNextState(State nextState) {
    this.nextState = nextState;
  }
  
  
}
