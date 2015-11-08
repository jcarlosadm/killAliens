package br.com.killaliens.util.cache.images;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import br.com.killaliens.util.atlas.AtlasLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * TextureCache
 * TODO Flyweight pattern
 *
 */
public class TextureCache {

    private static final String IMAGE_FOLDER = "images";

    private static Map<String, TextureRegion> textures = new HashMap<String, TextureRegion>();

    private TextureCache() {
    }

    /**
     * get texture region
     * @param textureRegionName
     * @return texture region instance
     */
    public static synchronized TextureRegion getTextureRegion(
            String textureRegionName) {
        if (textures.containsKey(textureRegionName)) {
            return textures.get(textureRegionName);
        }

        TextureAtlas atlas = AtlasLoader.getTextureAtlas();

        TextureRegion textureRegion = atlas.findRegion(textureRegionName);
        if (textureRegion == null && checkTextureRegionOutOfAtlas(textureRegionName) == true) {
            textureRegion = findOutOfTheAtlas(textureRegionName);
        }
        
        if (textureRegion != null) {
            textures.put(textureRegionName, textureRegion);
        }
        
        return textureRegion;
    }

    /**
     * find texture region out of the atlas
     * @param textureRegionName
     * @return texture region instance
     */
    private static TextureRegion findOutOfTheAtlas(String textureRegionName) {
        TextureRegion textureRegion;
        textureRegion = new TextureRegion(new Texture(
                Gdx.files.internal(IMAGE_FOLDER + File.separator
                        + textureRegionName)));
        textures.put(textureRegionName, textureRegion);
        return textureRegion;
    }

    /**
     * Check if texture out of the atlas exists
     * @param textureRegionName
     * @return true if exists
     */
    private static boolean checkTextureRegionOutOfAtlas(String textureRegionName) {
        return Gdx.files.internal(
                IMAGE_FOLDER + File.separator + textureRegionName).exists();
    }

    /**
     * Dispose all textures
     */
    public static void dispose() {
        for (TextureRegion textureRegion : textures.values()) {
            textureRegion.getTexture().dispose();
        }
    }
}
