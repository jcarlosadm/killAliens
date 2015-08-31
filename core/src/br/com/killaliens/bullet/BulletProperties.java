package br.com.killaliens.bullet;

import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.bullet.firepower.NullFirePower;
import br.com.killaliens.util.speed.NullSpeed;
import br.com.killaliens.util.speed.Speed;

public class BulletProperties {
    private boolean enemyBullet = false;
    
    private float positionX = 0;
    private float positionY = 0;
    private float width = 1;
    private float height = 1;
    
    private Speed speed = NullSpeed.getNullSpeedInstance();
    
    private FirePower firePower = NullFirePower.getNullFirePowerInstance();

    /**
     * @return the enemyBullet
     */
    public boolean isEnemyBullet() {
        return this.enemyBullet;
    }

    /**
     * @param enemyBullet the enemyBullet to set
     */
    public void setEnemyBullet(boolean enemyBullet) {
        this.enemyBullet = enemyBullet;
    }

    /**
     * @return the positionX
     */
    public float getPositionX() {
        return this.positionX;
    }

    /**
     * @param positionX the positionX to set
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
     * @param positionY the positionY to set
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
     * @param width the width to set
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
     * @param height the height to set
     */
    public void setHeight(float height) {
        if (height > 0) {
            this.height = height;
        }
    }

    /**
     * @return the speedX
     */
    public Speed getSpeed() {
        return this.speed;
    }

    /**
     * @param speedX the speedX to set
     */
    public void setSpeed(Speed speed) {
        if (speed != null) {
            this.speed = speed;
        }
    }

    /**
     * @return the firePower
     */
    public FirePower getFirePower() {
        return this.firePower;
    }

    /**
     * @param firePower the firePower to set
     */
    public void setFirePower(FirePower firePower) {
        if (firePower != null) {
            this.firePower = firePower;
        }
    }
    
}
