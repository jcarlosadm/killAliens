package br.com.killaliens.util.image;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AtlasLoader {
    
    private static String currentAtlasPath = "";
    
    private static TextureAtlas atlasInstance = null;
    
    private AtlasLoader() {}
    
    public static synchronized TextureAtlas getTextureAtlas(String atlasPath){
        if (atlasInstance == null || !currentAtlasPath.equals(atlasPath)) {
            atlasInstance = new TextureAtlas(Gdx.files.internal(atlasPath));
            currentAtlasPath = atlasPath;
        }
        return atlasInstance;
    }
}
