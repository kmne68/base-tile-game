/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author kmne6
 */
public class NPCController implements Controller {

  @Override
  public boolean isRequestingUp() {
    return false;
  }

  @Override
  public boolean isRequestingDown() {
    return false;
  }

  @Override
  public boolean isRequestingLeft() {
    return false;
  }

  @Override
  public boolean isRequestingRight() {
    return false;
  }
  
}
