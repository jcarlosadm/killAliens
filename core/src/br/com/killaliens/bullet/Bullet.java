package br.com.killaliens.bullet;

import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.bullet.firepower.NullFirePower;
import br.com.killaliens.explosion.Explosion;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.camera.CheckVisibleOnCamera;
import br.com.killaliens.util.speed.NullSpeed;
import br.com.killaliens.util.speed.Speed;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor {
    
    private Circle limits = new Circle();
    private boolean enemyBullet = false;
    
    private Speed speed = NullSpeed.getNullSpeedInstance();
    private FirePower firePower = NullFirePower.getNullFirePowerInstance();
    
    private AnimationManagement animationData = new AnimationManagement();
    
    private CheckVisibleOnCamera checkVisibleOnCamera = new CheckVisibleOnCamera(this);

    public Bullet(BulletProperties properties) {
        this.limits.setRadius(properties.getRadius());
        
        this.setX(properties.getPositionX());
        this.setY(properties.getPositionY());
        this.setWidth(properties.getRadius()*2);
        this.setHeight(properties.getRadius()*2);
        this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        this.speed = properties.getSpeed();
        
        this.firePower = properties.getFirePower();
        this.enemyBullet = properties.isEnemyBullet();
    }
    
    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
        super.act(delta);
        this.setPosition(this.getX()+this.getSpeedX(), this.getY()+this.getSpeedY());
        this.animationData.advanceTime(delta);
        
        if (!this.checkVisibleOnCamera.actorIsVisible(this.getStage().getCamera())) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        super.draw(batch, parentAlpha);
        
        batch.draw(this.animationData.getCurrentTextureRegion(true), 
                this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), 
                this.getWidth(), this.getHeight(), this.getScaleX(), 
                this.getScaleY(), this.getRotation());
    }
    
    @Override
    public void setX(float x) {
        super.setX(x);
        this.limits.setX(x + this.getWidth()/2);
    }
    
    @Override
    public void setY(float y) {
        super.setY(y);
        this.limits.setY(y + this.getHeight()/2);
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
        if (ship.colliding(this.limits)) {
            ship.getDamage(this.firePower.getDamage());
            this.explode();
            
            return true;
        }
        return false;
    }
    
    private void explode() {
        Explosion explosion = new Explosion(this.getX() + this.getWidth()/2, 
                this.getY() + this.getHeight()/2);
        this.getStage().addActor(explosion);
        
        this.remove();
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
