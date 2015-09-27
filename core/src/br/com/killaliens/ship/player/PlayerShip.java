package br.com.killaliens.ship.player;

import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.ShipPropertiesBuilder;
import br.com.killaliens.util.accumulatorScroll.AccumulatorScrool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class PlayerShip extends Ship implements AccumulatorScrool {
    
    private boolean touched = false;
    
    private static PlayerShip playerShipInstance = null;
    
    private float accumulatorScrollY = 0f;

    private PlayerShip(ShipProperties properties) {
        super(properties);
        this.setIfIsEnemy(false);
        this.setTouchable(Touchable.enabled);
        this.setVisible(true);
        this.addListener(new PlayerTouchListener(this));
    }
    
    public static synchronized PlayerShip getPlayerShip(){
        if (playerShipInstance == null) {
            ShipPropertiesBuilder builder = new ShipPlayerPropertiesBuilder();
            ShipProperties properties = builder.getShipProperties();
            playerShipInstance = new PlayerShip(properties);
        }
        
        return playerShipInstance;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        
        if (this.isTouched()) {
            this.setShooting(true);
            this.moveToLocation(Gdx.input.getX(), Gdx.graphics.getHeight() 
                    - Gdx.input.getY(), delta);
        }
    }
    
    public void moveToLocation(float x, float y, float timeInSeconds) {
        MoveToAction movAction = new MoveToAction();
        movAction.setPosition(x - this.getWidth()/2, 
                y + this.accumulatorScrollY - this.getHeight()/2);
        movAction.setDuration(timeInSeconds);
        
        this.addAction(movAction);
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
    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    @Override
    public void addAccumulatorScrollY(float value) {
        this.accumulatorScrollY += value;
        this.setY(this.getY()+value);
    }

}
