package br.com.killaliens.ship.enemy.types.keeper.states;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.EnemyStatus;
import br.com.killaliens.util.animation.AnimationTypes;

public class KeeperDeadStatus extends EnemyStatus {

    private static final float SECONDS_FOR_DEAD = 1.5f;
    private float currentCountForDead = 0f;
    
    public KeeperDeadStatus(EnemyShip enemyShip) {
        super(enemyShip);
    }

    @Override
    public void act(float delta) {
        this.currentCountForDead += delta;
        if (this.currentCountForDead >= SECONDS_FOR_DEAD ) {
            this.getShip().remove();
        }
    }

    @Override
    protected void specificSetup() {
        this.getShip().setCurrentAnimation(AnimationTypes.DEAD);
    }

}
