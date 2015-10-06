package br.com.killaliens.ammunition.bullet.factory;

import br.com.killaliens.ammunition.bullet.firepower.FirePower;
import br.com.killaliens.util.speed.Speed;

public class FlowerBulletFactory extends BulletFactory {

    private static final float ROTATION_INCREMENT = 25f;

    private static final String[] ANIMATION_FRAME_NAMES = {"bullet_normal"};
    
    private static final float SPEED_X = 3f;
    private static final float SPEED_Y = 3f;
    private static final int FIREPOWER_VALUE = 1;

    @Override
    protected FirePower getFirePower() {
        return new FirePower(FIREPOWER_VALUE);
    }

    @Override
    protected Speed getSpeed(CreateBulletParameter cBulletParameter) {
        return new Speed(SPEED_X, SPEED_Y);
    }

    @Override
    protected String[] getAnimationFramesName() {
        return ANIMATION_FRAME_NAMES;
    }
    
    @Override
    protected float setAngleInRadians(
            CreateBulletParameter createBulletParameter) {
        
        float rotation = createBulletParameter.getRotation();
        int bulletNumber = createBulletParameter.getNumBullet();
        if (bulletNumber % 2 == 0) {
            rotation = rotation + (bulletNumber / 2) * ROTATION_INCREMENT;
        } else {
            rotation = rotation - ((bulletNumber - 1) / 2) * ROTATION_INCREMENT;
        }
        return ((float) Math.toRadians(rotation));
    }
    
    @Override
    protected float getIncrementFromNumBullet(int numBullet) {
        return 0f;
    }

}
