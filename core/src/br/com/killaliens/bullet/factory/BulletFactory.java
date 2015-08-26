package br.com.killaliens.bullet.factory;

import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Animation;

import br.com.killaliens.ammunition.AmmunitionTypes;
import br.com.killaliens.util.Speed;
import br.com.killaliens.util.animation.AnimationTypes;

/**
 * BulletFactory
 * Using pattern abstract factory
 */
public abstract class BulletFactory {
    
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
    
    protected abstract int getFirePower();
    
    protected abstract Speed getSpeed();
    
    protected abstract String[] getAnimationNormalFrameNames();
    
    protected abstract Map<AnimationTypes, Animation> getStaticAnimationsInstance();
    
    protected abstract void setStaticAnimationsInstance(Map<AnimationTypes, 
            Animation> animations);
}
