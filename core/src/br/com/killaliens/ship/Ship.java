package br.com.killaliens.ship;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import br.com.killaliens.ammunition.Ammunition;
import br.com.killaliens.ammunition.AmmunitionFactory;
import br.com.killaliens.bullet.Bullet;
import br.com.killaliens.status.Life;
import br.com.killaliens.util.AnimationTypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Ship extends Actor {
    
    private float speedX = 1;
    private float speedY = 1;
    
    private Life life = null;
    private int shield = 0;
    
    private boolean shooting = false;
    
    private Stack<Ammunition> ammunitions = new Stack<Ammunition>();
    
    private Rectangle limits = new Rectangle();
    
    private Map<AnimationTypes, Animation> animations = 
            new HashMap<AnimationTypes, Animation>();
    
    private Animation currentAnimation = null;
    
    private float elapsedTime = 0;
    
    /**
     * Constructor
     * @param properties properties of the ship
     */
    public Ship(ShipProperties properties) {
        this.setX(properties.getPositionX());
        this.setY(properties.getPositionY());
        this.setHeight(properties.getHeight());
        this.setWidth(properties.getWidth());
        setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        this.speedX = properties.getSpeedX();
        this.speedY = properties.getSpeedY();
        this.life = properties.getLife();
        this.shield = properties.getShield();
        
        this.addAmmunition(AmmunitionFactory.MakeAmmunition(
                properties.getBasicAmmunition()));
    }
    
    @Override
    public void setX(float x) {
        super.setX(x);
        this.limits.setX(x);
    }
    
    @Override
    public void setY(float y) {
        super.setY(y);
        this.limits.setY(y);
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
    
    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
        super.act(delta);
        
        if (this.shooting == true) {
            this.shoot();
            this.shooting = false;
        }
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        super.draw(batch, parentAlpha);
        this.elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(this.currentAnimation.getKeyFrame(this.elapsedTime, true), 
                this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), 
                this.getWidth(), this.getHeight(), this.getScaleX(), 
                this.getScaleY(), this.getRotation());
    }
    
    /**
     * @return the speedX
     */
    public float getSpeedX() {
        return this.speedX;
    }

    /**
     * @return the speedY
     */
    public float getSpeedY() {
        return this.speedY;
    }

    /**
     * @return the life
     */
    public float getCurrentLife() {
        return this.life.getCurrentLife();
    }
    
    public float getMaxLife(){
        return this.life.getMaxLife();
    }
    
    /**
     * Get damage (must be greater than zero)
     * @param damage
     */
    public void getDamage(int damage){
        
        int totalDamage = damage - this.shield;
        
        if (totalDamage > 0) {
            this.life.modifyCurrentLife(totalDamage*(-1));
        }
    }
    
    /**
     * Get heal (must be greater than zero)
     * @param heal
     */
    public void getHeal(int heal){
        if (heal > 0) {
            this.life.modifyCurrentLife(heal);
        }
    }
    
    public boolean isDead(){
        return this.life.isDead();
    }

    /**
     * @return the shield
     */
    public int getShield() {
        return this.shield;
    }
    
    /**
     * Add ammunition to ammunition stack
     * @param ammunition
     */
    public void addAmmunition(Ammunition ammunition){
        this.ammunitions.push(ammunition);
    }
    
    /**
     * remove top ammunition, except if is latest one
     */
    public boolean removeTopAmmunition(){
        if (this.ammunitions.size() > 1) {
            this.ammunitions.pop();
            return true;
        }
        
        return false;
    }

    /**
     * set shooting status
     * @param shooting the shooting to set
     */
    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
    
    private void shoot(){
        // TODO implement
    }
    
    public boolean colliding(Bullet bullet){
        if (bullet.getLimits().overlaps(this.limits)) {
            return true;
        }
        return false;
    }
    
    public void addAnimation(AnimationTypes key, Animation animation){
        this.animations.put(key, animation);
    }
    
    public boolean removeAnimation(AnimationTypes key){
        if (this.animations.containsKey(key)) {
            this.animations.remove(key);
            return true;
        }
        
        return false;
    }
    
    public boolean setAnimation(AnimationTypes key){
        if (this.animations.containsKey(key)) {
            this.currentAnimation = this.animations.get(key);
            this.elapsedTime = 0;
            return true;
        }
        
        return false;
    }
}
