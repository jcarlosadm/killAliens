package br.com.killaliens.ship.player.statsbar;

import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.util.accumulatorScroll.AccumulatorScroolY;
import br.com.killaliens.util.image.TextureCache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class StatusBar extends Actor implements AccumulatorScroolY {

    private static final String STATSBAR_NAME = "statsbar";

    private PlayerShip playerShip = null;
    
    private TextureRegion statusBarTexture = TextureCache.getTextureRegion(STATSBAR_NAME);
    
    private float accumulatorScrollY = 0f;
    
    public StatusBar(PlayerShip playerShip) {
        this.playerShip = playerShip;
        this.setup();
    }
    
    private void setup() {
        // TODO Auto-generated method stub
        this.setX(Gdx.graphics.getWidth()-10f-this.statusBarTexture.getRegionWidth());
        this.setY(Gdx.graphics.getHeight()-10f-this.statusBarTexture.getRegionHeight());
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        super.draw(batch, parentAlpha);
        batch.draw(this.statusBarTexture, this.getX(), this.getY());
    }
    
    @Override
    public void addAccumulatorScrollY(float value) {
        this.accumulatorScrollY += value;
        this.setY(this.getY()+value);
    }

    @Override
    public float getAccumulatorScrollY() {
        return this.accumulatorScrollY;
    }
    
}
