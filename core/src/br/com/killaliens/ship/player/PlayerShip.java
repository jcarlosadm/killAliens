package br.com.killaliens.ship.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.util.touch.TouchState;

public class PlayerShip extends Ship {

    public PlayerShip(ShipProperties properties) {
        super(properties);
        this.setIfIsEnemy(false);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
        super.act(delta);
        
        if (TouchState.getDownState()) {
            this.moveToLocation(TouchState.getPosition(), delta);
        }
    }
    
    public void moveToLocation(Vector2 location, float timeInSeconds) {
        MoveToAction movAction = new MoveToAction();
        movAction.setPosition(location.x, location.y);
        movAction.setDuration(timeInSeconds);
        
        this.addAction(movAction);
    }

}
