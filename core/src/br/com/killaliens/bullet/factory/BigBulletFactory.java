package br.com.killaliens.bullet.factory;

import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.util.speed.Speed;


public class BigBulletFactory extends BulletFactory {

    private static final String SOUND_NAME = "big_bullet.wav";
    private static final int FIREPOWER = 3;
    private static final float SPEEDX = 10f;
    private static final float SPEEDY = 10f;
    private static final float SPEEDX_ENEMY = 3f;
    private static final float SPEEDY_ENEMY = 3f;
    
    private static final String[] ANIMATION_FRAMENAMES = {"bigbullet"};
    
    protected BigBulletFactory() {}

    @Override
    protected FirePower getFirePower() {
        return new FirePower(FIREPOWER);
    }

    @Override
    protected Speed getSpeed(CreateBulletParameter createBulletParameter) {
        if (createBulletParameter.isBulletEnemy()) {
            return new Speed(SPEEDX_ENEMY, SPEEDY_ENEMY);
        }
        return new Speed(SPEEDX, SPEEDY);
    }

    @Override
    protected String[] getAnimationFramesName() {
        return ANIMATION_FRAMENAMES;
    }

    @Override
    protected String getSoundName() {
        return SOUND_NAME;
    }
}
