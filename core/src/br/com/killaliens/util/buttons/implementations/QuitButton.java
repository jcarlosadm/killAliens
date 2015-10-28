package br.com.killaliens.util.buttons.implementations;

import com.badlogic.gdx.Gdx;

import br.com.killaliens.util.buttons.Button;

public class QuitButton extends Button {

    private static final String IMAGE_NAME = "blue_button";

    private static final String NAME = "Quit";

    @Override
    protected void action() {
        Gdx.app.exit();
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
