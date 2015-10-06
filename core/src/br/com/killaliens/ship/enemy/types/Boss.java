package br.com.killaliens.ship.enemy.types;

import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;

public class Boss extends EnemyShip {

    public Boss(ShipProperties properties) {
        super(properties);
    }

    @Override
    public void runArtificialIntelligence(float delta) {
        // TODO Auto-generated method stub
        this.setShooting(true);
    }

}
