package br.com.killaliens.screens.gamescreen;

import java.util.ArrayList;
import java.util.List;

import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.player.PlayerShip;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends Stage {
    
    private PlayerShip playerShip = null;
    private List<EnemyShip> enemyShips = new ArrayList<EnemyShip>();
    
    public void addPlayer(PlayerShip playerShip) {
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
