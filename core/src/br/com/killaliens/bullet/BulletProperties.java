package br.com.killaliens.bullet;

public class BulletProperties {
    private boolean enemyBullet = false;
    
    private float positionX = 0;
    private float positionY = 0;
    private float width = 1;
    private float height = 1;
    
    private float speedX = 1;
    private float speedY = 1;
    
    private int firePower = 1;

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
    public float getSpeedX() {
        return this.speedX;
    }

    /**
     * @param speedX the speedX to set
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
     * @param speedY the speedY to set
     */
    public void setSpeedY(float speedY) {
        if (speedY > 0) {
            this.speedY = speedY;
        }
    }

    /**
     * @return the firePower
     */
    public int getFirePower() {
        return this.firePower;
    }

    /**
     * @param firePower the firePower to set
     */
    public void setFirePower(int firePower) {
        if (firePower > 0) {
            this.firePower = firePower;
        }
    }
    
}
