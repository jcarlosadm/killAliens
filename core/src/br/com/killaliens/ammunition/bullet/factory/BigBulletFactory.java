package br.com.killaliens.ammunition.bullet.factory;

import br.com.killaliens.ammunition.bullet.firepower.FirePower;
import br.com.killaliens.util.speed.Speed;


public class BigBulletFactory extends BulletFactory {

    private static final int FIREPOWER = 3;
    private static final float SPEEDX = 10f;
    private static final float SPEEDY = 10f;
    
    private static final String[] ANIMATION_NORMAL_FRAMENAMES = {"bigbullet"};
    private static final String[] ANIMATION_DEAD_FRAMENAMES = {"bigbullet"};
    
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
