package br.com.killaliens.ship.enemy;

import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.ShipProperties;

public abstract class EnemyShip extends Ship {

    public EnemyShip(ShipProperties properties) {
        super(properties);
        this.setIfIsEnemy(true);
        // TODO Auto-generated constructor stub
    }

}
