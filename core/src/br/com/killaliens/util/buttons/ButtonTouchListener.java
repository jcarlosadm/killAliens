package br.com.killaliens.util.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ButtonTouchListener extends InputListener {
    
    private Button button = null;
    
    public ButtonTouchListener(Button button) {
        this.button = button;
    }
    
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer,
            int button) {
        this.button.setTouched(true);
        return true;
    }
    
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer,
            int button) {
        this.button.setTouched(false);
    }
}
