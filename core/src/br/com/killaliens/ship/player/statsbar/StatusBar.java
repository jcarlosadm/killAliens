package br.com.killaliens.ship.player.statsbar;

import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.util.accumulatorScroll.AccumulatorScroolY;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class StatusBar extends Actor implements AccumulatorScroolY {

    private PlayerShip playerShip = null;
    
    private float accumulatorScrollY = 0f;
    
    public StatusBar(PlayerShip playerShip) {
        this.playerShip = playerShip;
        this.setup();
    }
    
    private void setup() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void addAccumulatorScrollY(float value) {
        this.accumulatorScrollY += value;
        this.setY(this.getY()+value);
    }

    @Override
    public float getAccumulatorScrollY() {
        return this.accumulatorScrollY;
    }
    
}
