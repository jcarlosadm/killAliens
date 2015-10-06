package br.com.killaliens.ship.enemy.types;

import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.util.random.StaticRandom;

public class CrazyUFO extends EnemyShip {

    private static final float INITIAL_ROTATION_SPEED = 2f;
    private static final float LIMIT_TIME_TO_CHANGE_ROTATION = 3f;
    private static final float LIMIT_TIME_TO_CHANGE_SPEEDS = 2f;
    private static final int MIN_RANDOM_SPEED_X = -2;
    private static final int MAX_RANDOM_SPEED_X = 2;
    private static final int MIN_RANDOM_SPEED_Y = -2;
    private static final int MAX_RANDOM_SPEED_Y = 2;
    private static final int MIN_RANDOM_ROTATION = -3;
    private static final int MAX_RANDOM_ROTATION = 3;
    
    private float currentTimeToChangeRotationDirection = 0f;
    private float currentTimeToChangeSpeeds = 0f;
    private float rotationSpeed = INITIAL_ROTATION_SPEED;
    
    public CrazyUFO(ShipProperties properties) {
        super(properties);
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
    }

    private void changeSpeeds() {
        if (this.currentTimeToChangeSpeeds >= LIMIT_TIME_TO_CHANGE_SPEEDS) {
            this.setSpeedX(StaticRandom.getRandomValue(MIN_RANDOM_SPEED_X, MAX_RANDOM_SPEED_X));
            this.setSpeedY(StaticRandom.getRandomValue(MIN_RANDOM_SPEED_Y, MAX_RANDOM_SPEED_Y));
            
            this.currentTimeToChangeSpeeds = 0;
        }
    }

    private void changeRotation() {
        if (this.currentTimeToChangeRotationDirection >= LIMIT_TIME_TO_CHANGE_ROTATION) {
            this.rotationSpeed = StaticRandom.
                    getRandomValue(MIN_RANDOM_ROTATION, MAX_RANDOM_ROTATION);
            this.currentTimeToChangeRotationDirection = 0;
        }
    }
}
