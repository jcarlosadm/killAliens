package br.com.killaliens.ship;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import br.com.killaliens.ammunition.Ammunition;
import br.com.killaliens.bullet.factory.CreateBulletParameter;
import br.com.killaliens.ship.status.life.Life;
import br.com.killaliens.ship.status.life.NullLife;
import br.com.killaliens.ship.status.management.StatusManagement;
import br.com.killaliens.ship.status.shield.NullShield;
import br.com.killaliens.ship.status.shield.Shield;
import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.speed.NullSpeed;
import br.com.killaliens.util.speed.Speed;
import br.com.killaliens.util.vertices.VerticesBuilder;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

/**
 * Ship
 */
public abstract class Ship extends Actor {

    private static final int BASIC_AMMO_LEVEL = 1;

    private Speed speed = NullSpeed.getNullSpeedInstance();

    private Life life = NullLife.getNullLifeInstance();
    private Shield shield = NullShield.getNullShieldInstance();

    private boolean shooting = false;

    private Stack<Ammunition> ammunitions = new Stack<Ammunition>();

    private Polygon limits = new Polygon();

    private AnimationManagement animationData = new AnimationManagement();

    private boolean enemy = false;
    
    private Map<AnimationTypes, StatusManagement> statusTypes = 
            new HashMap<AnimationTypes, StatusManagement>();

    private StatusManagement currentStatus = null;

    /**
     * Constructor
     * 
     * @param properties
     *            properties of the ship
     */
    public Ship(ShipProperties properties) {
        this.limits.setVertices(this.buildVertices(properties.getWidth(),
                properties.getHeight()));

        this.setPosition(properties.getPositionX(), properties.getPositionY());
        this.setSize(properties.getWidth(), properties.getHeight());

        this.setOrigin(properties.getOriginX(), properties.getOriginY());
        this.setRotation(properties.getRotation());

        setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        this.speed = properties.getSpeed();
        this.life = properties.getLife();
        this.shield = properties.getShield();

        Ammunition ammunition = Ammunition.getInfinityAmmunition(properties.getBasicAmmunition(),
                BASIC_AMMO_LEVEL);
        this.addAmmunition(ammunition);
        
        this.animationData = properties.getAnimationData();
        this.animationData.setCurrentAnimation(AnimationTypes.NORMAL_STATE);
    }

