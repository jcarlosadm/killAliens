package br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.factory.EnemyFactory;
import br.com.killaliens.ship.enemy.types.EnemyTypes;

public class ExpertEnemies extends RandomGroupGenerator {

    @Override
    protected EnemyShip getStrongEnemy() {
        return EnemyFactory.getFactory(EnemyTypes.CANNON).getShip();
    }

    @Override
    protected EnemyShip getMiddleEnemy() {
        return EnemyFactory.getFactory(EnemyTypes.CRAZY_UFO).getShip();
    }

    @Override
    protected EnemyShip getWeakEnemy() {
        return EnemyFactory.getFactory(EnemyTypes.UFO).getShip();
    }

}
