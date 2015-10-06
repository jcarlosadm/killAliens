package br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.implement;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.factory.EnemyFactory;
import br.com.killaliens.ship.enemy.types.EnemyTypes;

public class BossEnemy extends RandomGroupGenerator {

    @Override
    protected EnemyShip getStrongEnemy() {
        return EnemyFactory.getFactory(EnemyTypes.BOSS).getShip();
    }

    @Override
    protected EnemyShip getMiddleEnemy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected EnemyShip getWeakEnemy() {
        // TODO Auto-generated method stub
        return null;
    }

}
