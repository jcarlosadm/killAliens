package br.com.killaliens.ship.player;

import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.ShipProperties;

public class PlayerShip extends Ship {

    public PlayerShip(ShipProperties properties) {
        super(properties);
        this.setIfIsEnemy(false);
        // TODO Auto-generated constructor stub
    }

}
