package br.com.killaliens.bullet;

import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.bullet.firepower.NullFirePower;
import br.com.killaliens.util.speed.NullSpeed;
import br.com.killaliens.util.speed.Speed;

public class BulletProperties {
    private boolean enemyBullet = false;
    
    private float positionX = 0;
    private float positionY = 0;
    
    private float radius = 1;
    
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

    /**
     * @return the radius
     */
    public float getRadius() {
        return this.radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }
    
}
