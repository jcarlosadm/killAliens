package br.com.killaliens.bullet.factory;

public class CreateBulletParameter {
    private boolean bulletEnemy = false;
    private float originX = 0;
    private float originY = 0;
    private float rotation = 0;
    private int numBullet = 1;

    /**
     * @return the bulletEnemy
     */
    public boolean isBulletEnemy() {
        return this.bulletEnemy;
    }

    /**
     * @param bulletEnemy the bulletEnemy to set
     */
    public void setBulletEnemy(boolean bulletEnemy) {
        this.bulletEnemy = bulletEnemy;
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
     * @return the numBullet
     */
    public int getNumBullet() {
        return this.numBullet;
    }

    /**
     * @param numBullet the numBullet to set
     */
    public void setNumBullet(int numBullet) {
        if (numBullet > 0) {
            this.numBullet = numBullet;
        }
    }
    
}