package br.com.killaliens.screens.gameplay.states.gameover;

import java.util.ArrayList;
import java.util.List;

import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.screens.options.OptionsScreen;
import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.GameOverLabel;
import br.com.killaliens.util.buttons.implementations.QuitGamePlayButton;
import br.com.killaliens.util.buttons.implementations.RestartButton;

public class GamePlayOver extends OptionsScreen{
    public GamePlayOver(GamePlayScreen gamePlayScreen, ScreenManager screenManager) {
        List<Button> buttons = new ArrayList<Button>();
        
        buttons.add(new QuitGamePlayButton(gamePlayScreen, screenManager));
        buttons.add(new RestartButton(gamePlayScreen));
        buttons.add(new GameOverLabel());
        
        this.setButtons(buttons);
    }
}
