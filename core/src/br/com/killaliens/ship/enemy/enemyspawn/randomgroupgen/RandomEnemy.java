package br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen;

import java.util.HashMap;
import java.util.Map;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.enemyspawn.EnemySpawnLevel;
import br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.implement.BasicEnemies;
import br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.implement.Boss;
import br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.implement.ExpertEnemies;
import br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.implement.HardEnemies;
import br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.implement.RandomGroupGenerator;

public class RandomEnemy {
    
    private Map<EnemySpawnLevel, RandomGroupGenerator> rndGroup = 
            new HashMap<EnemySpawnLevel, RandomGroupGenerator>();
    
    public RandomEnemy() {
        this.rndGroup.put(EnemySpawnLevel.BASIC, new BasicEnemies());
        this.rndGroup.put(EnemySpawnLevel.EXPERT, new ExpertEnemies());
        this.rndGroup.put(EnemySpawnLevel.HARD, new HardEnemies());
        this.rndGroup.put(EnemySpawnLevel.BOSS, new Boss());
    }
    
    public EnemyShip getRandomEnemyShip(EnemySpawnLevel esLevel){
        return this.rndGroup.get(esLevel).getEnemyShip();
    }
    
}
