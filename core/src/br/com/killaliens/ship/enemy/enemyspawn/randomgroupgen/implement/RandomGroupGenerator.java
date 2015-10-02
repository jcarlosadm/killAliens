package br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.implement;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.util.random.StaticRandom;

public abstract class RandomGroupGenerator {
    
    private static final int LIMIT_MIDDLE_ENEMY = 8;
    private static final int LIMIT_WEAK_ENEMY = 5;
    private static final int RANDOM_MINIMUM = 1;
    private static final int RANDOM_MAXIMUM = 10;
    
    public EnemyShip getEnemyShip(){
        int number = StaticRandom.getRandomValue(RANDOM_MINIMUM, RANDOM_MAXIMUM);
        
        if (number <= LIMIT_WEAK_ENEMY) {
            return this.getWeakEnemy();
        } else if (number <= LIMIT_MIDDLE_ENEMY) {
            return this.getMiddleEnemy();
        }
        
        return this.getStrongEnemy();
    }

    protected abstract EnemyShip getStrongEnemy();

    protected abstract EnemyShip getMiddleEnemy();

    protected abstract EnemyShip getWeakEnemy();
}
