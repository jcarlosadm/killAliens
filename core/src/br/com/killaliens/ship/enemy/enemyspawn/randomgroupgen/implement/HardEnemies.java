package br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.implement;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.factory.EnemyFactory;
import br.com.killaliens.ship.enemy.types.EnemyTypes;

public class HardEnemies extends RandomGroupGenerator {

    @Override
    protected EnemyShip getStrongEnemy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected EnemyShip getMiddleEnemy() {
        return EnemyFactory.getFactory(EnemyTypes.CANNON).getShip();
    }

    @Override
    protected EnemyShip getWeakEnemy() {
        return EnemyFactory.getFactory(EnemyTypes.CRAZY_UFO).getShip();
    }

}
