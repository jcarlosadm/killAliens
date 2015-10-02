package br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.implement;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.factory.EnemyFactory;
import br.com.killaliens.ship.enemy.types.EnemyTypes;

public class BasicEnemies extends RandomGroupGenerator {

    @Override
    protected EnemyShip getStrongEnemy() {
        // TODO Auto-generated method stub
        return EnemyFactory.getEnemyInstance(EnemyTypes.UFO);
    }

    @Override
    protected EnemyShip getMiddleEnemy() {
        // TODO Auto-generated method stub
        return EnemyFactory.getEnemyInstance(EnemyTypes.UFO);
    }

    @Override
    protected EnemyShip getWeakEnemy() {
        // TODO Auto-generated method stub
        return EnemyFactory.getEnemyInstance(EnemyTypes.UFO);
    }

}
