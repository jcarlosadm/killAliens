package br.com.killaliens.screens.title.background;

import br.com.killaliens.util.font.FontCache;
import br.com.killaliens.util.image.TextureCache;
import br.com.killaliens.util.text.TextMetrics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CreditsBackground extends Actor {
    
    private static final float SPACE_BETWEEN_LINES = 30f;

    private static final float LEFT_MARGIN = 40f;

    private static final float UP_MARGIN = 70f;

    private static final String IMAGE_NAME = "credits.png";

    private TextureRegion image = TextureCache.getTextureRegion(IMAGE_NAME);
    
    private BitmapFont font = FontCache.getFont("creditsFont.fnt");
    
    // TODO define credits
    private static final String[] TEXTS = {
        "Test 1",
        "Test 2"
    };
    
    private float defaultHeight = 0f;
    
    public CreditsBackground() {
        TextMetrics textMetrics = new TextMetrics();
        textMetrics.setText(font, TEXTS[0]);
        this.defaultHeight = textMetrics.getHeight();
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        batch.draw(this.image, 0, 0);
        
        float yBegin = Gdx.graphics.getHeight() - UP_MARGIN;
        for (int index = 0; index < TEXTS.length; index++) {
            font.draw(batch, TEXTS[index], LEFT_MARGIN, yBegin - index
                    * (defaultHeight + SPACE_BETWEEN_LINES));
        }
    }
    
}
