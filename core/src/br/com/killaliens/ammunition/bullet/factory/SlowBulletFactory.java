package br.com.killaliens.ammunition.bullet.factory;

import br.com.killaliens.ammunition.bullet.firepower.FirePower;
import br.com.killaliens.util.speed.Speed;

public class SlowBulletFactory extends BulletFactory {

    private static final float SPEED_X = 3f;
    private static final float SPEED_Y = 3f;
    private static final int FIREPOWER = 1;
    
    private static final String[] ANIMATION_FRAME_NAMES = {"bullet_normal"};

    @Override
    protected FirePower getFirePower() {
        return new FirePower(FIREPOWER);
    }

    @Override
    protected Speed getSpeed() {
        return new Speed(SPEED_X, SPEED_Y);
    }

    @Override
    protected String[] getAnimationFramesName() {
        return ANIMATION_FRAME_NAMES;
    }
}
