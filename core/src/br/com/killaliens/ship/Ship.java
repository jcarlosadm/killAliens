package br.com.killaliens.ship;

import java.util.Stack;

import br.com.killaliens.ammunition.Ammunition;
import br.com.killaliens.bullet.factory.CreateBulletParameter;
import br.com.killaliens.status.Life;
import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.speed.Speed;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Ship
 * 
 * Note:
 * credits for the method colliding(Circle):
 * http://stackoverflow.com/questions/15323719/circle-and-polygon-collision-with-libgdx
 */
public abstract class Ship extends Actor {

    private static final int MINSPEEDY = 1;
    private static final int MINSPEEDX = 1;

    private Speed speed = new Speed(MINSPEEDX, MINSPEEDY);

    private Life life = null;
    private int shield = 0;

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

        this.speed.setSpeedX(properties.getSpeedX());
        this.speed.setSpeedY(properties.getSpeedY());
        this.life = properties.getLife();
        this.shield = properties.getShield();

        Ammunition ammunition = new Ammunition(properties.getBasicAmmunition());
        ammunition.setInfinity(true);

        this.addAmmunition(ammunition);
    }

    private float[] buildVertices(float width, float height) {
        float[] vertices = new float[8];

        vertices = this.buildPoint(vertices, 1, 0f, 0f);
        vertices = this.buildPoint(vertices, 2, width, 0f);
        vertices = this.buildPoint(vertices, 3, 0f, height);
        vertices = this.buildPoint(vertices, 4, width, height);

        return vertices;
    }

    private float[] buildPoint(float[] vertices, int pointNumber, float xValue,
            float yValue) {
        int arrayPosition = 0;

        switch (pointNumber) {
        case 1:
            arrayPosition = 0;
            break;
        case 2:
            arrayPosition = 2;
            break;
        case 3:
            arrayPosition = 4;
            break;
        case 4:
            arrayPosition = 6;
            break;
        default:
            break;
        }

        vertices[arrayPosition] = xValue;
        vertices[arrayPosition + 1] = yValue;
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

        int totalDamage = damage - this.shield;

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
    public int getShield() {
        return this.shield;
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
        CreateBulletParameter cBulletParameter = new CreateBulletParameter();
        
        cBulletParameter.setBulletEnemy(this.isEnemy());
        cBulletParameter.setOriginX(this.getOriginX());
        cBulletParameter.setOriginY(this.getOriginY());
        cBulletParameter.setRotation(this.getRotation());
        cBulletParameter.setParentStage(this.getStage());
        
        this.ammunitions.peek().makeBullets(cBulletParameter);

        if (!this.ammunitions.peek().hasAmmunition()) {
            this.ammunitions.pop();
        }
    }

    public boolean colliding(Circle circle) {

        float[] vertices = this.limits.getTransformedVertices();
        Vector2 center = new Vector2(circle.x, circle.y);
        float squareRadius = circle.radius * circle.radius;

        for (int i = 0; i < vertices.length; i += 2) {
            if (i == 0) {
                if (Intersector.intersectSegmentCircle(new Vector2(
                        vertices[vertices.length - 2],
                        vertices[vertices.length - 1]), new Vector2(
                        vertices[i], vertices[i + 1]), center, squareRadius))
                    return true;
            } else {
                if (Intersector.intersectSegmentCircle(new Vector2(
                        vertices[i - 2], vertices[i - 1]), new Vector2(
                        vertices[i], vertices[i + 1]), center, squareRadius))
                    return true;
            }
        }
        return this.limits.contains(circle.x, circle.y);
    }

    public boolean colliding(Polygon polygon) {
        if (Intersector.overlapConvexPolygons(this.limits, polygon)) {
            return true;
        }
        return false;
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
