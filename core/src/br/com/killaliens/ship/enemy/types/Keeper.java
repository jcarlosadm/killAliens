package br.com.killaliens.ship.enemy.types;

import br.com.killaliens.bonus.BonusType;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;

import com.badlogic.gdx.graphics.Camera;

public class Keeper extends EnemyShip {
    
    private static final float SPEED_Y_MOVING = 2f;

    public Keeper(ShipProperties properties) {
        super(properties);
        this.getTopAmmunition().addLevel();
        this.getTopAmmunition().addLevel();
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

    @Override
    protected BonusType getLowBonusType() {
        return BonusType.UPLIFE;
    }

    @Override
    protected BonusType getMiddleBonusType() {
        return BonusType.UPATTACK;
    }

    @Override
    protected BonusType getHighBonusType() {
        return BonusType.UPSHIELD;
    }

}
