package br.com.killaliens.ship.enemy.enemyspawn;

import java.util.Random;

import br.com.killaliens.screens.gameplay.states.resume.GamePlayObjects;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayResume;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.RandomEnemy;
import br.com.killaliens.util.scrollobserver.ScrollObserver;

import com.badlogic.gdx.Gdx;

public class EnemySpawnGenerator implements ScrollObserver{
    
    private static final float TIME_LIMIT_TO_SPAWN_IN_SECONDS = 2;
    
    private static final int MAX_ENEMIES_ON_SCREEN = 5;
    
    private static final int TOTAL_ENEMIES_TO_UP_LEVEL = 25;
    
    private float totalTime = 0f;
    
    private int totalEnemies = 0;
    
    private EnemySpawnLevel spawnLevel = EnemySpawnLevel.BASIC;
    
    private GamePlayResume gameScreen = null;
    
    private RandomEnemy rndEnemy = new RandomEnemy();
    
    private float scrollTotalY = 0f;
    
    private boolean bossLock = false;
    
    /**
     * Constructor
     * @param gameScreen GamePlayResume object
     */
    public EnemySpawnGenerator(GamePlayResume gameScreen) {
        this.gameScreen = gameScreen;
    }
    
    /**
     * Get a random enemy ship and calculate the current spawn level
     * @param deltaTime time in seconds since the last frame
     * @return EnemyShip instance
     */
    public EnemyShip getRandomEnemyShip(float deltaTime){
        this.totalTime += deltaTime;
        
        if (this.bossLock) {
            return null;
        }
        
        if (this.totalTime < TIME_LIMIT_TO_SPAWN_IN_SECONDS
                || this.gameScreen.getTotalObjectsOnGroup(GamePlayObjects.ENEMY_SHIPS)
                    >= MAX_ENEMIES_ON_SCREEN) {
            return null;
        }
        
        EnemyShip enemyShip = this.rndEnemy.getRandomEnemyShip(this.spawnLevel);
        if (enemyShip == null) {
            return enemyShip;
        }
        
        this.totalTime = 0;
        this.totalEnemies += 1;
        this.putInRandomLocation(enemyShip);
        
        if (this.spawnLevel.equals(EnemySpawnLevel.BOSS)) {
            this.bossLock = true;
        }
        
        if (this.totalEnemies >= TOTAL_ENEMIES_TO_UP_LEVEL) {
            this.totalEnemies = 0;
            this.spawnLevel = this.spawnLevel.levelUp();
        }
        
        return enemyShip;
    }

    /**
     * Put enemy ship in random location
     * @param enemyShip
     */
    private void putInRandomLocation(EnemyShip enemyShip) {
        Random rnd = new Random();
        float x = rnd.nextInt(Gdx.graphics.getWidth() - (int) enemyShip.getWidth());
        float y = Gdx.graphics.getHeight() - 10 + this.scrollTotalY;
        
        enemyShip.setX(x);
        enemyShip.setY(y);
    }

    @Override
    public void updateScroll(float xDelta, float yDelta) {
        this.scrollTotalY += yDelta;
    }
    
    /**
     * @return enemy spawn level
     */
    public EnemySpawnLevel getEnemySpawnLevel(){
        return this.spawnLevel;
    }
}
