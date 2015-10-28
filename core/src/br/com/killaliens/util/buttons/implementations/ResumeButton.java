package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.util.buttons.Button;

public class ResumeButton extends Button {

    private static final String IMAGE_NAME = "blue_button";
    
    private static final String NAME = "Resume";
    
    public ResumeButton(float positionX, float positionY) {
        super(positionX, positionY);
    }

    @Override
    protected void action() {
        GamePlayScreen.getInstance().resume();
        //this.setTouched(false);
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
