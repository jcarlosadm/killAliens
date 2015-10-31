package br.com.killaliens.util.atlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AtlasLoader {
    
    private static final String ATLAS_PATH = "images/images.atlas";
    
    private static TextureAtlas atlasInstance = null;
    
    private AtlasLoader() {}
    
    public static synchronized TextureAtlas getTextureAtlas(){
        if (atlasInstance == null) {
            atlasInstance = new TextureAtlas(Gdx.files.internal(ATLAS_PATH));
        }
        return atlasInstance;
    }
}
