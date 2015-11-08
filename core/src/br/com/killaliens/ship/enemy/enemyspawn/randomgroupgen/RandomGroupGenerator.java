package br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.util.random.StaticRandom;

public abstract class RandomGroupGenerator {
    
    private static final int LIMIT_MIDDLE_ENEMY = 8;
    private static final int LIMIT_WEAK_ENEMY = 5;
    private static final int RANDOM_MINIMUM = 1;
    private static final int RANDOM_MAXIMUM = 10;
    
    /**
     * get random enemy ship instance
     * @return EnemyShip instance
     */
    public EnemyShip getEnemyShip(){
        int number = StaticRandom.getRandomValue(RANDOM_MINIMUM, RANDOM_MAXIMUM);
        
        if (number <= LIMIT_WEAK_ENEMY) {
            return this.getWeakEnemy();
        } else if (number <= LIMIT_MIDDLE_ENEMY) {
            return this.getMiddleEnemy();
        }
        
        return this.getStrongEnemy();
    }

    /**
     * Get strong enemy ship instance
     * @return EnemyShip instance
     */
    protected abstract EnemyShip getStrongEnemy();

    /**
     * Get middle enemy ship instance
     * @return EnemyShip instance
     */
    protected abstract EnemyShip getMiddleEnemy();

    /**
     * Get weak enemy ship instance
     * @return EnemyShip instance
     */
    protected abstract EnemyShip getWeakEnemy();
}
