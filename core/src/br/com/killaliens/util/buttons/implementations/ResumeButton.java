package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.util.buttons.Button;

public class ResumeButton extends Button {

    private static final String IMAGE_NAME = "blue_button";
    
    private static final String NAME = "Resume";
    
    private GamePlayScreen gamePlayScreen = null;
    
    public ResumeButton(GamePlayScreen gamePlayScreen) {
        this.gamePlayScreen = gamePlayScreen;
    }

    @Override
    protected void action() {
        this.gamePlayScreen.resume();
    }

    @Override
    protected String getImageName() {
        return IMAGE_NAME;
    }

    @Override
    protected String getButtonName() {
        return NAME;
    }

    @Override
    protected boolean hasSound() {
        return true;
    }
}
