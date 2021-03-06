package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.util.buttons.Button;

public class GameOverLabel extends Button {

    private static final String IMAGE_NAME = "grey_button";
    
    private static final String NAME = "GameOver!";
    
    @Override
    protected void action() {
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
        return false;
    }

}
