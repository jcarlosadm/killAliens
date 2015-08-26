package br.com.killaliens.bullet.factory;

import java.util.Map;

import br.com.killaliens.util.Speed;
import br.com.killaliens.util.animation.AnimationTypes;

import com.badlogic.gdx.graphics.g2d.Animation;


public class NullBulletFactory extends BulletFactory {

    private static NullBulletFactory instance = null;
    
    private NullBulletFactory() {}
    
    protected static synchronized NullBulletFactory getInstance(){
        if (instance == null) {
            instance = new NullBulletFactory();
        }
        return instance;
    }
    
    @Override
    public void createBullet(CreateBulletParameter parameterObject) {}

    @Override
    protected int getFirePower() {
        return 0;
    }

    @Override
    protected Speed getSpeed() {
        return null;
    }

    @Override
    protected String[] getAnimationNormalFrameNames() {
        return null;
    }

    @Override
    protected Map<AnimationTypes, Animation> getStaticAnimationsInstance() {
        return null;
    }

    @Override
    protected void setStaticAnimationsInstance(
            Map<AnimationTypes, Animation> animations) {}

}
