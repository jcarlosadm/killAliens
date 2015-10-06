package br.com.killaliens.ship.enemy.types.ufo;

import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;

public class UFO extends EnemyShip {

    private static final float ROTATION_SPEED = 1f;

    public UFO(ShipProperties properties) {
        super(properties);
    }

    @Override
    public void runArtificialIntelligence(float delta) {
        this.rotateBy(ROTATION_SPEED);
        this.setShooting(true);

        this.setX(this.getX() + this.getSpeedX());
        this.setY(this.getY() + this.getSpeedY());
    }
}
