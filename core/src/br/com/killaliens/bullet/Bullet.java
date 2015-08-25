package br.com.killaliens.bullet;

import br.com.killaliens.ship.Ship;
import br.com.killaliens.util.Speed;
import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor {
    
    private static final int MINFIREPOWER = 1;
    private static final int MINSPEEDY = 1;
    private static final int MINSPEEDX = 1;
    
    private Rectangle limits = new Rectangle();
    private boolean enemyBullet = false;
    
    private Speed speed = new Speed(MINSPEEDX, MINSPEEDY);
    private FirePower firePower = new FirePower(MINFIREPOWER);
    
    private AnimationManagement animationData = new AnimationManagement();

    public Bullet(BulletProperties properties) {
        this.setX(properties.getPositionX());
        this.setY(properties.getPositionY());
        this.setWidth(properties.getWidth());
        this.setHeight(properties.getHeight());
        this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        this.speed.setSpeedX(properties.getSpeedX());
        this.speed.setSpeedY(properties.getSpeedY());
        
        this.firePower.setFirePower(properties.getFirePower());
        this.enemyBullet = properties.isEnemyBullet();
    }
    
    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
        super.act(delta);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        super.draw(batch, parentAlpha);
        
        this.animationData.advanceTime(Gdx.graphics.getDeltaTime());
        batch.draw(this.animationData.getCurrentTextureRegion(true), 
                this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), 
                this.getWidth(), this.getHeight(), this.getScaleX(), 
                this.getScaleY(), this.getRotation());
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
        return this.firePower.getFirePower();
    }
    
    public boolean colliding(Ship ship){
        if (ship.getLimits().overlaps(this.limits)) {
            // TODO implement
            return true;
        }
        return false;
    }
    
    public boolean setCurrentAnimation(AnimationTypes key){
        return this.animationData.setCurrentAnimation(key);
    }
    
    public void addAnimation(AnimationTypes key, Animation animation){
        this.animationData.addAnimation(key, animation);
    }
    
    public boolean removeAnimation(AnimationTypes key){
        return this.animationData.removeAnimation(key);
    }
}
