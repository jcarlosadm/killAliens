package br.com.killaliens.ship.enemy;

import com.badlogic.gdx.scenes.scene2d.Stage;

import br.com.killaliens.screens.gamescreen.GameScreen;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.status.EnemyDeadStatus;
import br.com.killaliens.ship.enemy.status.EnemyNormalStatus;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.camera.CheckVisibleOnCamera;

public abstract class EnemyShip extends Ship {

    private CheckVisibleOnCamera checkVisibleOnCamera = new CheckVisibleOnCamera(this);
    
    public EnemyShip(ShipProperties properties) {
        super(properties);
        this.setIfIsEnemy(true);
        
        this.addStatus(AnimationTypes.NORMAL_STATE, new EnemyNormalStatus(this));
        this.addStatus(AnimationTypes.DEAD, new EnemyDeadStatus(this));
        this.setCurrentStatus(AnimationTypes.NORMAL_STATE);
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
        
        if (!this.checkVisibleOnCamera.actorIsVisible()) {
            this.remove();
        }
        
        if (!this.isDead()) {
            this.setCurrentStatus(AnimationTypes.NORMAL_STATE);
        } else {
            this.setCurrentStatus(AnimationTypes.DEAD);
        }
        
        this.getCurrentStatus().setup();
        this.getCurrentStatus().act(delta);
        
        
    }
    
    @Override
    public boolean remove() {
        Stage stage = this.getStage();
        if (stage != null && stage instanceof GameScreen) {
            ((GameScreen) this.getStage()).removeEnemy(this);
        }
        
        return super.remove();
    }

    public abstract void runArtificialIntelligence(float delta);
}
