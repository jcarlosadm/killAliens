package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.screens.title.TitleScreen;
import br.com.killaliens.util.buttons.Button;

public class PlayButton extends Button {

    private static final String IMAGE_NAME = "blue_button";
    private static final String NAME = "Play";
    
    private TitleScreen titleScreen = null;
    
    /**
     * Constructor
     * @param titleScreen TitleScreen object
     */
    public PlayButton(TitleScreen titleScreen) {
        this.titleScreen = titleScreen;
    }
    
    @Override
    protected void action() {
        this.titleScreen.startGame();
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
