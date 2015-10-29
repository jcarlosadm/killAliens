package br.com.killaliens.screens.gameplay.states.pause;

import java.util.ArrayList;
import java.util.List;

import br.com.killaliens.screens.options.OptionsScreen;
import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.PauseLabel;
import br.com.killaliens.util.buttons.implementations.QuitGamePlayButton;
import br.com.killaliens.util.buttons.implementations.ResumeButton;

public class GamePlayPause extends OptionsScreen {
    
    public GamePlayPause() {
        List<Button> buttons = new ArrayList<Button>();
        
        buttons.add(new QuitGamePlayButton());
        buttons.add(new ResumeButton());
        buttons.add(new PauseLabel());
        
        this.setButtons(buttons);
    }
}
