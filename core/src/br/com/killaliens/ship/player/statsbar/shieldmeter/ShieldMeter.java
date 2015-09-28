package br.com.killaliens.ship.player.statsbar.shieldmeter;

import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.ship.player.statsbar.StatusBar;
import br.com.killaliens.util.font.FontCache;
import br.com.killaliens.util.image.TextureCache;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ShieldMeter {
    
    private static final String ICON_NAME = "shield_icon";
    private static final String FONT_NAME = "statsFontSmall.fnt";
    
    private static final float RELATIVE_POSITIONX = 135f;
    private static final float RELATIVE_POSITIONY = 43f;
    private static final float ADJUST_TEXT_POSITIONX = 9f;
    private static final float ADJUST_TEXT_POSITIONY = 20f;
    
    private float positionX = 0f, positionY = 0f;
    
    private PlayerShip playerShip = null;
    
    private BitmapFont font = FontCache.getFont(FONT_NAME);
    private TextureRegion textureRegion = TextureCache.getTextureRegion(ICON_NAME);
    
    public ShieldMeter(StatusBar statusBar, PlayerShip playerShip) {
        this.playerShip = playerShip;
        
        this.positionX = statusBar.getX() + RELATIVE_POSITIONX;
        this.positionY = statusBar.getY() + RELATIVE_POSITIONY;
    }
    
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.textureRegion, this.positionX, this.positionY 
                + this.playerShip.getAccumulatorScrollY());
        
        this.font.draw(batch, ""+this.playerShip.getShieldLevel(), this.positionX
                + ADJUST_TEXT_POSITIONX, this.positionY 
                + this.playerShip.getAccumulatorScrollY() + ADJUST_TEXT_POSITIONY);
    }
    
}
