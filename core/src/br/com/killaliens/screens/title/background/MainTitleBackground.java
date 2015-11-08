package br.com.killaliens.screens.title.background;

import br.com.killaliens.util.cache.font.FontCache;
import br.com.killaliens.util.cache.images.TextureCache;
import br.com.killaliens.util.text.TextMetrics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MainTitleBackground extends Actor {
    
    private static final float UP_MARGIN = 70f;

    private static final String TITLE_NAME = "Kill Aliens";

    private static final String IMAGE_NAME = "title.png";

    private TextureRegion image = TextureCache.getTextureRegion(IMAGE_NAME);
    
    private BitmapFont font = FontCache.getFont("titleFont.fnt");

    private float titleWidth = 0f;

    private float titleHeight = 0f;
    
    /**
     * Constructor
     */
    public MainTitleBackground() {
        TextMetrics textMetrics = new TextMetrics();
        textMetrics.setText(font, TITLE_NAME);
        this.titleWidth = textMetrics.getWidth();
        this.titleHeight = textMetrics.getHeight();
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        batch.draw(this.image, 0, 0);
        font.draw(batch, TITLE_NAME, Gdx.graphics.getWidth()/2 - this.titleWidth/2, 
                Gdx.graphics.getHeight() - this.titleHeight - UP_MARGIN);
    }
    
}
