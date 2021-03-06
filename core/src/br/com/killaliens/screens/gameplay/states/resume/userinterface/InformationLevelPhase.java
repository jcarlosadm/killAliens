package br.com.killaliens.screens.gameplay.states.resume.userinterface;

import br.com.killaliens.ship.enemy.enemyspawn.EnemySpawnGenerator;
import br.com.killaliens.util.cache.font.FontCache;
import br.com.killaliens.util.scrollobserver.ScrollObserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * InformationLevelPhase
 * Show information about the phase in superior left corner of the screen
 */
public class InformationLevelPhase extends Actor implements ScrollObserver {

    private static final float POSITION_Y = Gdx.graphics.getHeight() - 10f;
    private static final float POSITION_X = 0f;
    
    private BitmapFont font = FontCache.getFont("statsFontSmall.fnt");
    
    private EnemySpawnGenerator enSpawnGenerator = null;
    
    /**
     * Constructor
     * @param enSpawnGenerator generator of enemies
     */
    public InformationLevelPhase(EnemySpawnGenerator enSpawnGenerator) {
        this.setX(POSITION_X);
        this.setY(POSITION_Y);
        
        this.enSpawnGenerator = enSpawnGenerator;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        String level = this.enSpawnGenerator.getEnemySpawnLevel().toString();
        font.draw(batch, "Phase level: "+ level, this.getX(), this.getY());
    }
    
    @Override
    public void updateScroll(float xDelta, float yDelta) {
        this.setY(this.getY() + yDelta);
    }
}
