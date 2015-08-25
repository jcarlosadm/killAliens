package br.com.killaliens.bullet;

import br.com.killaliens.util.Speed;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor {
    
    private static final int MINFIREPOWER = 1;
    private static final int MINSPEEDY = 1;
    private static final int MINSPEEDX = 1;
    
    private Rectangle limits = new Rectangle();
    private boolean enemyBullet = false;
    
    private Speed speed = new Speed(MINSPEEDX, MINSPEEDY);
    private int firePower = MINFIREPOWER;
    
    public Bullet(BulletProperties properties) {
        this.setX(properties.getPositionX());
        this.setY(properties.getPositionY());
        this.setWidth(properties.getWidth());
        this.setHeight(properties.getHeight());
        this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        this.speed.setSpeedX(properties.getSpeedX());
        this.speed.setSpeedY(properties.getSpeedY());
        
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
        return this.speed.getSpeedX();
    }
    
    public float getSpeedY(){
        return this.speed.getSpeedY();
    }
    
    public int getFirePower(){
        return this.firePower;
    }
}
