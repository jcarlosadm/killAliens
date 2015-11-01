package br.com.killaliens.screens.gameplay.states.win;

import java.util.ArrayList;
import java.util.List;

import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.screens.ScreenState;
import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.screens.options.OptionsScreen;
import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.QuitGamePlayButton;
import br.com.killaliens.util.buttons.implementations.RestartButton;
import br.com.killaliens.util.buttons.implementations.WinLabel;

public class GamePlayWin extends OptionsScreen implements ScreenState {
    
    public GamePlayWin(GamePlayScreen gamePlayScreen, ScreenManager screenManager) {
        List<Button> buttons = new ArrayList<Button>();
        
        buttons.add(new QuitGamePlayButton(gamePlayScreen, screenManager));
        buttons.add(new RestartButton(gamePlayScreen));
        buttons.add(new WinLabel());
        
        this.setButtons(buttons);
    }
}
