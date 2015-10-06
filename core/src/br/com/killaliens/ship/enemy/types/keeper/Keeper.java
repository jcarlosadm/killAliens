package br.com.killaliens.ship.enemy.types.keeper;

import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.types.keeper.states.KeeperDeadStatus;
import br.com.killaliens.ship.enemy.types.keeper.states.KeeperNormalStatus;
import br.com.killaliens.util.animation.AnimationTypes;

import com.badlogic.gdx.graphics.Camera;

public class Keeper extends EnemyShip {
    
    private static final float SPEED_Y_MOVING = 2f;

    public Keeper(ShipProperties properties) {
        super(properties);
        this.getTopAmmunition().addLevel();
        this.getTopAmmunition().addLevel();
        
        this.addStatus(AnimationTypes.NORMAL_STATE, new KeeperNormalStatus(this));
        this.addStatus(AnimationTypes.DEAD, new KeeperDeadStatus(this));
        this.setCurrentStatus(AnimationTypes.NORMAL_STATE);
    }

    @Override
    public void runArtificialIntelligence(float delta) {
        this.setShooting(true);
        
        Camera camera = this.getStage().getCamera();
        if (this.getY() <= camera.position.y - camera.viewportHeight/2) {
            this.setSpeedY(SPEED_Y_MOVING);
        }
        else if (this.getY() + this.getHeight() >= camera.position.y 
                + camera.viewportHeight/2) {
            this.setSpeedY(0f);
        }
        
        this.setY(this.getY() + this.getSpeedY());
    }

}
