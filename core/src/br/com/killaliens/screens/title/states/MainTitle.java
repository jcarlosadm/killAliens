package br.com.killaliens.screens.title.states;

import java.util.ArrayList;
import java.util.List;

import br.com.killaliens.screens.options.OptionsScreen;
import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.CreditsButton;
import br.com.killaliens.util.buttons.implementations.PlayButton;
import br.com.killaliens.util.buttons.implementations.QuitMainTitleButton;

public class MainTitle extends OptionsScreen {
    
    public MainTitle() {
        List<Button> buttons = new ArrayList<Button>();
        
        buttons.add(new QuitMainTitleButton());
        buttons.add(new CreditsButton());
        buttons.add(new PlayButton());
        
        this.setButtons(buttons);
    }
    
}
