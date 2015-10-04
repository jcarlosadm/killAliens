package br.com.killaliens.ammunition.bullet.factory;

import br.com.killaliens.ammunition.bullet.firepower.FirePower;
import br.com.killaliens.util.speed.Speed;


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
    protected FirePower getFirePower() {
        return null;
    }

    @Override
    protected Speed getSpeed() {
        return null;
    }

    @Override
    protected String[] getAnimationFramesName() {
        return null;
    }

    @Override
    protected float getRadius() {
        return 0;
    }
}
