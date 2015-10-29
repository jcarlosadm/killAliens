package br.com.killaliens.screens.title.background;

import br.com.killaliens.util.image.TextureCache;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MainTitleBackground extends Actor {
    
    private static final String IMAGE_NAME = "background";

    private TextureRegion image = TextureCache.getTextureRegion(IMAGE_NAME);
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        batch.draw(this.image, 0, 0);
    }
    
}
