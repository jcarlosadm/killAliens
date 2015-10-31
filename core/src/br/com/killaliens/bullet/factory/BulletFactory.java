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
 * BulletFactory Using pattern factory method (in getFactory) Using pattern
 * template method (in createBullet and in getAnimations)
 */
public abstract class BulletFactory {

    private static final float ANIMATION_FRAMETIME = 0.1f;

    private static final float INCREMENT_BULLET_DISTANCE = 15f;

    protected BulletFactory() {
    }

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

    public void createBullet(CreateBulletParameter createBulletParameter) {

        float increment = setDistanceBetweenBullets(createBulletParameter);
        float radians = setAngleInRadians(createBulletParameter);

        float positionX = createBulletParameter.getOriginX() - this.getRadius()
                + increment * ((float) Math.cos(radians));
        float positionY = createBulletParameter.getOriginY() - this.getRadius()
                + increment * ((float) Math.sin(radians));

        Speed speed = setSpeed(radians, createBulletParameter);

        BulletProperties bulletProperties = this.buildBulletProperties(
                createBulletParameter, positionX, positionY, speed);

        bulletProperties.addAnimation(AnimationTypes.NORMAL_STATE,
                BuildAnimation.build(ANIMATION_FRAMETIME,
                        getAnimationFramesName()));
        
        if (this.getSoundName() != null && this.getSoundName() != "") {
            bulletProperties.setSound(SoundCache.getSound(this.getSoundName()));
        }

        Bullet bullet = this.getBulletInstance(bulletProperties);

        Stage stage = createBulletParameter.getParentStage();
        if (stage != null && stage instanceof GamePlayResume) {
            ((GamePlayResume) stage).addObjectToGroup(GamePlayObjects.BULLET_LIST,
                    bullet);
        }
    }

    protected float setAngleInRadians(
            CreateBulletParameter createBulletParameter) {
        float radians = (float) Math.toRadians(createBulletParameter
                .getRotation());
        return radians;
    }

    protected float setDistanceBetweenBullets(
            CreateBulletParameter createBulletParameter) {
        float increment = this.getIncrementFromNumBullet(createBulletParameter
                .getNumBullet());
        return increment;
    }

    protected Bullet getBulletInstance(BulletProperties bulletProperties) {
        return new Bullet(bulletProperties);
    }

    protected Speed setSpeed(float radians,
            CreateBulletParameter cBulletParameter) {
        Speed speed = this.getSpeed(cBulletParameter);
        speed.setSpeedX(speed.getSpeedX() * ((float) Math.sin(radians)) * (-1));
        speed.setSpeedY(speed.getSpeedY() * ((float) Math.cos(radians)));
        return speed;
    }

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
        return bulletProperties;
    };

    protected float getIncrementFromNumBullet(int numBullet) {
        if (numBullet == 1) {
            return 0;
        }

        if (numBullet % 2 == 0) {
            return ((numBullet / 2) * INCREMENT_BULLET_DISTANCE);
        }
        return (((numBullet - 1) / 2) * INCREMENT_BULLET_DISTANCE * (-1));
    }

    protected float getRadius() {
        return TextureCache.getTextureRegion(this.getAnimationFramesName()[0])
                .getRegionWidth() / 2;
    }

    protected abstract FirePower getFirePower();

    protected abstract Speed getSpeed(CreateBulletParameter cBulletParameter);

    protected abstract String[] getAnimationFramesName();
    
    protected abstract String getSoundName();
}
