package br.com.killaliens.ship.enemy.enemyspawn;

/**
 * enemy spawn levels
 */
public enum EnemySpawnLevel {
    BASIC {
        @Override
        public EnemySpawnLevel levelUp() {
            return EXPERT;
        }
    },
    EXPERT {
        @Override
        public EnemySpawnLevel levelUp() {
            return HARD;
        }
    },
    HARD {
        @Override
        public EnemySpawnLevel levelUp() {
            return BOSS;
        }
    },
    BOSS {
        @Override
        public EnemySpawnLevel levelUp() {
            return BOSS;
        }
    };
    
    /**
     * Get the next level
     * @return next level
     */
    public abstract EnemySpawnLevel levelUp();
}
