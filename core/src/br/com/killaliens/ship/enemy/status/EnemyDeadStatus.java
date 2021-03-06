package br.com.killaliens.ship.enemy.status;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.EnemyStatus;
import br.com.killaliens.util.animation.AnimationTypes;

public class EnemyDeadStatus extends EnemyStatus {

    private static final float LIMIT_TIME_TO_DEAD = 1.5f;
    private float currentTime = 0;
    
    /**
     * Constructor
     * @param enemyShip enemy ship with this status
     */
    public EnemyDeadStatus(EnemyShip enemyShip) {
        super(enemyShip);
    }

    @Override
    public void act(float delta) {
        this.currentTime += delta;
        if (this.currentTime >= LIMIT_TIME_TO_DEAD) {
            this.getShip().remove();
        }
    }

    @Override
    protected void specificSetup() {
        this.getShip().setCurrentAnimation(AnimationTypes.DEAD);
    }

}
