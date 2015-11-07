package br.com.killaliens.bullet;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;

import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.bullet.firepower.NullFirePower;
import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.speed.NullSpeed;
import br.com.killaliens.util.speed.Speed;

public class BulletProperties {
    private boolean enemyBullet = false;
    
    private float positionX = 0;
    private float positionY = 0;
    
    private float radius = 1;
    
    private Speed speed = NullSpeed.getNullSpeedInstance();
    
    private FirePower firePower = NullFirePower.getNullFirePowerInstance();
    
    private AnimationManagement aniManagement = new AnimationManagement();
    
    private Sound sound = null;

    /**
     * @return if bullet is enemy
     */
    public boolean isEnemyBullet() {
        return this.enemyBullet;
    }

    /**
     * @param enemyBullet set if bullet is enemy
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
     * @return Speed instance
     */
    public Speed getSpeed() {
        return this.speed;
    }

    /**
     * @param speed the Speed instance to set
     */
    public void setSpeed(Speed speed) {
        if (speed != null) {
            this.speed = speed;
        }
    }

    /**
     * @return FirePower instance
     */
    public FirePower getFirePower() {
        return this.firePower;
    }

    /**
     * @param firePower the FirePower instance to set
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
    
    /**
     * Add an animation to bullet
     * @param type type of the animation
     * @param animation animation to set
     */
    public void addAnimation(AnimationTypes type, Animation animation){
        this.aniManagement.addAnimation(type, animation);
    }
    
    /**
     * Get animation management
     * @return animation management
     */
    public AnimationManagement getAnimationData(){
        return this.aniManagement;
    }

    /**
     * @return the sound object
     */
    public Sound getSound() {
        return this.sound;
    }

    /**
     * @param sound the sound to set
     */
    public void setSound(Sound sound) {
        this.sound = sound;
    }
}
