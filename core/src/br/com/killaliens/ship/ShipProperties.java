package br.com.killaliens.ship;

import br.com.killaliens.ammunition.AmmunitionTypes;
import br.com.killaliens.status.Life;

public class ShipProperties {
    private float positionX = 0;
    private float positionY = 0;
    private float width = 1;
    private float height = 1;
    
    private float originX = 0;
    private float originY = 0;
    
    private float rotation = 0;

    private float speedX = 1;
    private float speedY = 1;

    private Life life = null;
    private int shield = 0;
    private AmmunitionTypes basicAmmunition = AmmunitionTypes.NORMALBULLET;

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
     * @return the speedX
     */
    public float getSpeedX() {
        return this.speedX;
    }

    /**
     * @param speedX
     *            the speedX to set
     */
    public void setSpeedX(float speedX) {
        if (speedX > 0) {
            this.speedX = speedX;
        }
    }

    /**
     * @return the speedY
     */

    public float getSpeedY() {
        return this.speedY;
    }

    /**
     * @param speedY
     *            the speedY to set
     */

    public void setSpeedY(float speedY) {
        if (speedY > 0) {
            this.speedY = speedY;
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
    public void setLife(Life life) {
        this.life = life;
    }

    /**
     * @return the shield
     */
    public int getShield() {
        return this.shield;
    }

    /**
     * @param shield
     *            the shield to set
     */
    public void setShield(int shield) {
        this.shield = shield;
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
     * @param originX the originX to set
     */
    public void setOriginX(float originX) {
        this.originX = originX;
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
    public void setOriginY(float originY) {
        this.originY = originY;
    }
}
