package br.com.killaliens.util.buttons.implementations;

import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.util.buttons.Button;

public class ResumeButton extends Button{

    public ResumeButton(float positionX, float positionY) {
        super(positionX, positionY);
    }

    @Override
    protected void action() {
        GamePlayScreen.getInstance().resume();
        this.setTouched(false);
    }

    @Override
    protected String getImageName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getButtonName() {
        // TODO Auto-generated method stub
        return null;
    }
}
