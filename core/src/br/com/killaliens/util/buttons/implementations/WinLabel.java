package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.util.buttons.Button;

public class WinLabel extends Button {

    private static final String IMAGE_NAME = "grey_button";
    private static final String NAME = "You Win!";
    
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

}
