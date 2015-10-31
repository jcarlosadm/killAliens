package br.com.killaliens.screens.gameplay.states.resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.killaliens.ammunition.bullet.Bullet;
import br.com.killaliens.bonus.Bonus;
import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.screens.gameplay.states.resume.background.Background;
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

public class GamePlayResume extends Stage implements ScrollSubject {

    private static final float SCROLLDOWN_SPEED = 1f;

    private Map<GamePlayObjects, Group> gameScreenGroups = 
            new HashMap<GamePlayObjects, Group>();

    private List<ScrollObserver> scrollObservers = new ArrayList<ScrollObserver>();

    private EnemySpawnGenerator enemySpawnGenerator = null;

    public GamePlayResume(GamePlayScreen gamePlayScreen) {
        this.addGroups();
        
        this.addObjectToGroup(GamePlayObjects.BACKGROUND, new Background());
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

        this.registerObservers(statusBar, pauseButton, inPhase);
    }

    protected void addGroups() {
        for (GamePlayObjects unit : GamePlayObjects.values()) {
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

        Group bulletList = this.gameScreenGroups
                .get(GamePlayObjects.BULLET_LIST);
        Group enemyShips = this.gameScreenGroups
                .get(GamePlayObjects.ENEMY_SHIPS);
        Group playerShip = this.gameScreenGroups
                .get(GamePlayObjects.PLAYER_SHIP);
        Group bonusList = this.gameScreenGroups.get(GamePlayObjects.BONUS_LIST);

        this.checkBulletTouch(bulletList, enemyShips, playerShip);

        this.checkPlayerTouchToBonus(playerShip, bonusList);
    }

    protected void addRandomEnemy(float delta) {
        EnemyShip rndEnemyShip = this.enemySpawnGenerator
                .getRandomEnemyShip(delta);
        if (rndEnemyShip != null) {
            this.addObjectToGroup(GamePlayObjects.ENEMY_SHIPS, rndEnemyShip);
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

    public void addObjectToGroup(GamePlayObjects groupType, Actor actor) {
        Group group = this.gameScreenGroups.get(groupType);
        group.addActor(actor);
    }

    public void removeObjectFromGroup(GamePlayObjects groupType, Actor actor) {
        Group group = this.gameScreenGroups.get(groupType);
        group.removeActor(actor);
    }

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

    public EnemySpawnGenerator getEnemySpawnGenerator() {
        return this.enemySpawnGenerator;
    }

    public Map<GamePlayObjects, Group> getGameScreenGroups() {
        return this.gameScreenGroups;
    }
}