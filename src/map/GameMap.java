/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import core.Size;
import game.Game;
import gfx.SpriteLibrary;
import java.util.Arrays;

/**
 *
 * @author kmne6
 */
public class GameMap {
  
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
  
}
