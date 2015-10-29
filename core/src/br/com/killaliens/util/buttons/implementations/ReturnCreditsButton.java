package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.screens.title.TitleScreen;
import br.com.killaliens.util.buttons.Button;

public class ReturnCreditsButton extends Button {

    private static final String NAME = "";
    private static final String IMAGE_NAME = "blue_sliderLeft";

    @Override
    protected void action() {
        TitleScreen.getInstance().mainTitle();
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
