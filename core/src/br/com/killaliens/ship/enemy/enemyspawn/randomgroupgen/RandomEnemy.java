package br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen;

import java.util.HashMap;
import java.util.Map;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.enemyspawn.EnemySpawnLevel;

public class RandomEnemy {
    
    private Map<EnemySpawnLevel, RandomGroupGenerator> rndGroup = 
            new HashMap<EnemySpawnLevel, RandomGroupGenerator>();
    
    /**
     * Constructor
     */
    public RandomEnemy() {
        this.rndGroup.put(EnemySpawnLevel.BASIC, new BasicEnemies());
        this.rndGroup.put(EnemySpawnLevel.EXPERT, new ExpertEnemies());
        this.rndGroup.put(EnemySpawnLevel.HARD, new HardEnemies());
        this.rndGroup.put(EnemySpawnLevel.BOSS, new BossEnemy());
    }
    
    /**
     * get random enemy ship
     * @param esLevel level difficult of the enemy ship to get
     * @return EnemyShip instance
     */
    public EnemyShip getRandomEnemyShip(EnemySpawnLevel esLevel){
        return this.rndGroup.get(esLevel).getEnemyShip();
    }
    
}
