package br.com.killaliens.util;

public class Speed {
    private float speedX;
    private float speedY;

    public Speed(float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public float getSpeedX() {
        return this.speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return this.speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
}