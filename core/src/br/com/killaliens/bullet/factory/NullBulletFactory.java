package br.com.killaliens.bullet.factory;

public class NullBulletFactory extends BulletFactory {

    private static NullBulletFactory instance = null;
    
    private NullBulletFactory() {}
    
    public static synchronized NullBulletFactory getInstance(){
        if (instance == null) {
            instance = new NullBulletFactory();
        }
        return instance;
    }
    
    @Override
    public void createBullet(CreateBulletParameter parameterObject) {}

}
