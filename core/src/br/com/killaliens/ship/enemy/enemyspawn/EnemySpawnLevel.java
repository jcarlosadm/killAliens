package br.com.killaliens.ship.enemy.enemyspawn;

public enum EnemySpawnLevel {
    BASIC {
        @Override
        public EnemySpawnLevel levelUp() {
            // TODO Auto-generated method stub
            return EXPERT;
        }
    },
    EXPERT {
        @Override
        public EnemySpawnLevel levelUp() {
            // TODO Auto-generated method stub
            return HARD;
        }
    },
    HARD {
        @Override
        public EnemySpawnLevel levelUp() {
            // TODO Auto-generated method stub
            return BOSS;
        }
    },
    BOSS {
        @Override
        public EnemySpawnLevel levelUp() {
            // TODO Auto-generated method stub
            return BOSS;
        }
    };
    
    /**
     * Get the next level
     * @return next level
     */
    public abstract EnemySpawnLevel levelUp();
}
