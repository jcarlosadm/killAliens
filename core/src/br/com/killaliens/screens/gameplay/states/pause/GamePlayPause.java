package br.com.killaliens.screens.gameplay.states.pause;

import java.util.ArrayList;
import java.util.List;

import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.screens.options.OptionsScreen;
import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.PauseLabel;
import br.com.killaliens.util.buttons.implementations.QuitGamePlayButton;
import br.com.killaliens.util.buttons.implementations.ResumeButton;

public class GamePlayPause extends OptionsScreen {
    
    public GamePlayPause(GamePlayScreen gamePlayScreen, ScreenManager screenManager) {
        List<Button> buttons = new ArrayList<Button>();
        
        buttons.add(new QuitGamePlayButton(gamePlayScreen, screenManager));
        buttons.add(new ResumeButton(gamePlayScreen));
        buttons.add(new PauseLabel());
        
        this.setButtons(buttons);
    }
}
