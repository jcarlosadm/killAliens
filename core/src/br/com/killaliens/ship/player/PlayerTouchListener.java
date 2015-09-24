package br.com.killaliens.ship.player;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class PlayerTouchListener extends InputListener{
    
    private PlayerShip playerShip = null;
    
    public PlayerTouchListener(PlayerShip playerShip) {
        this.playerShip = playerShip;
    }
    
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer,
            int button) {
        this.playerShip.setTouched(true);
        return true;
    }
    
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer,
            int button) {
        this.playerShip.setTouched(false);
    }
}
