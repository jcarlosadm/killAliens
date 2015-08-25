package br.com.killaliens.bullet.factory;

import br.com.killaliens.ammunition.AmmunitionTypes;

/**
 * BulletFactory
 * Using pattern abstract factory
 */
public abstract class BulletFactory {
    
    
    public static BulletFactory getFactory(AmmunitionTypes type){
        if (type.equals(AmmunitionTypes.NORMALBULLET)) {
            return new NormalBulletFactory();
        } else if (type.equals(AmmunitionTypes.BIGBULLET)) {
            return new BigBulletFactory();
        } else if (type.equals(AmmunitionTypes.FASTBULLET)) {
            return new FastBulletFactory();
        }
        return null;
    }
    
    public abstract void createBullet(CreateBulletParameter parameterObject);
}
