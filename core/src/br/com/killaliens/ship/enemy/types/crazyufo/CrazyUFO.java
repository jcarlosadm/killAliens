package br.com.killaliens.ship.enemy.types.crazyufo;

import java.util.Random;

import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.types.crazyufo.states.CrazyUFODeadStatus;
import br.com.killaliens.ship.enemy.types.crazyufo.states.CrazyUFONormalStatus;
import br.com.killaliens.util.animation.AnimationTypes;

public class CrazyUFO extends EnemyShip {

    private static final float INITIAL_ROTATION_SPEED = 2f;
    private static final float LIMIT_TIME_TO_CHANGE_ROTATION = 3f;
    private static final float LIMIT_TIME_TO_CHANGE_SPEEDS = 2f;
    
    private float currentTimeToChangeRotationDirection = 0f;
    private float currentTimeToChangeSpeeds = 0f;
    private float rotationSpeed = INITIAL_ROTATION_SPEED;
    
    public CrazyUFO(ShipProperties properties) {
        super(properties);
        
        this.addStatus(AnimationTypes.NORMAL_STATE, new CrazyUFONormalStatus(this));
        this.addStatus(AnimationTypes.DEAD, new CrazyUFODeadStatus(this));
        this.setCurrentStatus(AnimationTypes.NORMAL_STATE);
    }

    @Override
    public void runArtificialIntelligence(float delta) {
        this.setShooting(true);
        this.currentTimeToChangeRotationDirection += delta;
        this.currentTimeToChangeSpeeds += delta;
        
        changeRotation();
        changeSpeeds();
        
        move(delta);
        this.rotateBy(this.rotationSpeed);
    }

    private void move(float delta) {
        this.setX(this.getX() + this.getSpeedX());
        this.setY(this.getY() + this.getSpeedY());
        this.moveToLocation(this.getX(), this.getY(), delta);
    }

    private void changeSpeeds() {
        if (this.currentTimeToChangeSpeeds >= LIMIT_TIME_TO_CHANGE_SPEEDS) {
            this.setSpeedX((new Random()).nextInt(4) - 2f);
            this.setSpeedY((new Random()).nextInt(4) - 2f);
            this.currentTimeToChangeSpeeds = 0;
        }
    }

    private void changeRotation() {
        if (this.currentTimeToChangeRotationDirection >= LIMIT_TIME_TO_CHANGE_ROTATION) {
            this.rotationSpeed = (new Random()).nextInt(4) - 2;
            this.currentTimeToChangeRotationDirection = 0;
        }
    }
}
