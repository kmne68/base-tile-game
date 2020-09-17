package display;

import game.Game;
import game.state.State;
import java.awt.Graphics;
import map.Tile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kmne6
 */
public class Renderer {

  public void render(State state, Graphics graphics) {

    renderMap(state, graphics);
    Camera camera = state.getCamera();
    
    state.getGameObjects().forEach(gameObject -> graphics.drawImage(
            gameObject.getSprite(),
            
            // the following values are corrected for the size of the game object's height and width
            gameObject.getPosition().intX() - camera.getPosition().intX() - gameObject.getSize().getWidth() / 2,
            gameObject.getPosition().intY() - camera.getPosition().intY() - gameObject.getSize().getHeight() / 2,
            null
    ));

  }
  

  private void renderMap(State state, Graphics graphics) {
    
    Tile[][] tiles = state.getGameMap().getTiles();
    Camera camera = state.getCamera();
    
    for(int x = 0; x < tiles.length; x++) {
      for(int y = 0; y < tiles[0].length; y++) {
        graphics.drawImage(
                tiles[x][y].getSprite(),
                x * Game.SPRITE_SIZE - camera.getPosition().intX(),
                y * Game.SPRITE_SIZE - camera.getPosition().intY(),
                null
        );
      }
    }
    
  }

}
