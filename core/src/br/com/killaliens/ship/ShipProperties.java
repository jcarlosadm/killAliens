package br.com.killaliens.ship;

import com.badlogic.gdx.graphics.g2d.Animation;

import br.com.killaliens.ammunition.AmmunitionTypes;
import br.com.killaliens.ship.status.life.Life;
import br.com.killaliens.ship.status.life.NullLife;
import br.com.killaliens.ship.status.shield.NullShield;
import br.com.killaliens.ship.status.shield.Shield;
import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.speed.NullSpeed;
import br.com.killaliens.util.speed.Speed;

public class ShipProperties {
    private float positionX = 0;
    private float positionY = 0;
    private float width = 1;
    private float height = 1;
    
    private float originX = 0;
    private float originY = 0;
    
    private float rotation = 0;

    private Speed speed = NullSpeed.getNullSpeedInstance();

    private Life life = NullLife.getNullLifeInstance();
    private Shield shield = NullShield.getNullShieldInstance();
    
    private AmmunitionTypes basicAmmunition = AmmunitionTypes.NORMALBULLET;
    
    private AnimationManagement aniManagement = new AnimationManagement();

    /**
     * @return the positionX
     */
    public float getPositionX() {
        return this.positionX;
    }

    /**
     * @param positionX
     *            the positionX to set
     */

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    /**
     * @return the positionY
     */
    public float getPositionY() {
        return this.positionY;
    }

    /**
     * @param positionY
     *            the positionY to set
     */
    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    /**
     * @return the width
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * @param width
     *            the width to set
     */
    public void setWidth(float width) {
        if (width > 0) {
            this.width = width;
        }
    }

    /**
     * @return the height
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * @param height
     *            the height to set
     */
    public void setHeight(float height) {
        if (height > 0) {
            this.height = height;
        }
    }

    /**
     * @return the life
     */
    public Life getLife() {
        return this.life;
    }

    /**
     * @param life
     *            the life to set
     */
    public void setLife(int maxLife) {
        this.life = new Life(maxLife);
    }

    /**
     * @return the shield
     */
    public Shield getShield() {
        return this.shield;
    }

    /**
     * @param shield
     *            the shield to set
     */
    public void setShield(int shieldLevel) {
        this.shield = new Shield(shieldLevel);
    }

    /**
     * @return the basicAmmunition
     */
    public AmmunitionTypes getBasicAmmunition() {
        return this.basicAmmunition;
    }

    /**
     * @param basicAmmunition
     *            the basicAmmunition to set
     */
    public void setBasicAmmunition(AmmunitionTypes basicAmmunition) {
        this.basicAmmunition = basicAmmunition;
    }

    /**
     * @return the rotation
     */
    public float getRotation() {
        return this.rotation;
    }

    /**
     * @param rotation the rotation to set
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * @return the originX
     */
    public float getOriginX() {
        return this.originX;
    }

    /**
     * @return the originY
     */
    public float getOriginY() {
        return this.originY;
    }

    /**
     * @param originY the originY to set
     */
    public void setOrigin(float originX, float originY) {
        this.originX = originX;
        this.originY = originY;
    }

    /**
     * @return the speed
     */
    public Speed getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(float speedX, float speedY) {
        this.speed = new Speed(speedX, speedY);
    }
    
    public void addAnimation(AnimationTypes animationType, Animation animation){
        this.aniManagement.addAnimation(animationType, animation);
    }
    
    public AnimationManagement getAnimationData(){
        return this.aniManagement;
    }
}
