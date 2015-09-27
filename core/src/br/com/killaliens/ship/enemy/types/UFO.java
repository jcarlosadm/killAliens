package br.com.killaliens.ship.enemy.types;

import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;

public class UFO extends EnemyShip {

    public UFO(ShipProperties properties) {
        super(properties);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void runArtificialIntelligence() {
        // TODO Auto-generated method stub
        this.rotateBy(1f);
        this.setShooting(true);
    }
    
    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
        super.act(delta);
        this.runArtificialIntelligence();
    }

}
