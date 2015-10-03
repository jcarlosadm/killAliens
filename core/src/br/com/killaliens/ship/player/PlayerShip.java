package br.com.killaliens.ship.player;

import br.com.killaliens.screens.gamescreen.GameScreen;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.player.states.PlayerDeadStatus;
import br.com.killaliens.ship.player.states.PlayerNormalStatus;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.scrollobserver.ScrollObserver;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class PlayerShip extends Ship implements ScrollObserver {

    private boolean touched = false;
    
    private static PlayerShip playerShipInstance = null;
    
    private float accumulatorScrollY = 0f;

    private PlayerShip(ShipProperties properties) {
        super(properties);
        this.setIfIsEnemy(false);
        this.setTouchable(Touchable.enabled);
        this.setVisible(true);
        this.addListener(new PlayerTouchListener(this));
        
        this.addStatus(AnimationTypes.NORMAL_STATE, new PlayerNormalStatus(this));
        this.addStatus(AnimationTypes.DEAD, new PlayerDeadStatus(this));
        this.setCurrentStatus(AnimationTypes.NORMAL_STATE);
    }
    
    public static synchronized PlayerShip getPlayerShip(){
        if (playerShipInstance == null) {
            ShipPlayerPropertiesBuilder builder = new ShipPlayerPropertiesBuilder();
            ShipProperties properties = builder.getShipProperties();
            playerShipInstance = new PlayerShip(properties);
        }
        
        return playerShipInstance;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        
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
            ((GameScreen) stage).removePlayer();
        }
        return super.remove();
    }
    
    /**
     * @return the touched
     */
    public boolean isTouched() {
        return this.touched;
    }

    /**
     * @param touched the touched to set
     */
    protected void setTouched(boolean touched) {
        this.touched = touched;
    }

    public float getAccumulatorScrollY() {
        return this.accumulatorScrollY;
    }

    @Override
    public void updateScroll(float xDelta, float yDelta) {
        this.accumulatorScrollY += yDelta;
        this.setY(this.getY()+yDelta);
    }

}
