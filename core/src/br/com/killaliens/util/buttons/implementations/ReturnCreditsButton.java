package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.screens.title.TitleScreen;
import br.com.killaliens.util.buttons.Button;

public class ReturnCreditsButton extends Button {

    private static final String NAME = "";
    private static final String IMAGE_NAME = "blue_sliderLeft";

    private TitleScreen titleScreen = null;
    
    /**
     * Constructor
     * @param titleScreen TitleScreen object
     */
    public ReturnCreditsButton(TitleScreen titleScreen) {
        this.titleScreen = titleScreen;
    }
    
    @Override
    protected void action() {
        this.titleScreen.mainTitle();
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
