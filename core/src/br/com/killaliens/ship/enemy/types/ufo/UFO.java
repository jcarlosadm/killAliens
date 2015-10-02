package br.com.killaliens.ship.enemy.types.ufo;

import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.types.ufo.states.UFODeadStatus;
import br.com.killaliens.ship.enemy.types.ufo.states.UFONormalStatus;
import br.com.killaliens.util.animation.AnimationTypes;

public class UFO extends EnemyShip {

    private static final float ROTATION_SPEED = 1f;

    public UFO(ShipProperties properties) {
        super(properties);
        
        this.addStatus(AnimationTypes.NORMAL_STATE, new UFONormalStatus(this));
        this.addStatus(AnimationTypes.DEAD, new UFODeadStatus(this));
        this.setCurrentStatus(AnimationTypes.NORMAL_STATE);
    }

    @Override
    public void runArtificialIntelligence(float delta) {
        this.rotateBy(ROTATION_SPEED);
        this.setShooting(true);

        this.setX(this.getX() + this.getSpeedX());
        this.setY(this.getY() + this.getSpeedY());
        this.moveToLocation(this.getX(), this.getY(), delta);
    }
}
