package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.util.buttons.Button;

public class RestartButton extends Button{

    private static final String IMAGE_NAME = "blue_button";
    private static final String NAME = "Restart";

    @Override
    protected void action() {
        GamePlayScreen.getInstance().reset();
        GamePlayScreen.getInstance().resume();
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
