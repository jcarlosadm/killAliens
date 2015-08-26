package br.com.killaliens.bullet.factory;

import java.util.Map;

import br.com.killaliens.util.Speed;
import br.com.killaliens.util.animation.AnimationTypes;

import com.badlogic.gdx.graphics.g2d.Animation;


public class FastBulletFactory extends BulletFactory {

    protected FastBulletFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected int getFirePower() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected Speed getSpeed() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String[] getAnimationNormalFrameNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Map<AnimationTypes, Animation> getStaticAnimationsInstance() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void setStaticAnimationsInstance(
            Map<AnimationTypes, Animation> animations) {
        // TODO Auto-generated method stub
        
    }
}
