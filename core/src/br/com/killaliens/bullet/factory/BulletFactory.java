package br.com.killaliens.bullet.factory;

import br.com.killaliens.bullet.Bullet;
import br.com.killaliens.bullet.BulletProperties;
import br.com.killaliens.bullet.BulletType;
import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayObjects;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayResume;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.animation.BuildAnimation;
import br.com.killaliens.util.cache.images.TextureCache;
import br.com.killaliens.util.cache.sounds.SoundCache;
import br.com.killaliens.util.speed.Speed;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * BulletFactory
 * TODO Abstract factory pattern
 */
public abstract class BulletFactory {

    private static final float ANIMATION_FRAMETIME = 0.1f;

    private static final float INCREMENT_BULLET_DISTANCE = 15f;

    protected BulletFactory() {
    }

    /**
     * get BulletFactory instance
     * @param type type of the Bullet
     * @return BulletFactory instance
     * TODO Factory Method pattern
     */
    public static BulletFactory getFactory(BulletType type) {
        if (type.equals(BulletType.NORMALBULLET)) {
            return new NormalBulletFactory();
        } else if (type.equals(BulletType.BIGBULLET)) {
            return new BigBulletFactory();
        } else if (type.equals(BulletType.FASTBULLET)) {
            return new FastBulletFactory();
        } else if (type.equals(BulletType.FLOWERBULLET)) {
            return new FlowerBulletFactory();
        }

        return NullBulletFactory.getInstance();
    }

    /**
     * create bullet
     * @param createBulletParameter set of parameters
     */
    public void createBullet(CreateBulletParameter createBulletParameter) {

        float increment = getIncrementFromNumBullet(createBulletParameter.getNumBullet());
        float radians = setAngleInRadians(createBulletParameter);

        float positionX = createBulletParameter.getOriginX() - this.getRadius()
                + increment * ((float) Math.cos(radians));
        float positionY = createBulletParameter.getOriginY() - this.getRadius()
                + increment * ((float) Math.sin(radians));

        Speed speed = setSpeed(radians, createBulletParameter);

        BulletProperties bulletProperties = this.buildBulletProperties(
                createBulletParameter, positionX, positionY, speed);

        Bullet bullet = new Bullet(bulletProperties);

        Stage stage = createBulletParameter.getParentStage();
        if (stage != null && stage instanceof GamePlayResume) {
            ((GamePlayResume) stage).addObjectToGroup(GamePlayObjects.BULLET_LIST,
                    bullet);
        }
    }

    /**
     * convert angle degree in radians
     * @param createBulletParameter set of parameters
     * @return angle in radians
     */
    protected float setAngleInRadians(
            CreateBulletParameter createBulletParameter) {
        float radians = (float) Math.toRadians(createBulletParameter
                .getRotation());
        return radians;
    }

    /**
     * Set speed x and y of the bullet
     * @param radians angle in radians
     * @param cBulletParameter set of parameters
     * @return speed object
     */
    protected Speed setSpeed(float radians,
            CreateBulletParameter cBulletParameter) {
        Speed speed = this.getSpeed(cBulletParameter);
        speed.setSpeedX(speed.getSpeedX() * ((float) Math.sin(radians)) * (-1));
        speed.setSpeedY(speed.getSpeedY() * ((float) Math.cos(radians)));
        return speed;
    }

    /**
     * Build bullet properties
     * @param createBulletParameter set of parameters
     * @param positionX
     * @param positionY
     * @param speed
     * @return BulletProperties instance
     */
    private BulletProperties buildBulletProperties(
            CreateBulletParameter createBulletParameter, float positionX,
            float positionY, Speed speed) {

        BulletProperties bulletProperties = new BulletProperties();
        bulletProperties.setEnemyBullet(createBulletParameter.isBulletEnemy());
        bulletProperties.setFirePower(this.getFirePower());
        bulletProperties.setPositionX(positionX);
        bulletProperties.setPositionY(positionY);
        bulletProperties.setRadius(this.getRadius());
        bulletProperties.setSpeed(speed);
        bulletProperties.addAnimation(AnimationTypes.NORMAL_STATE,
                BuildAnimation.build(ANIMATION_FRAMETIME,
                        getAnimationFramesName()));
        
        if (this.getSoundName() != null && this.getSoundName() != "") {
            bulletProperties.setSound(SoundCache.getSound(this.getSoundName()));
        }
        
        return bulletProperties;
    };

    /**
     * Get increment distance of the central bullet
     * @param numBullet number of the bullet (for multiples shoots at once)
     * @return distance of the central bullet
     */
    protected float getIncrementFromNumBullet(int numBullet) {
        if (numBullet == 1) {
            return 0;
        }

        if (numBullet % 2 == 0) {
            return ((numBullet / 2) * INCREMENT_BULLET_DISTANCE);
        }
        return (((numBullet - 1) / 2) * INCREMENT_BULLET_DISTANCE * (-1));
    }

    /**
     * Get radius of the bullet
     * @return radius of the bullet
     */
    protected float getRadius() {
        return TextureCache.getTextureRegion(this.getAnimationFramesName()[0])
                .getRegionWidth() / 2;
    }

    /**
     * get fire power of the bullet
     * @return FirePower object
     */
    protected abstract FirePower getFirePower();

    /**
     * get speed of the bullet
     * @param cBulletParameter set of parameters
     * @return Speed object
     */
    protected abstract Speed getSpeed(CreateBulletParameter cBulletParameter);

    /**
     * get frame names of the bullet animation
     * @return frame names of the bullet animation
     */
    protected abstract String[] getAnimationFramesName();
    
    /**
     * get sound name of the bullet
     * @return sound name of the bullet
     */
    protected abstract String getSoundName();
}
