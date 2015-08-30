package br.com.killaliens.util.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.killaliens.util.image.TextureCache;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BuildAnimation {
    
    private BuildAnimation() {}
    
    public static Animation build(float frametime, String[] frameNames){
        List<TextureRegion> textureRegions = new ArrayList<TextureRegion>();
        
        TextureRegion region = null;
        for (String frameName : frameNames) {
            region = TextureCache.getTextureRegion(frameName);
            if (region != null) {
                textureRegions.add(region);
            }
        }
        
        if (textureRegions.isEmpty()) {
            return null;
        }
        
        return new Animation(frametime, Arrays.copyOf(textureRegions.toArray(), 
                textureRegions.size(), TextureRegion[].class));
    }
    
}
