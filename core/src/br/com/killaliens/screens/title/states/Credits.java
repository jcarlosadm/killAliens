package br.com.killaliens.screens.title.states;

import br.com.killaliens.screens.title.background.CreditsBackground;
import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.ReturnCreditsButton;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Credits extends Stage {
    
    public Credits() {
        this.addActor(new CreditsBackground());
        
        Button returnButton = new ReturnCreditsButton();
        this.addActor(returnButton);
        
        returnButton.setX(50f);
        returnButton.setY(50f);
    }
    
}
