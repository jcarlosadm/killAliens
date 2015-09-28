package br.com.killaliens.bullet.factory;

import java.util.HashMap;
import java.util.Map;

import br.com.killaliens.ammunition.AmmunitionTypes;
import br.com.killaliens.bullet.Bullet;
import br.com.killaliens.bullet.BulletProperties;
import br.com.killaliens.bullet.firepower.FirePower;
import br.com.killaliens.screens.gamescreen.GameScreen;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.animation.BuildAnimation;
import br.com.killaliens.util.image.TextureCache;
import br.com.killaliens.util.speed.Speed;

import com.badlogic.gdx.graphics.g2d.Animation;
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

    public static BulletFactory getFactory(AmmunitionTypes type) {
        if (type.equals(AmmunitionTypes.NORMALBULLET)) {
            return new NormalBulletFactory();
        } else if (type.equals(AmmunitionTypes.BIGBULLET)) {
            return new BigBulletFactory();
        } else if (type.equals(AmmunitionTypes.FASTBULLET)) {
            return new FastBulletFactory();
        }

        return NullBulletFactory.getInstance();
    }

    public void createBullet(CreateBulletParameter createBulletParameter) {

        float increment = this.getIncrementFromNumBullet(createBulletParameter
                .getNumBullet());
        float radians = (float) Math.toRadians(createBulletParameter.getRotation());

        float positionX = createBulletParameter.getOriginX() - this.getRadius() + increment
                * ((float) Math.cos(radians));
        float positionY = createBulletParameter.getOriginY() - this.getRadius() + increment
                * ((float) Math.sin(radians));

        Speed speed = this.getSpeed();
        speed.setSpeedX(speed.getSpeedX() * ((float) Math.sin(radians)) * (-1));
        speed.setSpeedY(speed.getSpeedY() * ((float) Math.cos(radians)));

        BulletProperties bulletProperties = this.buildBulletProperties(
                createBulletParameter, positionX, positionY, speed);

        this.setAnimations(bulletProperties);
        
        Bullet bullet = new Bullet(bulletProperties);
        Stage stage = createBulletParameter.getParentStage();
        if (stage != null && stage instanceof GameScreen) {
            ((GameScreen)stage).addBullet(bullet);
        }
    }

    private void setAnimations(BulletProperties bulletProperties) {
        Map<AnimationTypes, Animation> animations = this.getAnimations();
        
        for (AnimationTypes type : AnimationTypes.values()) {
            bulletProperties.addAnimation(type, animations.get(type));
        }
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

    private float getIncrementFromNumBullet(int numBullet) {
        if (numBullet == 1) { return 0; }

        if (numBullet % 2 == 0) {
            return ((numBullet / 2) * INCREMENT_BULLET_DISTANCE);
        }
        return (((numBullet - 1) / 2) * INCREMENT_BULLET_DISTANCE * (-1));
    }

    protected abstract FirePower getFirePower();

    protected abstract Speed getSpeed();

    protected float getRadius(){
        return TextureCache.getTextureRegion(this.getAnimationNormalFramesName()[0])
                .getRegionWidth()/2;
    }

    protected abstract String[] getAnimationNormalFramesName();

    protected abstract String[] getAnimationDeadFramesName();

    protected Map<AnimationTypes, Animation> getAnimations() {
        Map<AnimationTypes, Animation> animations = new HashMap<AnimationTypes, Animation>();

        this.buildAnimationType(animations, getAnimationNormalFramesName(),
                AnimationTypes.NORMAL_STATE);
        this.buildAnimationType(animations, getAnimationDeadFramesName(),
                AnimationTypes.DEAD);

        return animations;
    }

    private void buildAnimationType(Map<AnimationTypes, Animation> animations,
            String[] frameNames, AnimationTypes stateName) {

        Animation animation = BuildAnimation.build(ANIMATION_FRAMETIME,
                frameNames);
        if (animation != null) {
            animations.put(stateName, animation);
        }
    }
}
