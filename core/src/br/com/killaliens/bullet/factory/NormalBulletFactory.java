package br.com.killaliens.bullet.factory;

import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.util.image.TextureCache;
import br.com.killaliens.util.speed.Speed;

public class NormalBulletFactory extends BulletFactory {

    private static final int FIREPOWER = 1;
    private static final float SPEEDX = 10f;
    private static final float SPEEDY = 10f;
    
    // TODO define animations
    private static final String[] ANIMATION_NORMAL_FRAMENAMES = {"bullet_normal"};
    
    // TODO define animations
    private static final String[] ANIMATION_DEAD_FRAMENAMES = {"bullet_normal"};
    
    protected NormalBulletFactory() {}

    @Override
    protected FirePower getFirePower() {
        return new FirePower(FIREPOWER);
    }

    @Override
    protected Speed getSpeed() {
        return new Speed(SPEEDX, SPEEDY);
    }

    @Override
    protected String[] getAnimationNormalFramesName() {
        return ANIMATION_NORMAL_FRAMENAMES;
    }

    @Override
    protected String[] getAnimationDeadFramesName() {
        return ANIMATION_DEAD_FRAMENAMES;
    }

    @Override
    protected float getRadius() {
        return TextureCache.getTextureRegion(ANIMATION_NORMAL_FRAMENAMES[0]).getRegionWidth()/2;
    }
}
