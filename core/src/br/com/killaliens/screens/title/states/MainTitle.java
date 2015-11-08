package br.com.killaliens.screens.title.states;

import java.util.ArrayList;
import java.util.List;

import br.com.killaliens.screens.ScreenState;
import br.com.killaliens.screens.title.TitleScreen;
import br.com.killaliens.screens.title.background.MainTitleBackground;
import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.CreditsButton;
import br.com.killaliens.util.buttons.implementations.PlayButton;
import br.com.killaliens.util.buttons.implementations.QuitMainTitleButton;
import br.com.killaliens.util.optionscreen.OptionsScreen;

public class MainTitle extends OptionsScreen implements ScreenState {
    
    public MainTitle(TitleScreen titleScreen) {
        this.addActor(new MainTitleBackground());
        
        List<Button> buttons = new ArrayList<Button>();
        
        buttons.add(new QuitMainTitleButton());
        buttons.add(new CreditsButton(titleScreen));
        buttons.add(new PlayButton(titleScreen));
        
        this.setButtons(buttons);
    }
    
}
