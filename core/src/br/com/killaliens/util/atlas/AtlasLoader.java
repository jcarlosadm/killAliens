package br.com.killaliens.util.atlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * AtlasLoader
 * TODO Singleton pattern
 */
public class AtlasLoader {
    
    private static final String ATLAS_PATH = "images/images.atlas";
    
    private static TextureAtlas atlasInstance = null;
    
    private AtlasLoader() {}
    
    /**
     * @return texture atlas instance
     */
    public static synchronized TextureAtlas getTextureAtlas(){
        if (atlasInstance == null) {
            atlasInstance = new TextureAtlas(Gdx.files.internal(ATLAS_PATH));
        }
        return atlasInstance;
    }
}
