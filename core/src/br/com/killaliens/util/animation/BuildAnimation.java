package br.com.killaliens.util.animation;

import br.com.killaliens.util.cache.images.TextureCache;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BuildAnimation {
    
    private BuildAnimation() {}
    
    public static Animation build(float frametime, String[] frameNames){
        TextureRegion[] textureRegions = new TextureRegion[frameNames.length];
        
        TextureRegion region = null;
        for (int textureIndex = 0; textureIndex < textureRegions.length; textureIndex++) {
            region = TextureCache.getTextureRegion(frameNames[textureIndex]);
            if (region != null) {
                textureRegions[textureIndex] = region;
            } else {
                return null;
            }
        }
        
        return new Animation(frametime, textureRegions);
    }
    
}
