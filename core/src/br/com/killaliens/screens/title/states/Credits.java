package br.com.killaliens.screens.title.states;

import br.com.killaliens.screens.ScreenState;
import br.com.killaliens.screens.title.TitleScreen;
import br.com.killaliens.screens.title.background.CreditsBackground;
import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.ReturnCreditsButton;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Credits extends Stage implements ScreenState {
    
    public Credits(TitleScreen titleScreen) {
        this.addActor(new CreditsBackground());
        
        Button returnButton = new ReturnCreditsButton(titleScreen);
        this.addActor(returnButton);
        
        returnButton.setX(50f);
        returnButton.setY(50f);
    }
    
}
