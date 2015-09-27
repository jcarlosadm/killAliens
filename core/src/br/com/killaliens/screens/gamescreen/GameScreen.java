package br.com.killaliens.screens.gamescreen;

import java.util.ArrayList;
import java.util.List;

import br.com.killaliens.bullet.Bullet;
import br.com.killaliens.screens.gamescreen.background.Background;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.factory.EnemyFactory;
import br.com.killaliens.ship.enemy.types.EnemyTypes;
import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.util.accumulatorScroll.AccumulatorScrool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends Stage {
    
    private static final float SCROLLDOWN_SPEED = 1f;
    private PlayerShip playerShip = null;
    private List<EnemyShip> enemyShips = new ArrayList<EnemyShip>();
    
    public GameScreen() {
        // TODO Auto-generated constructor stub
        this.addActor(new Background());
        
        this.addEnemy(EnemyFactory.getEnemyInstance(EnemyTypes.UFO));
        
        this.addPlayer(PlayerShip.getPlayerShip());
        
        Gdx.input.setInputProcessor(this);
    }
    
    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
        super.act(delta);
        this.getCamera().update();
        this.getCamera().translate(0, SCROLLDOWN_SPEED, 0);
        
        for (Actor actor : this.getActors()) {
            if (actor instanceof AccumulatorScrool) {
                ((AccumulatorScrool) actor).addAccumulatorScrollY(SCROLLDOWN_SPEED);
            }
        }
        
        for (Actor bullet : this.getActors()) {
            if (bullet instanceof Bullet) {
                for (EnemyShip enemyShip : enemyShips) {
                    ((Bullet) bullet).colliding(enemyShip);
                }
                ((Bullet) bullet).colliding(playerShip);
            }
        }
    }
    
    public void addPlayer(PlayerShip playerShip) {
        if (this.playerShip != null) {
            this.playerShip.remove();
        }
        
        this.playerShip = playerShip;
        this.addActor(playerShip);
    }
    
    public void addEnemy(EnemyShip enemyShip){
        this.enemyShips.add(enemyShip);
        this.addActor(enemyShip);
    }
    
    public void removeEnemyShip(EnemyShip enemyShip){
        this.enemyShips.remove(enemyShip);
    }
    
}
