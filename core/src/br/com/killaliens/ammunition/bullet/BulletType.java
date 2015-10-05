package br.com.killaliens.ammunition.bullet;

import br.com.killaliens.util.image.TextureCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum BulletType {
    
    NORMALBULLET {
        @Override
        public TextureRegion getTexture() {
            return TextureCache.getTextureRegion(NORMALBULLET_ICON);
        }
    },
    BIGBULLET {
        @Override
        public TextureRegion getTexture() {
            return TextureCache.getTextureRegion(BIGBULLET_ICON);
        }
    },
    FASTBULLET {
        @Override
        public TextureRegion getTexture() {
            return TextureCache.getTextureRegion(FASTBULLET_ICON);
        }
    };
    
    private static final String FASTBULLET_ICON = "fastbullet_icon";
    private static final String BIGBULLET_ICON = "bigbullet_icon";
    private static final String NORMALBULLET_ICON = "normalbullet_icon";

    /**
     * Get texture relative to type
     * @return texture
     */
    public abstract TextureRegion getTexture();
}
