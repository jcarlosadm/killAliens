package br.com.killaliens.ammunition;

import br.com.killaliens.util.image.TextureCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum AmmunitionTypes {
    NORMALBULLET {
        @Override
        public TextureRegion getTexture() {
            return TextureCache.getTextureRegion("normalbullet_icon");
        }
    },
    BIGBULLET {
        @Override
        public TextureRegion getTexture() {
            return TextureCache.getTextureRegion("bigbullet_icon");
        }
    },
    FASTBULLET {
        @Override
        public TextureRegion getTexture() {
            return TextureCache.getTextureRegion("fastbullet_icon");
        }
    };
    
    /**
     * Get texture relative to type
     * @return texture
     */
    public abstract TextureRegion getTexture();
}
