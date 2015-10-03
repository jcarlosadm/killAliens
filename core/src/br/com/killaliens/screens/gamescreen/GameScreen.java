package br.com.killaliens.screens.gamescreen;

import java.util.ArrayList;
import java.util.List;

import br.com.killaliens.bullet.Bullet;
import br.com.killaliens.explosion.Explosion;
import br.com.killaliens.screens.gamescreen.background.Background;
import br.com.killaliens.screens.gamescreen.userinterface.InformationLevelPhase;
import br.com.killaliens.screens.gamescreen.userinterface.PauseButton;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.enemyspawn.EnemySpawnGenerator;
import br.com.killaliens.ship.enemy.enemyspawn.EnemySpawnLevel;
import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.ship.player.statsbar.StatusBar;
import br.com.killaliens.util.scrollobserver.ScrollObserver;
import br.com.killaliens.util.scrollobserver.ScrollSubject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends Stage implements ScrollSubject {
    
    private static final float SCROLLDOWN_SPEED = 1f;
    
    private Group playerShip = new Group();
    private Group enemyShips = new Group();
    private Group bulletList = new Group();
    private Group background = new Group();
    private Group explosions = new Group();
    private Group userInterface = new Group();
    
    private List<ScrollObserver> scrollObservers = new ArrayList<ScrollObserver>();
    
    private EnemySpawnGenerator enemySpawnGenerator = null;
    
    public GameScreen() {
        this.addActor(this.background);
        this.addActor(this.enemyShips);
        this.addActor(this.playerShip);
        this.addActor(this.bulletList);
        this.addActor(this.explosions);
        this.addActor(this.userInterface);
        
        this.addBackground(new Background());
        this.addPlayer(PlayerShip.getPlayerShip());
        
        StatusBar statusBar = new StatusBar(PlayerShip.getPlayerShip());
        this.userInterface.addActor(statusBar);
        PauseButton pauseButton = new PauseButton(PlayerShip.getPlayerShip());
        this.userInterface.addActor(pauseButton);
        InformationLevelPhase inPhase = new InformationLevelPhase();
        this.userInterface.addActor(inPhase);
        
        this.enemySpawnGenerator = new EnemySpawnGenerator(this);
        
        this.registerScrollObserver(PlayerShip.getPlayerShip());
        this.registerScrollObserver(statusBar);
        this.registerScrollObserver(pauseButton);
        this.registerScrollObserver(this.enemySpawnGenerator);
        this.registerScrollObserver(inPhase);
        
        Gdx.input.setInputProcessor(this);
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
        
        this.changeCameraPosition(SCROLLDOWN_SPEED);
        
        EnemyShip rndEnemyShip = this.enemySpawnGenerator.getRandomEnemyShip(delta);
        if (rndEnemyShip != null) {
            this.addEnemy(rndEnemyShip);
        }
        
        for (Actor bullet : this.bulletList.getChildren()) {
            for (Actor enemyShip : enemyShips.getChildren()) {
                ((Bullet) bullet).colliding((Ship) enemyShip);
            }
            if (this.playerShip.getChildren().size > 0) {
                ((Bullet) bullet).colliding(PlayerShip.getPlayerShip());
            }
        }
    }

    private void changeCameraPosition(float deltaY) {
        this.getCamera().update();
        this.getCamera().translate(0, deltaY, 0);
        
        this.notifyScrollObservers(0, deltaY);
    }
    
    private void addBackground(Background background) {
        this.background.addActor(background);
    }
    
    private void addPlayer(PlayerShip playerShip) {
        this.playerShip.clear();
        this.playerShip.addActor(playerShip);
    }
    
    public void removePlayer(){
        this.playerShip.clear();
    }
    
    public void addEnemy(EnemyShip enemyShip){
        this.enemyShips.addActor(enemyShip);
    }
    
    public void removeEnemy(EnemyShip enemyShip){
        this.enemyShips.removeActor(enemyShip);
    }
    
    public int getTotalEnemiesOnScreen(){
        return this.enemyShips.getChildren().size;
    }
    
    public void addBullet(Bullet bullet){
        this.bulletList.addActor(bullet);
    }
    
    public void removeBullet(Bullet bullet){
        this.bulletList.removeActor(bullet);
    }
    
    public void addExplosion(Explosion explosion){
        this.explosions.addActor(explosion);
    }
    
    public void removeExplosion(Explosion explosion){
        this.explosions.removeActor(explosion);
    }

    @Override
    public void registerScrollObserver(ScrollObserver observer) {
        this.scrollObservers.add(observer);
    }

    @Override
    public void removeScrollObserver(ScrollObserver observer) {
        this.scrollObservers.remove(observer);
    }

    @Override
    public void notifyScrollObservers(float deltaX, float deltaY) {
        for (ScrollObserver scrollObserver : this.scrollObservers) {
            scrollObserver.updateScroll(deltaX, deltaY);
        }
    }
    
    public EnemySpawnLevel getEnemySpawnLevel(){
        return this.enemySpawnGenerator.getEnemySpawnLevel();
    }
}
