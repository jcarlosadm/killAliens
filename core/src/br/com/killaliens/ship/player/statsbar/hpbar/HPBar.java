package br.com.killaliens.ship.player.statsbar.hpbar;

import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.ship.player.statsbar.StatusBar;
import br.com.killaliens.util.cache.font.FontCache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * HPBar
 * Show the Hit Points bar in player status bar
 */
public class HPBar {
    
    private static final float RELATIVE_POSITIONX = 13f;
    private static final float RELATIVE_POSITIONY = 18f;
    private static final float WIDTH = 157f;
    private static final float HEIGHT = 22f;
    private static final float ADJUST_TEXT_POSITIONX = 20f;
    private static final float ADJUST_TEXT_POSITIONY = 17f;
    
    private static final float BAD_MARK = 0.2f;
    private static final float MIDDLE_MARK = 0.6f;
    private static final Color COLOR_GOOD = Color.GREEN;
    private static final Color COLOR_NOT_SO_GOOD = Color.YELLOW;
    private static final Color COLOR_BAD = Color.RED;
    
    private PlayerShip playerShip = null;
    
    private static final String FONT_NAME = "statsFontSmall.fnt";
    private BitmapFont font = FontCache.getFont(FONT_NAME);
    
    private float positionX = 0f, positionY = 0f;
    
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private boolean projectionMatrixSet = false;
    
    /**
     * Constructor
     * @param statusBar StatusBar object
     * @param playerShip PlayerShip object
     */
    public HPBar(StatusBar statusBar, PlayerShip playerShip) {
        this.positionX = statusBar.getX() + RELATIVE_POSITIONX;
        this.positionY = statusBar.getY() + RELATIVE_POSITIONY;
        
        this.playerShip = playerShip;
    }
    
    /**
     * draw the HPBar
     * @param batch place to draw
     * @param parentAlpha
     */
    public void draw(Batch batch, float parentAlpha) {
        
        float relativePlayerHp = this.playerShip.getCurrentLife() 
                / this.playerShip.getMaxLife();
        float adjustedWidth = WIDTH * relativePlayerHp;
        Color currentColor = this.setCurrentColor(relativePlayerHp);
        
        batch.end();
        
        this.setProjectionMatrix(batch);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        
        this.drawWithShapeRenderer(batch, adjustedWidth, currentColor);
        
        Gdx.gl.glDisable(GL20.GL_BLEND);
        
        batch.begin();
        
        drawText(batch);
    }

    /**
     * draw the bar relative to current player hit points
     * @param batch 
     * @param adjustedWidth
     * @param currentColor
     */
    private void drawWithShapeRenderer(Batch batch, float adjustedWidth,
            Color currentColor) {
        this.shapeRenderer.begin(ShapeType.Filled);
        this.shapeRenderer.setColor(currentColor.r, currentColor.g, currentColor.b, 
                batch.getColor().a);
        this.shapeRenderer.rect(this.positionX, this.positionY, adjustedWidth, 
                HEIGHT);
        this.shapeRenderer.end();
    }
    
    /**
     * draw text in hit points bar
     * @param batch
     */
    private void drawText(Batch batch) {
        int currentLife = (int) this.playerShip.getCurrentLife();
        int maxLife = (int) this.playerShip.getMaxLife();
        
        font.draw(batch, currentLife + "/ " + maxLife,
                this.positionX + ADJUST_TEXT_POSITIONX, this.positionY 
                + this.playerShip.getAccumulatorScrollY() + ADJUST_TEXT_POSITIONY);
    }
    
    /**
     * set the projection matrix (only one time)
     * @param batch
     */
    private void setProjectionMatrix(Batch batch) {
        if (!this.projectionMatrixSet) {
            this.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            this.projectionMatrixSet = true;
        }
    }
    
    /**
     * Define color of the bar relative to player current hit points
     * @param relativePlayerHp relative player current hit points
     * @return color of the hit points bar
     */
    private Color setCurrentColor(float relativePlayerHp) {
        Color currentColor = COLOR_GOOD;
        if (relativePlayerHp <= BAD_MARK) {
            currentColor = COLOR_BAD;
        } else if (relativePlayerHp <= MIDDLE_MARK){
            currentColor = COLOR_NOT_SO_GOOD;
        }
        return currentColor;
    }
    
}
