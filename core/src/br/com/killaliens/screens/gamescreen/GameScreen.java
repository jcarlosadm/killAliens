package br.com.killaliens.screens.gamescreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.killaliens.ammunition.bullet.Bullet;
import br.com.killaliens.bonus.Bonus;
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
        
        this.addGroups();
        
        this.addObjectToGroup(GameScreenUnits.BACKGROUND, new Background());
        this.addObjectToGroup(GameScreenUnits.PLAYER_SHIP, PlayerShip.getPlayerShip());
        
        this.enemySpawnGenerator = new EnemySpawnGenerator(this);
        
        StatusBar statusBar = new StatusBar(PlayerShip.getPlayerShip());
        PauseButton pauseButton = new PauseButton(PlayerShip.getPlayerShip());
        InformationLevelPhase inPhase = new InformationLevelPhase(this.enemySpawnGenerator);
        this.addObjectToGroup(GameScreenUnits.USER_INTERFACE, statusBar);
        this.addObjectToGroup(GameScreenUnits.USER_INTERFACE, pauseButton);
        this.addObjectToGroup(GameScreenUnits.USER_INTERFACE, inPhase);
        
        this.registerObservers(statusBar, pauseButton, inPhase);
        
        Gdx.input.setInputProcessor(this);
    }

    protected void addGroups() {
        for (GameScreenUnits unit : GameScreenUnits.values()) {
            Group group = new Group();
            this.gameScreenGroups.put(unit, group);
            this.addActor(group);
        }
    }

    protected void registerObservers(StatusBar statusBar,
            PauseButton pauseButton, InformationLevelPhase inPhase) {
        this.registerScrollObserver(PlayerShip.getPlayerShip());
        this.registerScrollObserver(statusBar);
        this.registerScrollObserver(pauseButton);
        this.registerScrollObserver(this.enemySpawnGenerator);
        this.registerScrollObserver(inPhase);
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
        this.changeCameraPosition(SCROLLDOWN_SPEED);
        
        this.addRandomEnemy(delta);
        
        Group bulletList = this.gameScreenGroups.get(GameScreenUnits.BULLET_LIST);
        Group enemyShips = this.gameScreenGroups.get(GameScreenUnits.ENEMY_SHIPS);
        Group playerShip = this.gameScreenGroups.get(GameScreenUnits.PLAYER_SHIP);
        Group bonusList = this.gameScreenGroups.get(GameScreenUnits.BONUS_LIST);
        
        this.checkBulletTouch(bulletList, enemyShips, playerShip);
        
        this.checkPlayerTouchToBonus(playerShip, bonusList);
    }

    protected void addRandomEnemy(float delta) {
        EnemyShip rndEnemyShip = this.enemySpawnGenerator.getRandomEnemyShip(delta);
        if (rndEnemyShip != null) {
            this.addObjectToGroup(GameScreenUnits.ENEMY_SHIPS, rndEnemyShip);
        }
    }

    protected void checkBulletTouch(Group bulletList, Group enemyShips,
            Group playerShip) {
        for (Actor bullet : bulletList.getChildren()) {
            this.checkBulletTouchToEnemies(enemyShips, bullet);
            this.checkBulletTouchToPlayer(playerShip, bullet);
        }
    }

    protected void checkBulletTouchToEnemies(Group enemyShips, Actor bullet) {
        for (Actor enemyShip : enemyShips.getChildren()) {
            ((Bullet) bullet).colliding((Ship) enemyShip);
        }
    }

    protected void checkBulletTouchToPlayer(Group playerShip, Actor bullet) {
        if (playerShip.getChildren().size > 0) {
            ((Bullet) bullet).colliding(PlayerShip.getPlayerShip());
        }
    }

    protected void checkPlayerTouchToBonus(Group playerShip, Group bonusList) {
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
    
    public void addObjectToGroup(GameScreenUnits groupType, Actor actor){
        Group group = this.gameScreenGroups.get(groupType);
        group.addActor(actor);
    }
    
    public void removeObjectFromGroup(GameScreenUnits groupType, Actor actor){
        Group group = this.gameScreenGroups.get(groupType);
        group.removeActor(actor);
    }
    
    public int getTotalObjectsOnGroup(GameScreenUnits groupType){
        return this.gameScreenGroups.get(groupType).getChildren().size;
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
