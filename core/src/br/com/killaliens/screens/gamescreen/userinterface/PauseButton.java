package br.com.killaliens.screens.gamescreen.userinterface;

import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.util.image.TextureCache;
import br.com.killaliens.util.scrollobserver.ScrollObserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class PauseButton extends Actor implements ScrollObserver {
    
    private static final float MINIMUM_DISTANCE_TO_ACTOR = 100f;
    private static final String PAUSE_ICON_IMAGE = "pause_icon";
    
    private TextureRegion image = TextureCache.getTextureRegion(PAUSE_ICON_IMAGE);
    
    private PlayerShip playerShip = null;
    
    private boolean touched = false;
    
    public PauseButton(PlayerShip playerShip) {
        this.playerShip = playerShip;
        
        this.setX(0);
        this.setY(0);
        this.setWidth(this.image.getRegionWidth());
        this.setHeight(this.image.getRegionHeight());
        this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        this.setTouchable(Touchable.enabled);
        this.setVisible(true);
        this.addListener(new PauseTouchListener(this));
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
        
        this.checkDistanceToPlayer();
        this.touchedAction();
    }

    private void touchedAction() {
        if (this.touched == true) {
            // TODO change action
            System.out.println("touched!!");
        }
    }

    private void checkDistanceToPlayer() {
        float distance = Vector2.dst(this.getX(), this.getY(), this.playerShip.getX(),
                this.playerShip.getY());
        
        if (distance <= MINIMUM_DISTANCE_TO_ACTOR) {
            this.changePosition();
        }
    }
    
    private void changePosition() {
        if (this.getX() == 0) {
            this.setX(Gdx.graphics.getWidth() - this.getWidth());
        } else {
            this.setX(0);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        batch.draw(this.image, this.getX(), this.getY());
    }
    
    protected void setTouched(boolean state){
        this.touched = state;
    }

    @Override
    public void updateScroll(float xDelta, float yDelta) {
        this.setY(this.getY() + yDelta);
    }
    
}
