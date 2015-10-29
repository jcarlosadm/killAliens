package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.screens.ScreenType;
import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.util.buttons.Button;

public class PlayButton extends Button {

    private static final String IMAGE_NAME = "blue_button";
    private static final String NAME = "Play";
    
    @Override
    protected void action() {
        ScreenManager.getInstance().changeCurrentScreen(ScreenType.GAMEPLAY_SCREEN);
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
