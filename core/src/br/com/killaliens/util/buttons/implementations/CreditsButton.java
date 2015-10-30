package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.screens.title.TitleScreen;
import br.com.killaliens.util.buttons.Button;

public class CreditsButton extends Button {

    private static final String IMAGE_NAME = "blue_button";
    private static final String NAME = "Credits";
    
    private TitleScreen titleScreen = null;

    public CreditsButton(TitleScreen titleScreen) {
        this.titleScreen = titleScreen;
    }
    
    @Override
    protected void action() {
        this.titleScreen.credits();
    }

    @Override
    protected String getImageName() {
        return IMAGE_NAME;
    }

    @Override
    protected String getButtonName() {
        return NAME;
    }

}
