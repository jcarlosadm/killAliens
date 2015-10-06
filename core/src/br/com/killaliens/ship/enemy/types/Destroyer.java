package br.com.killaliens.ship.enemy.types;

import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;

public class Destroyer extends EnemyShip{

    private static final float SPEED_Y_MOVE_UP = 2f;
    private static final float SPEED_Y_MOVE_DOWN = 0f;
    private static final float TIME_TO_UP_IN_SECONDS = 3f;
    private static final float TIME_TO_DOWN_IN_SECONDS = 4f;
    
    private boolean moveDown = true;
    private float accumulatorMove = 0f;
    
    public Destroyer(ShipProperties properties) {
        super(properties);
        this.getTopAmmunition().addLevel();
        this.getTopAmmunition().addLevel();
        this.getTopAmmunition().addLevel();
        this.getTopAmmunition().addLevel();
        this.getTopAmmunition().addLevel();
        this.getTopAmmunition().addLevel();
    }

    @Override
    public void runArtificialIntelligence(float delta) {
        
        this.accumulatorMove += delta;
        if (this.moveDown && this.accumulatorMove >= TIME_TO_DOWN_IN_SECONDS) {
            this.accumulatorMove = 0f;
            this.moveDown = false;
            this.setSpeedY(SPEED_Y_MOVE_UP);
        }
        else if (this.moveDown == false && this.accumulatorMove >= TIME_TO_UP_IN_SECONDS) {
            this.accumulatorMove = 0f;
            this.moveDown = true;
            this.setSpeedY(SPEED_Y_MOVE_DOWN);
        }
        
        this.setY(this.getY() + this.getSpeedY());
        this.setShooting(true);
    }

}
