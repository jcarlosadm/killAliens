package br.com.killaliens.bullet.factory;

import java.util.Map;

import br.com.killaliens.util.Speed;
import br.com.killaliens.util.animation.AnimationTypes;

import com.badlogic.gdx.graphics.g2d.Animation;


public class NormalBulletFactory extends BulletFactory {

    private static final int FIREPOWER = 1;
    private static final float SPEEDX = 1;
    private static final float SPEEDY = 1;
    
    // TODO define animations
    private static final String[] ANIMATION_NORMAL_FRAMENAMES = {""};
    
    private static Map<AnimationTypes, Animation> animationsInstance = null;
    
    protected NormalBulletFactory() {}

    @Override
    protected int getFirePower() {
        return FIREPOWER;
    }

    @Override
    protected Speed getSpeed() {
        return new Speed(SPEEDX, SPEEDY);
    }

    @Override
    protected Map<AnimationTypes, Animation> getStaticAnimationsInstance() {
        return animationsInstance;
    }

    @Override
    protected void setStaticAnimationsInstance(
            Map<AnimationTypes, Animation> animations) {
        animationsInstance = animations;
    }

    @Override
    protected String[] getAnimationNormalFrameNames() {
        return ANIMATION_NORMAL_FRAMENAMES;
    }
}
