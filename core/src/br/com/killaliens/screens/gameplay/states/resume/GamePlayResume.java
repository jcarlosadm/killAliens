package br.com.killaliens.screens.gameplay.states.resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.killaliens.bonus.Bonus;
import br.com.killaliens.bullet.Bullet;
import br.com.killaliens.screens.ScreenState;
import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.screens.gameplay.states.resume.background.InfinityBackground;
import br.com.killaliens.screens.gameplay.states.resume.userinterface.InformationLevelPhase;
import br.com.killaliens.screens.gameplay.states.resume.userinterface.PauseButton;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.enemyspawn.EnemySpawnGenerator;
import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.ship.player.statsbar.StatusBar;
import br.com.killaliens.util.scrollobserver.ScrollObserver;
import br.com.killaliens.util.scrollobserver.ScrollSubject;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GamePlayResume extends Stage implements ScrollSubject, ScreenState {

    private static final float SCROLLDOWN_SPEED = 1f;

    private Map<GamePlayObjects, Group> gameScreenGroups = 
            new HashMap<GamePlayObjects, Group>();

    private List<ScrollObserver> scrollObservers = new ArrayList<ScrollObserver>();

    private EnemySpawnGenerator enemySpawnGenerator = null;

    /**
     * Constructor
     * @param gamePlayScreen GamePlayScreen object
     */
    public GamePlayResume(GamePlayScreen gamePlayScreen) {
        this.setAllGroups();
        
        this.addObjectToGroup(GamePlayObjects.BACKGROUND, new InfinityBackground());
        this.addObjectToGroup(GamePlayObjects.PLAYER_SHIP,
                PlayerShip.getPlayerShip());

        this.enemySpawnGenerator = new EnemySpawnGenerator(this);

        StatusBar statusBar = new StatusBar(PlayerShip.getPlayerShip());
        PauseButton pauseButton = new PauseButton(PlayerShip.getPlayerShip(), gamePlayScreen);
        InformationLevelPhase inPhase = new InformationLevelPhase(
                this.enemySpawnGenerator);
        this.addObjectToGroup(GamePlayObjects.USER_INTERFACE, statusBar);
        this.addObjectToGroup(GamePlayObjects.USER_INTERFACE, pauseButton);
        this.addObjectToGroup(GamePlayObjects.USER_INTERFACE, inPhase);

        this.registerScrollObservers(statusBar, pauseButton, inPhase);
    }

    /**
     * set all groups
     */
    private void setAllGroups() {
        for (GamePlayObjects unit : GamePlayObjects.values()) {
            Group group = new Group();
            this.gameScreenGroups.put(unit, group);
            this.addActor(group);
        }
    }

    /**
     * Register scroll observers
     * @param statusBar StatusBar object
     * @param pauseButton PauseButton object
     * @param inPhase InformationLevelPhase object
     */
    private void registerScrollObservers(StatusBar statusBar,
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

        Group bulletList = this.gameScreenGroups
                .get(GamePlayObjects.BULLET_LIST);
        Group enemyShips = this.gameScreenGroups
                .get(GamePlayObjects.ENEMY_SHIPS);
        Group playerShip = this.gameScreenGroups
                .get(GamePlayObjects.PLAYER_SHIP);
        Group bonusList = this.gameScreenGroups.get(GamePlayObjects.BONUS_LIST);

        this.checkBulletsCollision(bulletList, enemyShips, playerShip);

        this.checkPlayerTouchToBonus(playerShip, bonusList);
    }

    /**
     * Add random enemy
     * @param delta Time in seconds since the last frame 
     */
    private void addRandomEnemy(float delta) {
        EnemyShip rndEnemyShip = this.enemySpawnGenerator
                .getRandomEnemyShip(delta);
        if (rndEnemyShip != null) {
            this.addObjectToGroup(GamePlayObjects.ENEMY_SHIPS, rndEnemyShip);
        }
    }

    /**
     * Check if bullet collide with ships
     * @param bulletList bullet group
     * @param enemyShips enemy group
     * @param playerShips player group
     */
    private void checkBulletsCollision(Group bulletList, Group enemyShips,
            Group playerShips) {
        for (Actor bullet : bulletList.getChildren()) {
            this.checkBulletCollisionToShips(enemyShips, bullet);
            this.checkBulletCollisionToShips(playerShips, bullet);
        }
    }

    /**
     * Check bullet collision with ships
     * @param enemyShips enemy group
     * @param bullet bullet object
     */
    private void checkBulletCollisionToShips(Group ships, Actor bullet) {
        for (Actor ship : ships.getChildren()) {
            ((Bullet) bullet).colliding((Ship) ship);
        }
    }

    /**
     * Check if player touch bonus objects
     * @param playerShip player group
     * @param bonusList bonus group
     */
    private void checkPlayerTouchToBonus(Group playerShip, Group bonusList) {
        for (Actor bonus : bonusList.getChildren()) {
            for (Actor ship : playerShip.getChildren()) {
                ((Bonus) bonus).colliding((PlayerShip) ship);
            }
        }
    }

    /**
     * Change the camera position
     * @param deltaY value to change in vertical position
     */
    private void changeCameraPosition(float deltaY) {
        this.getCamera().update();
        this.getCamera().translate(0, deltaY, 0);

        this.notifyScrollObservers(0, deltaY);
    }

    /**
     * Add an actor object to a specific group
     * @param groupType
     * @param actor
     */
    public void addObjectToGroup(GamePlayObjects groupType, Actor actor) {
        Group group = this.gameScreenGroups.get(groupType);
        group.addActor(actor);
    }

    /**
     * Remove actor object from a specific group
     * @param groupType
     * @param actor
     */
    public void removeObjectFromGroup(GamePlayObjects groupType, Actor actor) {
        Group group = this.gameScreenGroups.get(groupType);
        group.removeActor(actor);
    }

    /**
     * Get number of objects in a specific group 
     * @param groupType
     * @return number of objects in this group
     */
    public int getTotalObjectsOnGroup(GamePlayObjects groupType) {
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
}
