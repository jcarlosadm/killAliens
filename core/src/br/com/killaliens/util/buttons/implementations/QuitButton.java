package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.util.buttons.Button;

public class QuitButton extends Button {

    private static final String IMAGE_NAME = "blue_button";

    private static final String NAME = "Quit";

    public QuitButton(float positionX, float positionY) {
        super(positionX, positionY);
    }

    @Override
    protected void action() {
        // TODO Auto-generated method stub

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
