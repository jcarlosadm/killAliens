package br.com.killaliens.bullet.factory;

import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.util.speed.Speed;


public class FastBulletFactory extends BulletFactory {

    private static final int FIREPOWER = 1;
    private static final float SPEEDX = 20f;
    private static final float SPEEDY = 20f;
    
    private static final String[] ANIMATION_NORMAL_FRAMENAMES = {"bullet_normal"};
    private static final String[] ANIMATION_DEAD_FRAMENAMES = {"bullet_normal"};
    
    protected FastBulletFactory() {}

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
