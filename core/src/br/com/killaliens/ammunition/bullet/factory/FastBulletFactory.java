package br.com.killaliens.ammunition.bullet.factory;

import br.com.killaliens.ammunition.bullet.actions.BulletAction;
import br.com.killaliens.ammunition.bullet.actions.NullBulletAction;
import br.com.killaliens.ammunition.bullet.firepower.FirePower;
import br.com.killaliens.util.speed.Speed;


public class FastBulletFactory extends BulletFactory {

    private static final int FIREPOWER = 1;
    private static final float SPEEDX = 20f;
    private static final float SPEEDY = 20f;
    private static final float SPEEDX_ENEMY = 6f;
    private static final float SPEEDY_ENEMY = 6f;
    
    private static final String[] ANIMATION_FRAMENAMES = {"bullet_normal"};
    
    protected FastBulletFactory() {}

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
    protected BulletAction getActionToBePerformed() {
        return new NullBulletAction();
    }
}
