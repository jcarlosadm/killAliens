package br.com.killaliens.ship.enemy.factory;

import br.com.killaliens.ammunition.bullet.BulletType;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.types.EnemyTypes;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.animation.BuildAnimation;
import br.com.killaliens.util.image.TextureCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class EnemyFactory {
    
    protected EnemyFactory() {}
    
    public static EnemyFactory getFactory(EnemyTypes type){
        //TODO implement
        
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
        
        return null;
    }
    
    public EnemyShip getShip(){
        ShipProperties shipProperties = this.buildProperties();
        return this.getShipInstance(shipProperties);
    }
    
    protected abstract EnemyShip getShipInstance(ShipProperties shipProperties);
    
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
    
    private float getStartHeight() {
        TextureRegion textureRegion = TextureCache.getTextureRegion
                (this.getAnimationNormalFrameNames()[0]);
        return (textureRegion == null ? 0 : textureRegion.getRegionHeight());
    }

    private float getStartWidth() {
        TextureRegion textureRegion = TextureCache.getTextureRegion
                (this.getAnimationNormalFrameNames()[0]);
        return (textureRegion == null ? 0 : textureRegion.getRegionWidth());
    }

    protected abstract String[] getAnimationNormalFrameNames();
    
    protected abstract String[] getAnimationDeadFrames();
    
    protected abstract float getAnimationNormalFrameTime();
    
    protected abstract float getAnimationDeadFrameTime();
    
    protected abstract float getRotation();
    
    protected abstract float getSpeedX();
    
    protected abstract float getSpeedY();
    
    protected abstract int getLifeValue();
    
    protected abstract int getShieldValue();
    
    protected abstract BulletType getBasicAmmunition();
}
