package base;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import game.GameLoop;
import game.Game;

/**
 *
 * @author kmne6
 */
public class Launcher {
  
  public static void main(String[] args) {
    
    new Thread(new GameLoop(new Game(800, 600))).start();
    
    // Display display = new Display(800, 600);
  }
}
