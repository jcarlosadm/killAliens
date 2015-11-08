package br.com.killaliens.screens.title.background;

import br.com.killaliens.util.cache.font.FontCache;
import br.com.killaliens.util.cache.images.TextureCache;
import br.com.killaliens.util.text.TextMetrics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CreditsBackground extends Actor {

    private static final float LEFT_MARGIN = 40f;

    private static final float UP_MARGIN = 70f;

    private static final String IMAGE_NAME = "credits.png";

    private TextureRegion image = TextureCache.getTextureRegion(IMAGE_NAME);
    
    private BitmapFont font = FontCache.getFont("creditsFont.fnt");
    
    private static final String[] TEXT = {
        "Open Game Art:",
        "    http://opengameart.org",
        "Libgdx:",
        "    https://libgdx.badlogicgames.com/",
        "Hiero4 and Texture Packer:",
        "    https://libgdx.badlogicgames.com/tools.html",
        "Stack Overflow:",
        "    http://stackoverflow.com/",
        "GameDev Stack Exchange:",
        "    http://gamedev.stackexchange.com/",
        "Github:",
        "    https://github.com/"
    };
    
    private float defaultHeight = 0f;
    
    /**
     * Constructor
     */
    public CreditsBackground() {
        TextMetrics textMetrics = new TextMetrics();
        textMetrics.setText(font, TEXT[0]);
        this.defaultHeight = textMetrics.getHeight();
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        batch.draw(this.image, 0, 0);
        
        float yBegin = Gdx.graphics.getHeight() - UP_MARGIN;
        for (int index = 0; index < TEXT.length; index++) {
            font.draw(batch, TEXT[index], LEFT_MARGIN, yBegin - index
                    * (defaultHeight + this.defaultHeight/2));
        }
    }
    
}
