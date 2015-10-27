package br.com.killaliens.screens.gamescreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.killaliens.ammunition.bullet.Bullet;
import br.com.killaliens.bonus.Bonus;
import br.com.killaliens.explosion.Explosion;
import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.screens.ScreenType;
import br.com.killaliens.screens.gamescreen.background.Background;
import br.com.killaliens.screens.gamescreen.userinterface.InformationLevelPhase;
import br.com.killaliens.screens.gamescreen.userinterface.pause.PauseButton;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.enemyspawn.EnemySpawnGenerator;
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
    
    private Map<GameScreenUnits, Group> gameScreenGroups = new HashMap<GameScreenUnits, Group>();
    
    private List<ScrollObserver> scrollObservers = new ArrayList<ScrollObserver>();
    
    private EnemySpawnGenerator enemySpawnGenerator = null;
    
    private ScreenManager screenManager = null;
    
    public GameScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
        
        for (GameScreenUnits unit : GameScreenUnits.values()) {
            Group group = new Group();
            this.gameScreenGroups.put(unit, group);
            this.addActor(group);
        }
        
        this.addBackground(new Background());
        this.addPlayer(PlayerShip.getPlayerShip());
        
        this.enemySpawnGenerator = new EnemySpawnGenerator(this);
        
        Group userInterface = this.gameScreenGroups.get(GameScreenUnits.USER_INTERFACE);
        StatusBar statusBar = new StatusBar(PlayerShip.getPlayerShip());
        userInterface.addActor(statusBar);
        PauseButton pauseButton = new PauseButton(PlayerShip.getPlayerShip());
        userInterface.addActor(pauseButton);
        InformationLevelPhase inPhase = new InformationLevelPhase(this.enemySpawnGenerator);
        userInterface.addActor(inPhase);
        
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
        
        Group bulletList = this.gameScreenGroups.get(GameScreenUnits.BULLET_LIST);
        Group enemyShips = this.gameScreenGroups.get(GameScreenUnits.ENEMY_SHIPS);
        Group playerShip = this.gameScreenGroups.get(GameScreenUnits.PLAYER_SHIP);
        Group bonusList = this.gameScreenGroups.get(GameScreenUnits.BONUS_LIST);
        
        for (Actor bullet : bulletList.getChildren()) {
            for (Actor enemyShip : enemyShips.getChildren()) {
                ((Bullet) bullet).colliding((Ship) enemyShip);
            }
            if (playerShip.getChildren().size > 0) {
                ((Bullet) bullet).colliding(PlayerShip.getPlayerShip());
            }
        }
        
        for (Actor bonus : bonusList.getChildren()) {
            if (playerShip.getChildren().size > 0) {
                ((Bonus) bonus).colliding(PlayerShip.getPlayerShip());
            }
        }
    }

    private void changeCameraPosition(float deltaY) {
        this.getCamera().update();
        this.getCamera().translate(0, deltaY, 0);
        
        this.notifyScrollObservers(0, deltaY);
    }
    
    private void addBackground(Background background) {
        Group group = this.gameScreenGroups.get(GameScreenUnits.BACKGROUND);
        group.addActor(background);
    }
    
    private void addPlayer(PlayerShip playerShip) {
        Group group = this.gameScreenGroups.get(GameScreenUnits.PLAYER_SHIP);
        group.clear();
        group.addActor(playerShip);
    }
    
    public void removePlayer(){
        this.gameScreenGroups.get(GameScreenUnits.PLAYER_SHIP).clear();
    }
    
    public void addEnemy(EnemyShip enemyShip){
        Group group = this.gameScreenGroups.get(GameScreenUnits.ENEMY_SHIPS);
        group.addActor(enemyShip);
    }
    
    public void removeEnemy(EnemyShip enemyShip){
        Group group = this.gameScreenGroups.get(GameScreenUnits.ENEMY_SHIPS);
        group.removeActor(enemyShip);
    }
    
    public int getTotalEnemiesOnScreen(){
        Group group = this.gameScreenGroups.get(GameScreenUnits.ENEMY_SHIPS);
        return group.getChildren().size;
    }
    
    public void addBullet(Bullet bullet){
        Group group = this.gameScreenGroups.get(GameScreenUnits.BULLET_LIST);
        group.addActor(bullet);
    }
    
    public void removeBullet(Bullet bullet){
        Group group = this.gameScreenGroups.get(GameScreenUnits.BULLET_LIST);
        group.removeActor(bullet);
    }
    
    public void addExplosion(Explosion explosion){
        Group group = this.gameScreenGroups.get(GameScreenUnits.EXPLOSIONS);
        group.addActor(explosion);
    }
    
    public void removeExplosion(Explosion explosion){
        Group group = this.gameScreenGroups.get(GameScreenUnits.EXPLOSIONS);
        group.removeActor(explosion);
    }
    
    public void addBonus(Bonus bonus){
        Group group = this.gameScreenGroups.get(GameScreenUnits.BONUS_LIST);
        group.addActor(bonus);
    }
    
    public void removeBonus(Bonus bonus){
        Group group = this.gameScreenGroups.get(GameScreenUnits.BONUS_LIST);
        group.removeActor(bonus);
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
    
    public EnemySpawnGenerator getEnemySpawnGenerator(){
        return this.enemySpawnGenerator;
    }
    
    public Map<GameScreenUnits, Group> getGameScreenGroups(){
        return this.gameScreenGroups;
    }
    
    public void pause() {
        this.screenManager.changeCurrentScreen(ScreenType.PAUSE_MENU);
    }
}
