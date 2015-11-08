package br.com.killaliens.util.speed;

public class Speed {
    private float speedX = 0;
    private float speedY = 0;

    /**
     * Constructor
     * @param speedX
     * @param speedY
     */
    public Speed(float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    /**
     * @return speed x
     */
    public float getSpeedX() {
        return this.speedX;
    }

    /**
     * set speed x
     * @param speedX
     */
    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    /**
     * @return speed y
     */
    public float getSpeedY() {
        return this.speedY;
    }

    /**
     * set speed y
     * @param speedY
     */
    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
}