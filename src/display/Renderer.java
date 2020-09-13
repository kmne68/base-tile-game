package display;


import game.Game;
import java.awt.Graphics;

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

  public void render(Game game, Graphics graphics) {

    game.getGameObjects().forEach(gameObject -> graphics.drawImage(
            gameObject.getSprite(),
            gameObject.getPosition().intX(),
            gameObject.getPosition().intY(),
            null
    ));

  }

}
