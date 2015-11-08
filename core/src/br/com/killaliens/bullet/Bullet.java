package br.com.killaliens.bullet;

import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.bullet.firepower.NullFirePower;
import br.com.killaliens.explosion.Explosion;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayObjects;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayResume;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.camera.CheckVisibleOnCamera;
import br.com.killaliens.util.collision.CollisionChecker;
import br.com.killaliens.util.speed.NullSpeed;
import br.com.killaliens.util.speed.Speed;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Bullet extends Actor {

    private Circle limits = new Circle();
    private boolean enemyBullet = false;

    private Speed speed = NullSpeed.getNullSpeedInstance();
    private FirePower firePower = NullFirePower.getNullFirePowerInstance();

    private AnimationManagement animationData = null;

    private CheckVisibleOnCamera checkVisibleOnCamera = new CheckVisibleOnCamera(
            this);

    /**
     * Constructor
     * @param properties set of properties
     */
    public Bullet(BulletProperties properties) {
        this.limits.setRadius(properties.getRadius());

        this.setX(properties.getPositionX());
        this.setY(properties.getPositionY());
        this.setWidth(properties.getRadius() * 2);
        this.setHeight(properties.getRadius() * 2);
        this.setBounds(this.getX(), this.getY(), this.getWidth(),
                this.getHeight());

        this.speed = properties.getSpeed();

        this.firePower = properties.getFirePower();
        this.enemyBullet = properties.isEnemyBullet();

        this.animationData = properties.getAnimationData();
        this.animationData.setCurrentAnimation(AnimationTypes.NORMAL_STATE);
        
        if (properties.getSound() != null) {
            properties.getSound().play(0.3f);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.move();
        if (!this.checkVisibleOnCamera.actorIsVisible()) {
            this.remove();
        }
    }

    /**
     * Move action
     */
    protected void move() {
        this.setPosition(this.getX() + this.speed.getSpeedX(),
                this.getY() + this.speed.getSpeedY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        this.animationData.advanceTime(Gdx.graphics.getDeltaTime());
        batch.draw(this.animationData.getCurrentTextureRegion(true),
                this.getX(), this.getY(), this.getOriginX(), this.getOriginY(),
                this.getWidth(), this.getHeight(), this.getScaleX(),
                this.getScaleY(), this.getRotation());
    }

    @Override
    public boolean remove() {
        Stage stage = this.getStage();
        if (stage != null && stage instanceof GamePlayResume) {
            ((GamePlayResume) this.getStage()).removeObjectFromGroup(
                    GamePlayObjects.BULLET_LIST, this);
        }

        return super.remove();
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        this.limits.setX(x + this.getWidth() / 2);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        this.limits.setY(y + this.getHeight() / 2);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        this.limits.setX(x);
        this.limits.setY(y);
    }

    /**
     * Check collision with ship. After collision, this bullet will explode and make damage
     * @param ship Ship to check
     * @return true if collision happens
     */
    public boolean colliding(Ship ship) {
        if (ship.isEnemy() == this.enemyBullet || ship.isDead() == true) {
            return false;
        }
        
        if (CollisionChecker.check(ship.getLimits(),this.limits)) {
            ship.receiveDamage(this.firePower.getDamage());
            this.explode();

            return true;
        }
        return false;
    }

    /**
     * Create explosion and remove bullet
     */
    private void explode() {
        Explosion explosion = new Explosion(this.getX() + this.getWidth() / 2,
                this.getY() + this.getHeight() / 2);
        Stage stage = this.getStage();
        if (stage != null && stage instanceof GamePlayResume) {
            ((GamePlayResume) stage).addObjectToGroup(GamePlayObjects.EXPLOSIONS,
                    explosion);
        }

        this.remove();
    }
}
