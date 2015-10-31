package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.screens.ScreenType;
import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.util.buttons.Button;

public class QuitGamePlayButton extends Button {

    private static final String IMAGE_NAME = "blue_button";
    private static final String NAME = "Quit";
    
    private GamePlayScreen gamePlayScreen = null;
    
    private ScreenManager screenManager = null;

    public QuitGamePlayButton(GamePlayScreen gamePlayScreen, ScreenManager screenManager) {
        this.gamePlayScreen = gamePlayScreen;
        this.screenManager = screenManager;
    }
    
    @Override
    protected void action() {
        this.gamePlayScreen.reset();
        this.screenManager.changeCurrentScreen(ScreenType.TITLE_SCREEN);
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
