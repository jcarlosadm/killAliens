package br.com.killaliens.bullet.factory;

import java.util.HashMap;
import java.util.Map;

import br.com.killaliens.ammunition.AmmunitionTypes;
import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.animation.BuildAnimation;
import br.com.killaliens.util.speed.Speed;

import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * BulletFactory
 * Using pattern factory method (in getFactory)
 * Using pattern template method (in createBullet and in getAnimations)
 */
public abstract class BulletFactory {
    
    private static final float ANIMATION_FRAMETIME = 15f;
    
    protected BulletFactory() {}
    
    public static BulletFactory getFactory(AmmunitionTypes type){
        if (type.equals(AmmunitionTypes.NORMALBULLET)) {
            return new NormalBulletFactory();
        } else if (type.equals(AmmunitionTypes.BIGBULLET)) {
            return new BigBulletFactory();
        } else if (type.equals(AmmunitionTypes.FASTBULLET)) {
            return new FastBulletFactory();
        }
        
        return NullBulletFactory.getInstance();
    }
    
    public void createBullet(CreateBulletParameter parameterObject){
        // TODO implement
    };
    
    protected abstract FirePower getFirePower();
    
    protected abstract Speed getSpeed();
    
    protected abstract String[] getAnimationNormalFramesName();
    
    protected abstract String[] getAnimationDeadFramesName();
    
    protected Map<AnimationTypes, Animation> getAnimations(){
        Map<AnimationTypes, Animation> animations = new HashMap<AnimationTypes, Animation>();
        
        this.buildAnimationType(animations, getAnimationNormalFramesName(), 
                AnimationTypes.NORMAL_STATE);
        this.buildAnimationType(animations, getAnimationDeadFramesName(), 
                AnimationTypes.DEAD);
        
        return animations;
    }
    
    private void buildAnimationType(Map<AnimationTypes, Animation> animations,
            String[] frameNames, AnimationTypes stateName){
        
        Animation animation = BuildAnimation.build(ANIMATION_FRAMETIME, frameNames);
        if (animation != null) {
            animations.put(stateName, animation);
        }
    }
}