    private float[] buildVertices(float width, float height) {
        
        VerticesBuilder vBuilder = new VerticesBuilder();
        vBuilder.addPoint(0f, 0f);
        vBuilder.addPoint(width, 0f);
        vBuilder.addPoint(width, height);
        vBuilder.addPoint(0f, height);
        
        return vBuilder.buildVertices();
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        this.limits.setPosition(x, this.getY());
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        this.limits.setPosition(this.getX(), y);
    }
    
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        this.limits.setPosition(x, y);
    }

    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        this.limits.setRotation(this.getRotation());
    }
    
    @Override
    public void rotateBy(float amountInDegrees) {
        super.rotateBy(amountInDegrees);
        this.limits.rotate(amountInDegrees);
    }

    @Override
    public void setOrigin(float originX, float originY) {
        super.setOrigin(originX, originY);
        this.limits.setOrigin(originX, originY);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.animationData.advanceTime(delta);

        if (this.shooting == true) {
            this.shoot(delta);
            this.shooting = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        batch.draw(this.animationData.getCurrentTextureRegion(true),
                this.getX(), this.getY(), this.getOriginX(), this.getOriginY(),
                this.getWidth(), this.getHeight(), this.getScaleX(),
                this.getScaleY(), this.getRotation());
    }

    /**
     * @return the speedX
     */
    public float getSpeedX() {
        return this.speed.getSpeedX();
    }
    
    public void setSpeedX(float speedX) {
        this.speed.setSpeedX(speedX);
    }

    /**
     * @return the speedY
     */
    public float getSpeedY() {
        return this.speed.getSpeedY();
    }
    
    public void setSpeedY(float speedY){
        this.speed.setSpeedY(speedY);
    }

    /**
     * @return the life
     */
    public float getCurrentLife() {
        return this.life.getCurrentLife();
    }

    public float getMaxLife() {
        return this.life.getMaxLife();
    }

    /**
     * Get damage (must be greater than zero)
     * 
     * @param damage
     */
    public void getDamage(int damage) {

        int totalDamage = damage - this.shield.calcProtectionValue();

        if (totalDamage > 0) {
            this.life.downLife(totalDamage);
        }
    }

    /**
     * Get heal (must be greater than zero)
     * 
     * @param heal
     */
    public void getHeal(int heal) {
        if (heal > 0) {
            this.life.upLife(heal);
        }
    }

    public boolean isDead() {
        return this.life.isDead();
    }

    /**
     * @return the shield
     */
    public int getShieldLevel() {
        return this.shield.getShieldLevel();
    }
    
    public void upShieldLevel() {
        this.shield.setShieldLevel(this.getShieldLevel() + 1);
    }

    /**
     * Add ammunition to ammunition stack
     * 
     * @param ammunition
     */
    public void addAmmunition(Ammunition ammunition) {
        this.ammunitions.push(ammunition);
    }

    /**
     * remove top ammunition, except if is latest one
     */
    public boolean removeTopAmmunition() {
        if (this.ammunitions.size() > 1) {
            this.ammunitions.pop();
            return true;
        }

        return false;
    }
    
    public Ammunition getTopAmmunition(){
        return this.ammunitions.peek();
    }

    /**
     * set shooting status
     * 
     * @param shooting
     *            the shooting to set
     */
    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    private void shoot(float deltaTime) {
        CreateBulletParameter createBulletParameters = new CreateBulletParameter();
        
        this.buildCreateBulletParameter(createBulletParameters);
        
        this.ammunitions.peek().makeBullets(deltaTime, createBulletParameters);

        if (!this.ammunitions.peek().hasAmmunition()) {
            this.removeTopAmmunition();
        }
    }

    private void buildCreateBulletParameter(
            CreateBulletParameter createBulletParameters) {
        createBulletParameters.setBulletEnemy(this.isEnemy());
        createBulletParameters.setOriginX(this.getX() + this.getOriginX());
        createBulletParameters.setOriginY(this.getY() + this.getOriginY());
        createBulletParameters.setRotation(this.getRotation());
        createBulletParameters.setParentStage(this.getStage());
    }
    
    public Polygon getLimits(){
        return this.limits;
    }

    public void addAnimation(AnimationTypes key, Animation animation) {
        this.animationData.addAnimation(key, animation);
    }

    public boolean removeAnimation(AnimationTypes key) {
        return this.animationData.removeAnimation(key);
    }

    public boolean setCurrentAnimation(AnimationTypes key) {
        return this.animationData.setCurrentAnimation(key);
    }

    public boolean isEnemy() {
        return this.enemy;
    }

    public void setIfIsEnemy(boolean enemy) {
        this.enemy = enemy;
    }
    
    public void moveToLocation(float x, float y, float timeInSeconds) {
        MoveToAction movAction = new MoveToAction();
        movAction.setPosition(x, y);
        movAction.setDuration(timeInSeconds);
        
        this.addAction(movAction);
    }
    
    protected void addStatus(AnimationTypes type, StatusManagement status){
        this.statusTypes.put(type, status);
    }
    
    protected boolean setCurrentStatus(AnimationTypes key){
        if (this.statusTypes.containsKey(key)) {
            this.currentStatus = this.statusTypes.get(key);
            return true;
        }
        
        return false;
    }
    
    protected StatusManagement getCurrentStatus(){
        return this.currentStatus;
    }
    
    public void resetAllStatus(){
        for (StatusManagement status : this.statusTypes.values()) {
            status.reset();
        }
    }
}
