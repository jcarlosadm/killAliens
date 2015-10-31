package br.com.killaliens.bullet;

import br.com.killaliens.util.cache.images.TextureCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum BulletType {
    
    NORMALBULLET {
        @Override
        public TextureRegion getTexture() {
            return TextureCache.getTextureRegion(NORMALBULLET_ICON);
        }

        @Override
        public float getReloadTimeInSeconds() {
            return RELOAD_NORMAL;
        }
    },
    BIGBULLET {
        @Override
        public TextureRegion getTexture() {
            return TextureCache.getTextureRegion(BIGBULLET_ICON);
        }

        @Override
        public float getReloadTimeInSeconds() {
            return RELOAD_SLOW;
        }
    },
    FASTBULLET {
        @Override
        public TextureRegion getTexture() {
            return TextureCache.getTextureRegion(FASTBULLET_ICON);
        }

        @Override
        public float getReloadTimeInSeconds() {
            return RELOAD_FAST;
        }
    },
    FLOWERBULLET {
        @Override
        public TextureRegion getTexture() {
            return TextureCache.getTextureRegion(NORMALBULLET_ICON);
        }

        @Override
        public float getReloadTimeInSeconds() {
            return RELOAD_SLOW;
        }
    };
    
    private static final float RELOAD_NORMAL = 0.35f;
    private static final float RELOAD_SLOW = 0.7f;
    private static final float RELOAD_FAST = 0.2f;
    
    private static final String FASTBULLET_ICON = "fastbullet_icon";
    private static final String BIGBULLET_ICON = "bigbullet_icon";
    private static final String NORMALBULLET_ICON = "normalbullet_icon";

    /**
     * Get texture relative to type
     * @return texture
     */
    public abstract TextureRegion getTexture();
    
    public abstract float getReloadTimeInSeconds();
}
