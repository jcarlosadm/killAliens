package br.com.killaliens.ship.player;

import br.com.killaliens.screens.gameplay.states.resume.GamePlayObjects;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayResume;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.player.states.PlayerDeadStatus;
import br.com.killaliens.ship.player.states.PlayerNormalStatus;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.collision.CollisionChecker;
import br.com.killaliens.util.mouse.TouchAndMouseState;
import br.com.killaliens.util.scrollobserver.ScrollObserver;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class PlayerShip extends Ship implements ScrollObserver {

    private boolean touched = false;

    private static PlayerShip playerShipInstance = null;

    private float accumulatorScrollY = 0f;

    private PlayerShip(ShipProperties properties) {
        super(properties);
        this.setIfIsEnemy(false);

        this.addStatus(AnimationTypes.NORMAL_STATE,
                new PlayerNormalStatus(this));
        this.addStatus(AnimationTypes.DEAD, new PlayerDeadStatus(this));
        this.setCurrentStatus(AnimationTypes.NORMAL_STATE);
    }

    public static synchronized PlayerShip getPlayerShip() {
        if (playerShipInstance == null) {
            ShipPlayerPropertiesBuilder builder = new ShipPlayerPropertiesBuilder();
            ShipProperties properties = builder.getShipProperties();
            playerShipInstance = new PlayerShip(properties);
        }

        return playerShipInstance;
    }
    
    public static synchronized void reset(){
        playerShipInstance = null;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (!this.isDead()) {
            this.setCurrentStatus(AnimationTypes.NORMAL_STATE);
        } else {
            this.setCurrentStatus(AnimationTypes.DEAD);
        }

        TouchAndMouseState touch = TouchAndMouseState.getInstance();
        if (touch.isPressing()) {
            float pointX = touch.getPoint().x;
            float pointY = touch.getPoint().y + this.accumulatorScrollY;
            if (CollisionChecker.check(this.getLimits(), pointX, pointY)) {
                this.touched = true;
            }
            
        } else {
            this.touched = false;
        }

        this.getCurrentStatus().setup();
        this.getCurrentStatus().act(delta);
    }

    @Override
    public boolean remove() {
        Stage stage = this.getStage();
        if (stage != null && stage instanceof GamePlayResume) {
            ((GamePlayResume) stage).removeObjectFromGroup(
                    GamePlayObjects.PLAYER_SHIP, this);
        }
        return super.remove();
    }

    /**
     * @return the touched
     */
    public boolean isTouched() {
        return this.touched;
    }

    public float getAccumulatorScrollY() {
        return this.accumulatorScrollY;
    }

    @Override
    public void updateScroll(float xDelta, float yDelta) {
        this.accumulatorScrollY += yDelta;
        this.setY(this.getY() + yDelta);
    }

}
