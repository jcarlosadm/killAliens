package br.com.killaliens.bullet.factory;

import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.util.speed.Speed;


public class BigBulletFactory extends BulletFactory {

    private static final int FIREPOWER = 1;
    private static final float SPEEDX = 1;
    private static final float SPEEDY = 1;
    
    // TODO define animations
    private static final String[] ANIMATION_NORMAL_FRAMENAMES = {""};
    
    // TODO define animations
    private static final String[] ANIMATION_DEAD_FRAMENAMES = {""};
    
    protected BigBulletFactory() {}

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

}
