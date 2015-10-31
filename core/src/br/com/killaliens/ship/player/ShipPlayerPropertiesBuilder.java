package br.com.killaliens.ship.player;

import br.com.killaliens.bullet.BulletType;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.animation.BuildAnimation;
import br.com.killaliens.util.cache.images.TextureCache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ShipPlayerPropertiesBuilder {

    private static final String[] ANIMATION_NORMAL_FRAMES = { "player" };
    private static final String[] ANIMATION_DEAD_FRAMES = { "player", "player_blank" };
    private static final float FRAMETIME_NORMAL = 0.2f;
    private static final float FRAMETIME_DEAD = 0.2f;
    
    private static final float INITIAL_POSITION_X = Gdx.graphics.getWidth()/2;
    private static final float INITIAL_POSITION_Y = 10f;
    
    private static final float INITIAL_ROTATION = 0f;

    private static final float INITIAL_SPEEDX = 0f;
    private static final float INITIAL_SPEEDY = 0f;

    private static final int INITIAL_MAXLIFE = 40;
    private static final int INITIAL_SHIELD = 1;

    private static final BulletType BASIC_AMMUNITION = BulletType.NORMALBULLET;

    public ShipProperties getShipProperties(){
        
        ShipProperties properties = new ShipProperties();
        
        properties.addAnimation(AnimationTypes.NORMAL_STATE, BuildAnimation
                .build(FRAMETIME_NORMAL, ANIMATION_NORMAL_FRAMES));
        properties.addAnimation(AnimationTypes.DEAD, BuildAnimation
                .build(FRAMETIME_DEAD, ANIMATION_DEAD_FRAMES));
        
        properties.setBasicAmmunition(BASIC_AMMUNITION);
        
        properties.setPositionX(INITIAL_POSITION_X);
        properties.setPositionY(INITIAL_POSITION_Y);
        properties.setHeight(this.getStartHeight());
        properties.setWidth(this.getStartWidth());
        
        properties.setOrigin(this.getStartWidth()/2, this.getStartHeight()/2);
        properties.setRotation(INITIAL_ROTATION);
        
        properties.setLife(INITIAL_MAXLIFE);
        properties.setShield(INITIAL_SHIELD);
        
        properties.setSpeed(INITIAL_SPEEDX, INITIAL_SPEEDY);
        
        return properties;
    }
    
    protected float getStartWidth(){
        TextureRegion textureRegion = TextureCache.getTextureRegion(ANIMATION_NORMAL_FRAMES[0]);
        return (textureRegion == null ? 0 : textureRegion.getRegionWidth());
    }
    
    protected float getStartHeight(){
        TextureRegion textureRegion = TextureCache.getTextureRegion(ANIMATION_NORMAL_FRAMES[0]);
        return (textureRegion == null ? 0 : textureRegion.getRegionHeight());
    }
}
