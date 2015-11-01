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

    public static synchronized TextureRegion getTextureRegion(
            String textureRegionName) {
        if (textures.containsKey(textureRegionName)) {
            return textures.get(textureRegionName);
        }

        TextureAtlas atlas = AtlasLoader.getTextureAtlas();

        TextureRegion textureRegion = atlas.findRegion(textureRegionName);
        if (textureRegion != null) {
            textures.put(textureRegionName, textureRegion);
        } else if (checkImage(textureRegionName) == true) {
            textureRegion = findOutOfTheAtlas(textureRegionName);
        }
        return textureRegion;
    }

    protected static TextureRegion findOutOfTheAtlas(String textureRegionName) {
        TextureRegion textureRegion;
        textureRegion = new TextureRegion(new Texture(
                Gdx.files.internal(IMAGE_FOLDER + File.separator
                        + textureRegionName)));
        textures.put(textureRegionName, textureRegion);
        return textureRegion;
    }

    private static boolean checkImage(String textureRegionName) {
        return Gdx.files.internal(
                IMAGE_FOLDER + File.separator + textureRegionName).exists();
    }

    public static void dispose() {
        for (TextureRegion textureRegion : textures.values()) {
            textureRegion.getTexture().dispose();
        }
    }
}
