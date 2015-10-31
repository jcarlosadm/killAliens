package br.com.killaliens.ship.player.statsbar;

import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.ship.player.statsbar.ammunitionmeter.AmmunitionMeter;
import br.com.killaliens.ship.player.statsbar.hpbar.HPBar;
import br.com.killaliens.ship.player.statsbar.shieldmeter.ShieldMeter;
import br.com.killaliens.util.cache.images.TextureCache;
import br.com.killaliens.util.scrollobserver.ScrollObserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class StatusBar extends Actor implements ScrollObserver {

    private static final String STATSBAR_NAME = "statsbar";
    private static final float ALPHA_COLOR = 0.8f;

    private PlayerShip playerShip = null;

    private TextureRegion statusBarTexture = TextureCache.getTextureRegion(STATSBAR_NAME);
    
    private HPBar hpBar = null;
    private ShieldMeter shieldMeter = null;
    private AmmunitionMeter ammunitionMeter = null;

    private float accumulatorScrollY = 0f;

    public StatusBar(PlayerShip playerShip) {
        this.playerShip = playerShip;
        this.setup();
    }

    private void setup() {
        this.setX(Gdx.graphics.getWidth() - 10f
                - this.statusBarTexture.getRegionWidth());
        this.setY(Gdx.graphics.getHeight() - 10f
                - this.statusBarTexture.getRegionHeight());
        
        this.hpBar = new HPBar(this, this.playerShip);
        this.shieldMeter = new ShieldMeter(this, this.playerShip);
        this.ammunitionMeter = new AmmunitionMeter(this, playerShip);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Color color = batch.getColor();
        batch.setColor(color.r, color.g, color.b, ALPHA_COLOR);

        this.hpBar.draw(batch, parentAlpha);
        batch.draw(this.statusBarTexture, this.getX(), this.getY());
        this.shieldMeter.draw(batch, parentAlpha);
        this.ammunitionMeter.draw(batch, parentAlpha);

        batch.setColor(color.r, color.g, color.b, 1f);
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
