/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state.menu.ui;

import core.Size;
import state.State;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;
import ui.clickable.UISlider;

/**
 *
 * @author kmne6
 */
public class UIOptionMenu extends VerticalContainer {
  
  
  private UISlider musicVolumeSlider;
  private UIText musicVolumeLabel;
  
  
  public UIOptionMenu(Size windowSize) {
    super(windowSize);
    
    alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);
    
    musicVolumeSlider = new UISlider(0, 1);
    musicVolumeLabel = new UIText("");
    
    addUIComponent(new UIText("OPTIONS"));
    addUIComponent(musicVolumeLabel);
    addUIComponent(musicVolumeSlider);
    addUIComponent(new UIButton("BACK", (state) -> ((MenuState) state).enterMenu(new UIMainMenu(windowSize))));
  }
  
  
  @Override
  public void update(State state) {
    super.update(state);
    
    handleVolume(state);    
    
  }

  private void handleVolume(State state) {
    
    state.getGameSettings().getAudioSettings().setMusicVolume( (float) musicVolumeSlider.getValue() );
    musicVolumeLabel.setText(String.format("Music Volume: %d", Math.round(musicVolumeSlider.getValue() * 100) ) );
  }
  
}
