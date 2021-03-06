package br.com.killaliens.ship.player.statsbar.ammunitionmeter;

import br.com.killaliens.ammunition.Ammunition;
import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.ship.player.statsbar.StatusBar;
import br.com.killaliens.util.cache.font.FontCache;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * AmmunitionMeter
 * Show type and total of Ammunition in player status bar
 */
public class AmmunitionMeter {
    
    private static final float RELATIVE_POSITIONX = 190f;
    private static final float RELATIVE_POSITIONY = 40f;
    private static final float ADJUST_LEVEL_POSITIONX = 28f;
    private static final float ADJUST_LEVEL_POSITIONY = 35f;
    private static final float ADJUST_TOTAL_POSITIONX = 0f;
    private static final float ADJUST_TOTAL_POSITIONY = 5f;
    
    private static final String FONT_NAME = "statsFontSmall.fnt";
    private static final String TEXT_LV = "LV";
    private static final String TEXT_TOTAL = "bullets";
    
    private PlayerShip playerShip = null;
    
    private float positionX = 0f, positionY = 0f;
    
    private BitmapFont font = FontCache.getFont(FONT_NAME);
    
    /**
     * Constructor
     * @param statusBar StatusBar object
     * @param playerShip PlayerShip object
     */
    public AmmunitionMeter(StatusBar statusBar, PlayerShip playerShip) {
        this.playerShip = playerShip;
        
        this.positionX = statusBar.getX() + RELATIVE_POSITIONX;
        this.positionY = statusBar.getY() + RELATIVE_POSITIONY;
    }
    
    /**
     * draw the ammunition meter
     * @param batch place to draw
     * @param parentAlpha
     */
    public void draw(Batch batch, float parentAlpha) {
        Ammunition ammunition = this.playerShip.getTopAmmunition();
        batch.draw(ammunition.getType().getIconTexture(), this.positionX, this.positionY 
                + this.playerShip.getAccumulatorScrollY());
        
        font.draw(batch, TEXT_LV+" "+ammunition.getLevel(), this.positionX 
                + ADJUST_LEVEL_POSITIONX, this.positionY 
                + this.playerShip.getAccumulatorScrollY() + ADJUST_LEVEL_POSITIONY);
        
        String textTotal = ammunition.getCurrentBullets()+"";
        if (ammunition.isInfinity()) {
            textTotal = "-";
        }
        font.draw(batch, TEXT_TOTAL+" "+textTotal, this.positionX + ADJUST_TOTAL_POSITIONX, 
                this.positionY + this.playerShip.getAccumulatorScrollY() 
                + ADJUST_TOTAL_POSITIONY);
    }
}
