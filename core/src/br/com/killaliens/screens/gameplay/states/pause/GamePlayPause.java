package br.com.killaliens.screens.gameplay.states.pause;

import java.util.ArrayList;
import java.util.List;

import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.screens.ScreenState;
import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.PauseLabel;
import br.com.killaliens.util.buttons.implementations.QuitGamePlayButton;
import br.com.killaliens.util.buttons.implementations.ResumeButton;
import br.com.killaliens.util.optionscreen.OptionsScreen;

public class GamePlayPause extends OptionsScreen implements ScreenState {
    
    /**
     * Constructor
     * @param gamePlayScreen GamePlayScreen object
     * @param screenManager ScreenManager object
     */
    public GamePlayPause(GamePlayScreen gamePlayScreen, ScreenManager screenManager) {
        List<Button> buttons = new ArrayList<Button>();
        
        buttons.add(new QuitGamePlayButton(gamePlayScreen, screenManager));
        buttons.add(new ResumeButton(gamePlayScreen));
        buttons.add(new PauseLabel());
        
        this.setButtons(buttons);
    }
}
