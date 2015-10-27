package br.com.killaliens.screens.gamescreen.userinterface.pause;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class PauseTouchListener extends InputListener {
    
    private PauseButton pauseButton = null;
    
    public PauseTouchListener(PauseButton pauseButton) {
        this.pauseButton = pauseButton;
    }
    
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer,
            int button) {
        this.pauseButton.setTouched(true);
        return true;
    }
    
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer,
            int button) {
        this.pauseButton.setTouched(false);
    }
}
