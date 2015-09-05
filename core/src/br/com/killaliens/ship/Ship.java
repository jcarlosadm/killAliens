package br.com.killaliens.ship;

import java.awt.Point;
import java.util.Arrays;
import java.util.Stack;

import br.com.killaliens.ammunition.Ammunition;
import br.com.killaliens.bullet.factory.CreateBulletParameter;
import br.com.killaliens.ship.status.Life;
import br.com.killaliens.ship.status.NullLife;
import br.com.killaliens.ship.status.NullShield;
import br.com.killaliens.ship.status.Shield;
import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.collision.CollisionPolygonWithCircle;
import br.com.killaliens.util.speed.NullSpeed;
import br.com.killaliens.util.speed.Speed;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Ship
 * 
 * Note:
 * credits for method colliding(Circle):
 * http://stackoverflow.com/questions/15323719/circle-and-polygon-collision-with-libgdx
 */
public abstract class Ship extends Actor {

    private Speed speed = NullSpeed.getNullSpeedInstance();

    private Life life = NullLife.getNullLifeInstance();
    private Shield shield = NullShield.getNullShieldInstance();

    private boolean shooting = false;

    private Stack<Ammunition> ammunitions = new Stack<Ammunition>();

    private Polygon limits = new Polygon();

    private AnimationManagement animationData = new AnimationManagement();

    private boolean enemy = false;

    /**
     * Constructor
     * 
     * @param properties
     *            properties of the ship
     */
    public Ship(ShipProperties properties) {
        this.limits.setVertices(this.buildVertices(properties.getWidth(),
                properties.getHeight()));

        this.setX(properties.getPositionX());
        this.setY(properties.getPositionY());
        this.setHeight(properties.getHeight());
        this.setWidth(properties.getWidth());

        this.setOrigin(properties.getOriginX(), properties.getOriginY());
        this.setRotation(properties.getRotation());

        setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        this.speed = properties.getSpeed();
        this.life = properties.getLife();
        this.shield = properties.getShield();

        Ammunition ammunition = new Ammunition(properties.getBasicAmmunition());
        ammunition.setInfinity(true);

        this.addAmmunition(ammunition);
    }

    private float[] buildVertices(float width, float height) {
        float[] vertices = new float[8];
        Arrays.fill(vertices, -100);
        
        Point[] points = new Point[4];
        
        for (int index = 0; index < points.length; index++) {
            int x = (index % 2 != 0 ? (int) width : 0);
            int y = (index >= 2 ? (int) height : 0);
            points[index] = new Point(x, y);
        }
        
        for (int pointIndex = 0; pointIndex < points.length; pointIndex++) {
            vertices = addPointToVertice(vertices, points[pointIndex]);
        }

        return vertices;
    }

    private float[] addPointToVertice(float[] vertices, Point point) {
        int arrayPosition = 0;

        for (int verticeIndex = 0; verticeIndex < vertices.length; verticeIndex++) {
            if (vertices[verticeIndex] == -100) {
                arrayPosition = verticeIndex;
            }
        }

        vertices[arrayPosition] = point.x;
        vertices[arrayPosition + 1] = point.y;
        return vertices;
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
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        this.limits.setRotation(this.getRotation());
    }

    @Override
    public void setOrigin(float originX, float originY) {
        super.setOrigin(originX, originY);
        this.limits.setOrigin(originX, originY);
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
        this.animationData.advanceTime(Gdx.graphics.getDeltaTime());
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

    /**
     * @return the speedY
     */
    public float getSpeedY() {
        return this.speed.getSpeedY();
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
            this.life.modifyCurrentLife(totalDamage * (-1));
        }
    }

    /**
     * Get heal (must be greater than zero)
     * 
     * @param heal
     */
    public void getHeal(int heal) {
        if (heal > 0) {
            this.life.modifyCurrentLife(heal);
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

    /**
     * set shooting status
     * 
     * @param shooting
     *            the shooting to set
     */
    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    private void shoot() {
        CreateBulletParameter createBulletParameters = new CreateBulletParameter();
        
        this.buildCreateBulletParameter(createBulletParameters);
        
        this.ammunitions.peek().makeBullets(createBulletParameters);

        if (!this.ammunitions.peek().hasAmmunition()) {
            this.ammunitions.pop();
        }
    }

    private void buildCreateBulletParameter(
            CreateBulletParameter createBulletParameters) {
        createBulletParameters.setBulletEnemy(this.isEnemy());
        createBulletParameters.setOriginX(this.getOriginX());
        createBulletParameters.setOriginY(this.getOriginY());
        createBulletParameters.setRotation(this.getRotation());
        createBulletParameters.setParentStage(this.getStage());
    }

    public boolean colliding(Circle circle) {
        return CollisionPolygonWithCircle.checkCollision(this.limits, circle);
    }

    public boolean colliding(Polygon polygon) {
        return (Intersector.overlapConvexPolygons(this.limits, polygon));
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
}
