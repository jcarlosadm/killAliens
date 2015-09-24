package br.com.killaliens.util.image;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureCache {

    private static Map<String, TextureRegion> textures = new HashMap<String, TextureRegion>();

    private TextureCache() {
    }

    public static synchronized TextureRegion getTextureRegion(
            String textureRegionName) {
        if (textures.containsKey(textureRegionName)) {
            return textures.get(textureRegionName);
        }

        TextureAtlas atlas = AtlasLoader.getTextureAtlas();
        
        TextureRegion texture = atlas.findRegion(textureRegionName);
        if (texture != null) {
            textures.put(textureRegionName, texture);
        }
        return texture;
    }

    // TODO call this before finish the game
    public static void dispose() {
        for (TextureRegion textureRegion : textures.values()) {
            textureRegion.getTexture().dispose();
        }
    }
}
