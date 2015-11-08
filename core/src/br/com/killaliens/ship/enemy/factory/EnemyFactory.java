package br.com.killaliens.ship.enemy.factory;

import br.com.killaliens.bullet.BulletType;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.types.EnemyTypes;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.animation.BuildAnimation;
import br.com.killaliens.util.cache.images.TextureCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * EnemyFactory
 * TODO Abstract factory pattern
 */
public abstract class EnemyFactory {
    
    protected EnemyFactory() {}
    
    /**
     * get EnemyFactory instance
     * @param type type of the enemy
     * @return EnemyFactory instance
     * TODO Factory method pattern
     */
    public static EnemyFactory getFactory(EnemyTypes type){
        
        if (type.equals(EnemyTypes.UFO)) {
            return new UFOFactory();
        }
        else if (type.equals(EnemyTypes.CRAZY_UFO)) {
            return new CrazyUFOFactory();
        }
        else if (type.equals(EnemyTypes.KEEPER)) {
            return new KeeperFactory();
        }
        else if (type.equals(EnemyTypes.CANNON)) {
            return new CannonFactory();
        }
        else if (type.equals(EnemyTypes.DESTROYER)) {
            return new DestroyerFactory();
        }
        else if (type.equals(EnemyTypes.BOSS)) {
            return new BossFactory();
        }
        
        return null;
    }
    
    /**
     * get EnemyShip instance
     * @return EnemyShip instance
     */
    public EnemyShip getShip(){
        ShipProperties shipProperties = this.buildProperties();
        return this.getShipInstance(shipProperties);
    }
    
    /**
     * get ship instance
     * @param shipProperties
     * @return ship instance
     */
    protected abstract EnemyShip getShipInstance(ShipProperties shipProperties);
    
    /**
     * Build ship properties
     * @return ship properties
     */
    private ShipProperties buildProperties(){
        ShipProperties shipProperties = new ShipProperties();
        
        shipProperties.addAnimation(AnimationTypes.NORMAL_STATE, BuildAnimation
                .build(getAnimationNormalFrameTime(), getAnimationNormalFrameNames()));
        shipProperties.addAnimation(AnimationTypes.DEAD, BuildAnimation
                .build(getAnimationDeadFrameTime(), getAnimationDeadFrames()));
        
        shipProperties.setBasicAmmunition(getBasicAmmunition());
        
        shipProperties.setHeight(this.getStartHeight());
        shipProperties.setWidth(this.getStartWidth());
        
        shipProperties.setOrigin(this.getStartWidth()/2, this.getStartHeight()/2);
        shipProperties.setRotation(this.getRotation());
        
        shipProperties.setLife(this.getLifeValue());
        shipProperties.setShield(this.getShieldValue());
        
        shipProperties.setSpeed(this.getSpeedX(), this.getSpeedY());
        
        return shipProperties;
    }
    
    /**
     * @return start height
     */
    private float getStartHeight() {
        TextureRegion textureRegion = TextureCache.getTextureRegion
                (this.getAnimationNormalFrameNames()[0]);
        return (textureRegion == null ? 0 : textureRegion.getRegionHeight());
    }

    /**
     * @return start width
     */
    private float getStartWidth() {
        TextureRegion textureRegion = TextureCache.getTextureRegion
                (this.getAnimationNormalFrameNames()[0]);
        return (textureRegion == null ? 0 : textureRegion.getRegionWidth());
    }

    /**
     * @return animation normal state frame names
     */
    protected abstract String[] getAnimationNormalFrameNames();
    
    /**
     * @return animation dead state frame names
     */
    protected abstract String[] getAnimationDeadFrames();
    
    /**
     * @return animation normal state frame time
     */
    protected abstract float getAnimationNormalFrameTime();
    
    /**
     * @return animation dead frame time
     */
    protected abstract float getAnimationDeadFrameTime();
    
    /**
     * @return rotation
     */
    protected abstract float getRotation();
    
    /**
     * @return speed x
     */
    protected abstract float getSpeedX();
    
    /**
     * @return speed y
     */
    protected abstract float getSpeedY();
    
    /**
     * @return total and current life value
     */
    protected abstract int getLifeValue();
    
    /**
     * @return shield value
     */
    protected abstract int getShieldValue();
    
    /**
     * @return type of basic ammunition
     */
    protected abstract BulletType getBasicAmmunition();
}
