package br.com.killaliens.ship.player.states;

import com.badlogic.gdx.Gdx;

import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.ship.status.management.StatusManagement;
import br.com.killaliens.util.animation.AnimationTypes;

public class PlayerNormalStatus implements StatusManagement {

    private PlayerShip playerShip = null;
    
    private boolean setupComplete = false;
    
    public PlayerNormalStatus(PlayerShip playerShip) {
        this.playerShip = playerShip;
    }
    
    @Override
    public void setup() {
        if (this.setupComplete == false) {
            this.playerShip.resetAllStatus();
            this.playerShip.setCurrentAnimation(AnimationTypes.NORMAL_STATE);
            this.setupComplete = true;
        }
    }

    @Override
    public void reset() {
        this.setupComplete = false;
    }

    @Override
    public void act(float delta) {
        if (this.playerShip.isTouched()) {
            this.playerShip.setShooting(true);
            float x = Gdx.input.getX() - this.playerShip.getWidth()/2;
            float y = Gdx.graphics.getHeight()  - Gdx.input.getY()
                    + this.playerShip.getAccumulatorScrollY() - this.playerShip.getHeight()/2;
            this.playerShip.moveToLocation(x, y, delta);
        }
    }

}
