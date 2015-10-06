package br.com.killaliens.ship.enemy.types;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;

import br.com.killaliens.ammunition.Ammunition;
import br.com.killaliens.ammunition.bullet.BulletType;
import br.com.killaliens.screens.gamescreen.GameScreen;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.enemyspawn.EnemySpawnLevel;
import br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.RandomEnemy;
import br.com.killaliens.util.random.StaticRandom;

public class Boss extends EnemyShip {

    private static final int LEVEL_NEW_BULLETS = 5;
    private static final int NUM_NEW_BULLETS = 50;

    private static final float SPEED_Y_POSITIONED = 1f;
    private static final float LEFT_SPEED_X = -4f;
    private static final float RIGHT_SPEED_X = 4f;

    private static final float TIME_LIMIT_TO_CHANGE_BULLET = 10f;
    private static final float TIME_LIMIT_TO_SPAWN_ENEMY = 15f;
    
    private float currentTime = 0f;
    
    private boolean positioned = false;
    private boolean moveLeft = false;
    
    private float spawnEnemyTime = 0f;
    
    public Boss(ShipProperties properties) {
        super(properties);
        this.getTopAmmunition().addLevel();
        this.getTopAmmunition().addLevel();
    }

    @Override
    public void runArtificialIntelligence(float delta) {
        move();
        this.changeBullets(delta);
        spawnEnemies(delta);
        
        this.setShooting(true);
    }

    protected void spawnEnemies(float delta) {
        this.spawnEnemyTime += delta;
        if (this.spawnEnemyTime >= TIME_LIMIT_TO_SPAWN_ENEMY) {
            this.spawnEnemyTime = 0f;
            
            EnemyShip enemyShip = (new RandomEnemy()).getRandomEnemyShip(EnemySpawnLevel.BASIC);
            Stage stage = this.getStage();
            
            Random rnd = new Random();
            float x = rnd.nextInt(Gdx.graphics.getWidth() - (int) enemyShip.getWidth());
            float y = stage.getCamera().position.y + stage.getCamera().viewportHeight/2 - 10f;
            enemyShip.setX(x);
            enemyShip.setY(y);
            
            if (stage != null && stage instanceof GameScreen) {
                ((GameScreen)stage).addEnemy(enemyShip);
            }
        }
    }

    protected void changeBullets(float delta) {
        this.currentTime += delta;
        if (this.currentTime >= TIME_LIMIT_TO_CHANGE_BULLET) {
            this.currentTime = 0f;
            
            int randomBullet = StaticRandom.getRandomValue(1, 3);
            if (randomBullet == 1) {
                this.addAmmunition(new Ammunition(BulletType.BIGBULLET, LEVEL_NEW_BULLETS));
            }
            else if (randomBullet == 2) {
                this.addAmmunition(new Ammunition(BulletType.FASTBULLET, LEVEL_NEW_BULLETS));
            }
            else if (randomBullet == 3) {
                this.addAmmunition(new Ammunition(BulletType.FLOWERBULLET, LEVEL_NEW_BULLETS));
            }
            
            this.getTopAmmunition().setTotalBullets(NUM_NEW_BULLETS);
            this.getTopAmmunition().setCurrentBullets(NUM_NEW_BULLETS);
        }
    }

    protected void move() {
        Camera camera = this.getStage().getCamera();
        
        goToLocation(camera);
        moveLeftRight(camera);
        
        this.setX(this.getX() + this.getSpeedX());
        this.setY(this.getY() + this.getSpeedY());
    }

    protected void moveLeftRight(Camera camera) {
        if (this.positioned && this.moveLeft) {
            if (this.getX() <= camera.position.x - camera.viewportWidth / 2) {
                this.moveLeft = false;
                this.setSpeedX(RIGHT_SPEED_X);
            }
        }
        else if (this.positioned && !this.moveLeft) {
            if (this.getX() + this.getWidth() >= camera.position.x + camera.viewportWidth/2) {
                this.moveLeft = true;
                this.setSpeedX(LEFT_SPEED_X);
            }
        }
    }

    protected void goToLocation(Camera camera) {
        if (!this.positioned &&
                this.getY() + this.getHeight()*2 < camera.position.y + camera.viewportHeight/2) {
            this.setSpeedY(SPEED_Y_POSITIONED);
            this.positioned = true;
            this.moveLeft = false;
            this.setSpeedX(RIGHT_SPEED_X);
        }
    }

}
