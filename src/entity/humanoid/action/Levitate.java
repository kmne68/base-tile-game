/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.humanoid.action;

import entity.humanoid.Humanoid;
import state.State;

/**
 *
 * @author kmne6
 */
public class Levitate extends Action {

    public Levitate() {
        interruptable = false;
    }

    @Override
    public void update(State state, Humanoid humanoid) {

    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String getAnimationName() {
        return "levitate";
    }
  
}
