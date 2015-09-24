package br.com.killaliens.ship;

import br.com.killaliens.ammunition.AmmunitionTypes;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.animation.BuildAnimation;

public abstract class ShipPropertiesBuilder {
    
    private ShipProperties properties = new ShipProperties();
    
    public ShipProperties getShipProperties(){
        
        this.addAnimation(AnimationTypes.NORMAL_STATE);
        this.addAnimation(AnimationTypes.DEAD);
        
        this.properties.setBasicAmmunition(this.getBasicAmmunition());
        
        this.properties.setPositionX(this.getStartPositionX());
        this.properties.setPositionY(this.getStartPositionY());
        this.properties.setHeight(this.getStartHeight());
        this.properties.setWidth(this.getStartWidth());
        
        this.properties.setOrigin(this.getStartWidth()/2, this.getStartHeight()/2);
        this.properties.setRotation(this.getStartRotation());
        
        this.properties.setLife(this.getStartLife());
        this.properties.setShield(this.getStartShield());
        
        this.properties.setSpeed(this.getStartSpeedX(), this.getStartSpeedY());
        
        return this.properties;
    }
    
    private void addAnimation(AnimationTypes type){
        float animationFrameTime = 0f;
        String[] frameNames = null;
        
        if (type.equals(AnimationTypes.NORMAL_STATE)) {
            animationFrameTime = this.getFrameTimeNormalAnimation();
            frameNames = this.getAnimationNormalFrameNames();
        } else if (type.equals(AnimationTypes.DEAD)) {
            animationFrameTime = this.getFrameTimeDeadAnimation();
            frameNames = this.getAnimationDeadFrameNames();
        }
        
        this.properties.addAnimation(type, BuildAnimation.build(animationFrameTime, frameNames));
    }
    
    protected abstract String[] getAnimationNormalFrameNames();
    
    protected abstract String[] getAnimationDeadFrameNames();
    
    protected abstract float getStartPositionX();
    
    protected abstract float getStartPositionY();
    
    protected abstract float getStartWidth();
    
    protected abstract float getStartHeight();
    
    protected abstract float getStartRotation();
    
    protected abstract float getStartSpeedX();
    
    protected abstract float getStartSpeedY();
    
    protected abstract int getStartLife();
    
    protected abstract int getStartShield();
    
    protected abstract AmmunitionTypes getBasicAmmunition();

    protected abstract float getFrameTimeNormalAnimation();
    
    protected abstract float getFrameTimeDeadAnimation();
}
