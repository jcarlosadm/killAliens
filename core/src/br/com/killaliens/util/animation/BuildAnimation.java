package br.com.killaliens.util.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.killaliens.util.image.AtlasLoader;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BuildAnimation {

    private String atlasPath = "";
    
    public BuildAnimation(String atlasPath) {
        this.atlasPath = atlasPath;
    }
    
    public Animation build(float frametime, String[] frameNames){
        TextureAtlas atlas = AtlasLoader.getTextureAtlas(this.atlasPath);
        
        List<TextureRegion> textureRegions = new ArrayList<TextureRegion>();
        
        TextureRegion region = null;
        for (String frameName : frameNames) {
            region = atlas.findRegion(frameName);
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
