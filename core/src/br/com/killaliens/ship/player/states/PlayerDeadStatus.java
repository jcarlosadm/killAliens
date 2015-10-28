package br.com.killaliens.ship.player.states;

import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.ship.status.management.StatusManagement;
import br.com.killaliens.util.animation.AnimationTypes;

public class PlayerDeadStatus implements StatusManagement {

    private static final float TIME_FOR_DEAD = 1.5f;
    
    private float currentTimeForDead = 0f;
    
    private PlayerShip playerShip = null;
    
    private boolean setupComplete = false;
    
    public PlayerDeadStatus(PlayerShip playerShip) {
        this.playerShip = playerShip;
    }
    
    @Override
    public void setup() {
        if (this.setupComplete == false) {
            this.playerShip.resetAllStatus();
            this.playerShip.setCurrentAnimation(AnimationTypes.DEAD);
            this.setupComplete = true;
        }
    }

    @Override
    public void reset() {
        this.setupComplete = false;
    }

    @Override
    public void act(float delta) {
        this.currentTimeForDead += delta;
        if (this.currentTimeForDead >= TIME_FOR_DEAD) {
            this.playerShip.remove();
            GamePlayScreen.getInstance().gameover();
        }
        // TODO add continue option
    }

}
