package br.com.killaliens.screens.gameplay.states.win;

import java.util.ArrayList;
import java.util.List;

import br.com.killaliens.screens.options.OptionsScreen;
import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.QuitGamePlayButton;
import br.com.killaliens.util.buttons.implementations.RestartButton;
import br.com.killaliens.util.buttons.implementations.WinLabel;

public class GamePlayWin extends OptionsScreen {
    
    public GamePlayWin() {
        List<Button> buttons = new ArrayList<Button>();
        
        buttons.add(new QuitGamePlayButton());
        buttons.add(new RestartButton());
        buttons.add(new WinLabel());
        
        this.setButtons(buttons);
    }
}
