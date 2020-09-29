/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import core.Position;
import core.Size;
import display.Camera;
import game.Game;
import gfx.SpriteLibrary;
import java.util.Arrays;

/**
 *
 * @author kmne6
 */
public class GameMap {
  
  private static final int SAFETY_SPACE = 2;
  
  private Tile[][] tiles;

  public GameMap(Size size, SpriteLibrary spriteLibrary) {
    
    tiles = new Tile[size.getWidth()][size.getHeight()];
    initializeTiles(spriteLibrary);
    
  }
  
  
  private void initializeTiles(SpriteLibrary spriteLibrary) {
    
    for(Tile[] row : tiles) {
      Arrays.fill(row, new Tile(spriteLibrary));
    }
  }
  
  
  public Tile[][] getTiles() {
    
    return tiles;
  }
  
  
  public int getWidth() {
    
    return tiles.length * Game.SPRITE_SIZE;
  }
  
  
  public int getHeight() {
    
    return tiles[0].length * Game.SPRITE_SIZE;
  }

  public Position getRandomPosition() {
    
    double x = Math.random() * tiles.length * Game.SPRITE_SIZE;
    double y = Math.random() * tiles[0].length * Game.SPRITE_SIZE;
    
    return new Position(x, y);
  }

  public Position getViewableStartingGridPosition(Camera camera) {
    return new Position(
                        Math.max(0, camera.getPosition().getX() / Game.SPRITE_SIZE - SAFETY_SPACE),
                        Math.max(0, camera.getPosition().getY() / Game.SPRITE_SIZE - SAFETY_SPACE)
                        );
  }

  public Position getViewableEndingGridPosition(Camera camera) {
    return new Position(
            Math.min(tiles.length, camera.getPosition().getX() / 
                    Game.SPRITE_SIZE + camera.getWindowSize().getWidth() / Game.SPRITE_SIZE + SAFETY_SPACE),
            Math.min(tiles[0].length, camera.getPosition().getY() / 
                    Game.SPRITE_SIZE + camera.getWindowSize().getHeight() / Game.SPRITE_SIZE + SAFETY_SPACE)   
    );
  }
  
}
