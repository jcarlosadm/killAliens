package br.com.killaliens.ship.enemy.types.crazyufo.states;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.EnemyStatus;
import br.com.killaliens.util.animation.AnimationTypes;

public class CrazyUFONormalStatus extends EnemyStatus{

    public CrazyUFONormalStatus(EnemyShip enemyShip) {
        super(enemyShip);
    }

    @Override
    public void act(float delta) {
        this.getShip().runArtificialIntelligence(delta);
    }

    @Override
    protected void specificSetup() {
        this.getShip().setCurrentAnimation(AnimationTypes.NORMAL_STATE);
    }

}
