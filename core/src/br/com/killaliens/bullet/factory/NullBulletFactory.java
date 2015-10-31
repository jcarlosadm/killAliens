package br.com.killaliens.bullet.factory;

import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.bullet.firepower.NullFirePower;
import br.com.killaliens.util.speed.NullSpeed;
import br.com.killaliens.util.speed.Speed;


public class NullBulletFactory extends BulletFactory {

    private static NullBulletFactory instance = null;
    
    private static final String[] nullString = {""};
    
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
    protected FirePower getFirePower() {
        return NullFirePower.getNullFirePowerInstance();
    }

    @Override
    protected Speed getSpeed(CreateBulletParameter createBulletParameter) {
        return NullSpeed.getNullSpeedInstance();
    }

    @Override
    protected String[] getAnimationFramesName() {
        return nullString;
    }

    @Override
    protected float getRadius() {
        return 0;
    }

    @Override
    protected String getSoundName() {
        return null;
    }
}
