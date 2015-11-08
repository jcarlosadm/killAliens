package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.util.buttons.Button;

public class RestartButton extends Button{

    private static final String IMAGE_NAME = "blue_button";
    private static final String NAME = "Restart";

    private GamePlayScreen gamePlayScreen = null;
    
    /**
     * Constructor
     * @param gamePlayScreen GamePlayScreen object
     */
    public RestartButton(GamePlayScreen gamePlayScreen) {
        this.gamePlayScreen = gamePlayScreen;
    }
    
    @Override
    protected void action() {
        this.gamePlayScreen.reset();
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
