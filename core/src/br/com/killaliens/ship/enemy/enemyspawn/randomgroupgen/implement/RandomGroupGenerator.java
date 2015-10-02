package br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.implement;

import java.util.Random;

import br.com.killaliens.ship.enemy.EnemyShip;

public abstract class RandomGroupGenerator {
    
    private static final int LIMIT_MIDDLE_ENEMY = 8;
    private static final int LIMIT_WEAK_ENEMY = 5;
    private static final int RANDOM_MINIMUM = 1;
    private static final int RANDOM_MAXIMUM = 10;
    
    public EnemyShip getEnemyShip(){
        Random rnd = new Random();
        int number = rnd.nextInt(RANDOM_MAXIMUM - RANDOM_MINIMUM) + RANDOM_MINIMUM;
        
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
