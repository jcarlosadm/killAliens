package br.com.killaliens.bullet.factory;

import br.com.killaliens.bullet.firepower.FirePower;
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
    protected String[] getAnimationNormalFramesName() {
        return null;
    }

    @Override
    protected String[] getAnimationDeadFramesName() {
        return null;
    }

    @Override
    protected float getRadius() {
        // TODO Auto-generated method stub
        return 0;
    }
}
