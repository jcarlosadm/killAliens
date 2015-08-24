package br.com.killaliens.bullet;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor {
    private Rectangle limits = new Rectangle();
    private boolean enemyBullet = false;
    
    private float speedX = 1;
    private float speedY = 1;
    
    private int firePower = 1;
    
    public Bullet(BulletProperties properties) {
        this.setX(properties.getPositionX());
        this.setY(properties.getPositionY());
        this.setWidth(properties.getWidth());
        this.setHeight(properties.getHeight());
        this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        this.speedX = properties.getSpeedX();
        this.speedY = properties.getSpeedY();
        
        this.firePower = properties.getFirePower();
        this.enemyBullet = properties.isEnemyBullet();
    }
    
    @Override
    public void setX(float x) {
        super.setX(x);
        this.limits.setX(this.getX());
    }
    
    @Override
    public void setY(float y) {
        super.setY(y);
        this.limits.setY(this.getY());
    }
    
    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        this.limits.setWidth(width);
    }
    
    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        this.limits.setHeight(height);
    }
    
    public final Rectangle getLimits(){
        return this.limits;
    }
    
    public boolean isEnemyBullet(){
        return this.enemyBullet;
    }
    
    public float getSpeedX(){
        return this.speedX;
    }
    
    public float getSpeedY(){
        return this.speedY;
    }
    
    public int getFirePower(){
        return this.firePower;
    }
}
