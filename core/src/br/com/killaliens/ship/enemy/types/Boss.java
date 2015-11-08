package br.com.killaliens.ship.enemy.types;

import java.util.Random;

import br.com.killaliens.ammunition.Ammunition;
import br.com.killaliens.bonus.BonusType;
import br.com.killaliens.bullet.BulletType;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayObjects;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayResume;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.enemyspawn.EnemySpawnLevel;
import br.com.killaliens.ship.enemy.enemyspawn.randomgroupgen.RandomEnemy;
import br.com.killaliens.ship.enemy.status.BossDeadStatus;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.random.StaticRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;

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
    
    /**
     * Constructor
     * @param properties
     */
    public Boss(ShipProperties properties) {
        super(properties);
        this.addStatus(AnimationTypes.DEAD, new BossDeadStatus(this));
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

    /**
     * spawn random enemies in screen 
     * @param delta time in seconds since the last frame
     */
    private void spawnEnemies(float delta) {
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
            
            if (stage != null && stage instanceof GamePlayResume) {
                ((GamePlayResume)stage).addObjectToGroup(GamePlayObjects.ENEMY_SHIPS, enemyShip);
            }
        }
    }

    /**
     * change bullet type
     * @param delta time in seconds since the last frame
     */
    private void changeBullets(float delta) {
        this.currentTime += delta;
        if (this.currentTime >= TIME_LIMIT_TO_CHANGE_BULLET) {
            this.currentTime = 0f;
            
            BulletType type = BulletType.NORMALBULLET;
            int randomBullet = StaticRandom.getRandomValue(1, 3);
            
            if (randomBullet == 1) {
                type = BulletType.BIGBULLET;
            }
            else if (randomBullet == 2) {
                type = BulletType.FASTBULLET;
            }
            else {
                type = BulletType.FLOWERBULLET;
            }
            
            this.addAmmunition(Ammunition.getNonInfinityAmmunition(type, LEVEL_NEW_BULLETS,
                    NUM_NEW_BULLETS));
        }
    }

    /**
     * move algorithm
     */
    private void move() {
        Camera camera = this.getStage().getCamera();
        
        goToLocation(camera);
        moveLeftRight(camera);
        
        this.setX(this.getX() + this.getSpeedX());
        this.setY(this.getY() + this.getSpeedY());
    }

    /**
     * move to left and right
     * @param camera
     */
    private void moveLeftRight(Camera camera) {
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

    /**
     * initial move
     * @param camera
     */
    private void goToLocation(Camera camera) {
        if (!this.positioned &&
                this.getY() + this.getHeight()*2 < camera.position.y + camera.viewportHeight/2) {
            this.setSpeedY(SPEED_Y_POSITIONED);
            this.positioned = true;
            this.moveLeft = false;
            this.setSpeedX(RIGHT_SPEED_X);
        }
    }

    @Override
    protected BonusType getLowBonusType() {
        return null;
    }

    @Override
    protected BonusType getMiddleBonusType() {
        return null;
    }

    @Override
    protected BonusType getHighBonusType() {
        return null;
    }

}
