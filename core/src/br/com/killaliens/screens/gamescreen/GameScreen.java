package br.com.killaliens.screens.gamescreen;

import br.com.killaliens.bullet.Bullet;
import br.com.killaliens.explosion.Explosion;
import br.com.killaliens.screens.gamescreen.background.Background;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.factory.EnemyFactory;
import br.com.killaliens.ship.enemy.types.EnemyTypes;
import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.util.accumulatorScroll.AccumulatorScroolY;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends Stage {
    
    private static final float SCROLLDOWN_SPEED = 1f;
    
    private Group playerShip = new Group();
    private Group enemyShips = new Group();
    private Group bulletList = new Group();
    private Group background = new Group();
    private Group explosions = new Group();
    private Group userInterface = new Group();
    
    public GameScreen() {
        this.addActor(this.background);
        this.addActor(this.enemyShips);
        this.addActor(this.playerShip);
        this.addActor(this.bulletList);
        this.addActor(this.explosions);
        this.addActor(this.userInterface);
        
        this.addBackground(new Background());
        this.addPlayer(PlayerShip.getPlayerShip());
        
        // TODO add user interfaces
        
        // TODO for tests
        this.addEnemy(EnemyFactory.getEnemyInstance(EnemyTypes.UFO));
        
        Gdx.input.setInputProcessor(this);
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
        
        this.getCamera().update();
        this.getCamera().translate(0, SCROLLDOWN_SPEED, 0);
        
        for (Actor group : this.getActors()) {
            for (Actor actor : (((Group) group).getChildren())) {
                if (actor instanceof AccumulatorScroolY) {
                    ((AccumulatorScroolY) actor).addAccumulatorScrollY(SCROLLDOWN_SPEED);
                }
            }
        }
        
        for (Actor bullet : this.bulletList.getChildren()) {
            for (Actor enemyShip : enemyShips.getChildren()) {
                ((Bullet) bullet).colliding((Ship) enemyShip);
            }
            if (this.playerShip.getChildren().size > 0) {
                ((Bullet) bullet).colliding((PlayerShip) this.playerShip.getChildren().first());
            }
            
        }
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
    
    public void removeShipFromEnemyList(EnemyShip enemyShip){
        this.enemyShips.removeActor(enemyShip);
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
    
}
