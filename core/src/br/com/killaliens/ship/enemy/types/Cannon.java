package br.com.killaliens.ship.enemy.types;

import br.com.killaliens.bonus.BonusType;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.player.PlayerShip;

public class Cannon extends EnemyShip {

    private static final float TIME_TO_ROTATE_IN_SECONDS = 2f;
    
    private float currentTime = 0f;
    
    public Cannon(ShipProperties properties) {
        super(properties);
    }

    @Override
    public void runArtificialIntelligence(float delta) {
        this.currentTime += delta;
        
        if (PlayerShip.getPlayerShip().isDead()) {
            return;
        }
        
        if (this.currentTime >= TIME_TO_ROTATE_IN_SECONDS) {
            this.currentTime = 0f;
            
            float dx = this.getX() - PlayerShip.getPlayerShip().getX();
            float dy = this.getY() - PlayerShip.getPlayerShip().getY();
            this.setRotation(90f+(float)Math.atan2(dy, dx)* 180f/(float)Math.PI);
        }
        
        this.setShooting(true);

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
        return BonusType.BIGBULLET;
    }
}
