package br.com.killaliens.ship.enemy.status;

import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.ship.enemy.EnemyShip;

public class BossDeadStatus extends EnemyDeadStatus {

    public BossDeadStatus(EnemyShip enemyShip) {
        super(enemyShip);
    }
    
    @Override
    protected void specificSetup() {
        super.specificSetup();
        GamePlayScreen.getInstance().win();
    }

}
